import React from 'react';

const SLAMonitor = () => {
    const praticheSLA = [
        { id: 1, cliente: 'Mario Rossi', stato: 'In ritardo', scadenza: '2025-05-22' },
        { id: 2, cliente: 'Luca Bianchi', stato: 'In tempo', scadenza: '2025-05-23' },
        { id: 3, cliente: 'Sara Verdi', stato: 'In ritardo', scadenza: '2025-05-20' },
    ];

    return (
        <div className="card p-3 mb-4">
            <h5>Monitoraggio SLA</h5>
            <div className="list-group">
                {praticheSLA.map((pratica) => (
                    <div
                        key={pratica.id}
                        className={`list-group-item d-flex justify-content-between align-items-center ${
                            pratica.stato === 'In ritardo' ? 'bg-danger text-white' : 'bg-success text-white'
                        }`}
                    >
                        <div>
                            <strong>{pratica.cliente}</strong> – Scadenza: {pratica.scadenza}
                        </div>
                        <span className={`badge bg-${pratica.stato === 'In ritardo' ? 'danger' : 'success'}`}>
              {pratica.stato}
            </span>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default SLAMonitor;
