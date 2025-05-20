import React from 'react';

const PaymentStatusPanel = () => {
    return (
        <div className="card p-3 mb-4">
            <h5>Status dei Pagamenti</h5>
            <ul className="list-group">
                <li className="list-group-item text-success">Pagato: Leasing Auto</li>
                <li className="list-group-item text-warning">In Scadenza: Leasing Moto</li>
                <li className="list-group-item text-danger">In Ritardo: Leasing Casa</li>
            </ul>
        </div>
    );
};

export default PaymentStatusPanel;
