import React from 'react';

const QuickReporting = () => {
    const reportData = [
        { id: 1, nome: 'Mario Rossi', pratica: 'Pratica 123', importo: '€5,000', stato: 'Completato' },
        { id: 2, nome: 'Luca Bianchi', pratica: 'Pratica 456', importo: '€3,500', stato: 'In elaborazione' },
        { id: 3, nome: 'Sara Verdi', pratica: 'Pratica 789', importo: '€7,200', stato: 'Anomalia' },
    ];

    const exportData = () => {
        // Logica di esportazione (per esempio, conversione in CSV, Excel, etc.)
        console.log('Dati esportati:', reportData);
    };

    return (
        <div className="card p-3 mb-4">
            <h5>Reporting Rapido</h5>
            <table className="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Nome Cliente</th>
                    <th scope="col">Pratica</th>
                    <th scope="col">Importo</th>
                    <th scope="col">Stato</th>
                </tr>
                </thead>
                <tbody>
                {reportData.map((report) => (
                    <tr key={report.id}>
                        <td>{report.nome}</td>
                        <td>{report.pratica}</td>
                        <td>{report.importo}</td>
                        <td>
                <span className={`badge bg-${getStatusBadge(report.stato)} rounded-pill`}>
                  {report.stato}
                </span>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <button className="btn btn-primary mt-3" onClick={exportData}>
                Esporta Dati
            </button>
        </div>
    );
};

// Funzione per determinare il badge di stato
function getStatusBadge(stato) {
    switch (stato) {
        case 'Completato':
            return 'success';
        case 'In elaborazione':
            return 'info';
        case 'Anomalia':
            return 'danger';
        default:
            return 'secondary';
    }
}

export default QuickReporting;
