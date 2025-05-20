import React from 'react';

const WorkflowVisual = () => {
    const workflowStages = [
        { id: 1, fase: 'Inizio', stato: 'Completato' },
        { id: 2, fase: 'Verifica Dati', stato: 'In Corso' },
        { id: 3, fase: 'Approvazione Finanziaria', stato: 'In Attesa' },
        { id: 4, fase: 'Contratto Firmato', stato: 'In Attesa' },
        { id: 5, fase: 'Attivazione Contratto', stato: 'In Attesa' },
    ];

    return (
        <div className="card p-3 mb-4">
            <h5>Workflow delle Pratiche</h5>
            <div className="d-flex justify-content-between">
                {workflowStages.map((stage) => (
                    <div key={stage.id} className="text-center">
                        <div className={`badge bg-${getStatusBadge(stage.stato)} rounded-pill mb-2`}>
                            {stage.fase}
                        </div>
                        <span>{stage.stato}</span>
                    </div>
                ))}
            </div>
        </div>
    );
};

// Funzione per determinare il badge di stato
function getStatusBadge(stato) {
    switch (stato) {
        case 'Completato':
            return 'success';
        case 'In Corso':
            return 'warning';
        case 'In Attesa':
            return 'secondary';
        default:
            return 'primary';
    }
}

export default WorkflowVisual;
