// ClientRequestsTable.js
import React from 'react';

const ClientRequestsTable = () => {
    const richieste = [
        { id: 1, cliente: 'Mario Rossi', stato: 'In attesa', data: '2025-05-20', assegnatario: 'Luca' },
        { id: 2, cliente: 'Luca Bianchi', stato: 'In elaborazione', data: '2025-05-18', assegnatario: 'Giulia' },
        { id: 3, cliente: 'Sara Verdi', stato: 'Completato', data: '2025-05-15', assegnatario: 'Marco' },
        { id: 4, cliente: 'Giulia Neri', stato: 'Anomalia', data: '2025-05-17', assegnatario: 'Andrea' },
    ];

    return (
        <div className="card p-3 mb-4">
            <h5>Tabella delle Richieste Clienti</h5>
            <table className="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Cliente</th>
                    <th scope="col">Stato</th>
                    <th scope="col">Data</th>
                    <th scope="col">Assegnatario</th>
                    <th scope="col">Azioni</th>
                </tr>
                </thead>
                <tbody>
                {richieste.map((richiesta) => (
                    <tr key={richiesta.id}>
                        <td>{richiesta.cliente}</td>
                        <td>
                <span className={`badge bg-${getStatusBadge(richiesta.stato)} rounded-pill`}>
                  {richiesta.stato}
                </span>
                        </td>
                        <td>{richiesta.data}</td>
                        <td>{richiesta.assegnatario}</td>
                        <td>
                            <button className="btn btn-sm btn-primary me-2">Visualizza</button>
                            <button className="btn btn-sm btn-warning">Azione Rapida</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

// Funzione per colorare lo stato
function getStatusBadge(stato) {
    switch (stato) {
        case 'In attesa':
            return 'warning';
        case 'In elaborazione':
            return 'info';
        case 'Completato':
            return 'success';
        case 'Anomalia':
            return 'danger';
        default:
            return 'secondary';
    }
}

export default ClientRequestsTable;
