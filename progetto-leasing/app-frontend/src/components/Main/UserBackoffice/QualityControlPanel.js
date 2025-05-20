import React from 'react';

const QualityControlPanel = () => {
    const auditTrail = [
        { id: 1, operazione: 'Modifica pratica 123', data: '2025-05-19', eseguitaDa: 'Luca' },
        { id: 2, operazione: 'Aggiunta documento', data: '2025-05-18', eseguitaDa: 'Giulia' },
        { id: 3, operazione: 'Verifica stato pratica 456', data: '2025-05-17', eseguitaDa: 'Marco' },
        { id: 4, operazione: 'Chiusura pratica 789', data: '2025-05-16', eseguitaDa: 'Andrea' },
    ];

    return (
        <div className="card p-3 mb-4">
            <h5>Pannello di Controllo Qualità</h5>
            <table className="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Operazione</th>
                    <th scope="col">Data</th>
                    <th scope="col">Eseguita Da</th>
                </tr>
                </thead>
                <tbody>
                {auditTrail.map((audit) => (
                    <tr key={audit.id}>
                        <td>{audit.operazione}</td>
                        <td>{audit.data}</td>
                        <td>{audit.eseguitaDa}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default QualityControlPanel;
