import React, { useState } from 'react';

const AlertSystem = () => {
    const [alerts, setAlerts] = useState([
        { id: 1, tipo: 'Scadenza contratto', descrizione: 'Contratto 123 in scadenza il 10 Giugno 2025.', attivo: true },
        { id: 2, tipo: 'Anomalia pagamento', descrizione: 'Pagamento pratica 456 non ricevuto.', attivo: false },
        { id: 3, tipo: 'Soglia superata', descrizione: 'Importo leasing superato la soglia di 100.000€.', attivo: true },
    ]);

    const toggleAlertStatus = (id) => {
        setAlerts((prevAlerts) =>
            prevAlerts.map((alert) =>
                alert.id === id ? { ...alert, attivo: !alert.attivo } : alert
            )
        );
    };

    return (
        <div className="card p-3 mb-4">
            <h5>Gestione Allarmi</h5>
            <table className="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Tipo Allarme</th>
                    <th scope="col">Descrizione</th>
                    <th scope="col">Stato</th>
                    <th scope="col">Azione</th>
                </tr>
                </thead>
                <tbody>
                {alerts.map((alert) => (
                    <tr key={alert.id}>
                        <td>{alert.tipo}</td>
                        <td>{alert.descrizione}</td>
                        <td>
                <span className={`badge bg-${alert.attivo ? 'success' : 'danger'} rounded-pill`}>
                  {alert.attivo ? 'Attivo' : 'Disattivato'}
                </span>
                        </td>
                        <td>
                            <button
                                className={`btn btn-${alert.attivo ? 'danger' : 'success'} btn-sm`}
                                onClick={() => toggleAlertStatus(alert.id)}
                            >
                                {alert.attivo ? 'Disattiva' : 'Attiva'}
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default AlertSystem;
