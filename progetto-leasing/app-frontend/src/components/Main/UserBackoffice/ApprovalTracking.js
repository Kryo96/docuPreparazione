import React, { useState } from 'react';

const ApprovalTracking = () => {
    const [approvals, setApprovals] = useState([
        { id: 1, pratica: 'Pratica 123', livello: 1, stato: 'In attesa' },
        { id: 2, pratica: 'Pratica 456', livello: 2, stato: 'Approvata' },
        { id: 3, pratica: 'Pratica 789', livello: 3, stato: 'Rifiutata' },
    ]);

    const handleApprovalChange = (id, nuovoStato) => {
        setApprovals((prevApprovals) =>
            prevApprovals.map((approval) =>
                approval.id === id ? { ...approval, stato: nuovoStato } : approval
            )
        );
    };

    return (
        <div className="card p-3 mb-4">
            <h5>Tracciamento Approvazioni</h5>
            <table className="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Pratica</th>
                    <th scope="col">Livello Approvazione</th>
                    <th scope="col">Stato</th>
                    <th scope="col">Azione</th>
                </tr>
                </thead>
                <tbody>
                {approvals.map((approval) => (
                    <tr key={approval.id}>
                        <td>{approval.pratica}</td>
                        <td>{`Livello ${approval.livello}`}</td>
                        <td>
                <span className={`badge bg-${getApprovalBadge(approval.stato)} rounded-pill`}>
                  {approval.stato}
                </span>
                        </td>
                        <td>
                            {approval.stato === 'In attesa' && (
                                <button
                                    className="btn btn-success btn-sm"
                                    onClick={() => handleApprovalChange(approval.id, 'Approvata')}
                                >
                                    Approva
                                </button>
                            )}
                            {approval.stato === 'Approvata' && (
                                <button
                                    className="btn btn-danger btn-sm"
                                    onClick={() => handleApprovalChange(approval.id, 'Rifiutata')}
                                >
                                    Rifiuta
                                </button>
                            )}
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

// Funzione per determinare il badge di stato
function getApprovalBadge(stato) {
    switch (stato) {
        case 'Approvata':
            return 'success';
        case 'Rifiutata':
            return 'danger';
        case 'In attesa':
            return 'warning';
        default:
            return 'secondary';
    }
}

export default ApprovalTracking;
