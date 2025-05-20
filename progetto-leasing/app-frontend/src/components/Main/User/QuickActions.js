import React from 'react';

const QuickActions = () => {
    return (
        <div className="card p-3 mb-4">
            <h5>Azioni Rapide</h5>
            <div className="d-flex gap-2">
                <button className="btn btn-primary">Effettua Pagamento</button>
                <button className="btn btn-success">Richiedi Preventivo</button>
                <button className="btn btn-warning">Contatta Assistenza</button>
            </div>
        </div>
    );
};

export default QuickActions;
