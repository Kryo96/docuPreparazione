import React from 'react';

const ExceptionManagement = () => {
    const anomalies = [
        { id: 1, pratica: 'Pratica 123', tipo: 'Errore Documentazione', data: '2025-05-19', assegnatoA: 'Luca B.' },
        { id: 2, pratica: 'Pratica 456', tipo: 'Superamento Limite Importo', data: '2025-05-18', assegnatoA: 'Giulia M.' },
        { id: 3, pratica: 'Pratica 789', tipo: 'Ritardo Verifica', data: '2025-05-17', assegnatoA: 'Marco D.' },
    ];

    const handleResolve = (id) => {
        // Simula la risoluzione di un'anomalia
        console.log(`Anomalia ${id} risolta`);
    };

    return (
        <div className="card p-3 mb-4">
            <h5>Gestione Eccezioni/Anomalie</h5>
            <div className="list-group">
                {anomalies.map((anomaly) => (
                    <div key={anomaly.id} className="list-group-item d-flex justify-content-between align-items-center">
                        <div>
                            <strong>Pratica {anomaly.pratica}</strong> – {anomaly.tipo}
                            <p className="mb-0">Data: {anomaly.data}</p>
                            <p className="mb-0">Assegnato a: {anomaly.assegnatoA}</p>
                        </div>
                        <button className="btn btn-sm btn-success" onClick={() => handleResolve(anomaly.id)}>
                            Risolvi
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ExceptionManagement;
