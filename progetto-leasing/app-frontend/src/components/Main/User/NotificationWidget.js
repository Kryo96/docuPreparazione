import React from 'react';

const NotificationWidget = () => {
    return (
        <div className="card p-3 mb-4">
            <h5>Notifiche</h5>
            <ul className="list-group">
                <li className="list-group-item">Scadenza pagamento: Leasing Auto - 12 Giugno 2025</li>
                <li className="list-group-item">Pagamento in ritardo: Leasing Casa - 20 Maggio 2025</li>
            </ul>
        </div>
    );
};

export default NotificationWidget;
