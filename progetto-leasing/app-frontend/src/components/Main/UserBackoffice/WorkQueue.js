// WorkQueue.js
import React from 'react';

const WorkQueue = () => {
    const pratiche = [
        { id: 1, cliente: 'Mario Rossi', scadenza: '2025-05-22', priorita: 'Alta' },
        { id: 2, cliente: 'Luca Bianchi', scadenza: '2025-05-24', priorita: 'Media' },
        { id: 3, cliente: 'Sara Verdi', scadenza: '2025-05-21', priorita: 'Alta' },
        { id: 4, cliente: 'Giulia Neri', scadenza: '2025-05-25', priorita: 'Bassa' },
    ];

    return (
        <div className="card p-3 mb-4">
            <h5>Coda di Lavoro - Pratiche da Elaborare</h5>
            <ul className="list-group">
                {pratiche
                    .sort((a, b) => new Date(a.scadenza) - new Date(b.scadenza))
                    .map(pratica => (
                        <li
                            key={pratica.id}
                            className="list-group-item d-flex justify-content-between align-items-center"
                        >
                            <div>
                                <strong>{pratica.cliente}</strong> – Scadenza: {pratica.scadenza}
                            </div>
                            <span className={`badge bg-${getBadgeColor(pratica.priorita)} rounded-pill`}>
                Priorità: {pratica.priorita}
              </span>
                        </li>
                    ))}
            </ul>
        </div>
    );
};

// Funzione helper per assegnare colori Bootstrap in base alla priorità
function getBadgeColor(priorita) {
    switch (priorita) {
        case 'Alta':
            return 'danger';
        case 'Media':
            return 'warning';
        case 'Bassa':
            return 'secondary';
        default:
            return 'light';
    }
}

export default WorkQueue;
