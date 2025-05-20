import React from 'react';

const Dashboard = () => {
    return (
        <div className="row mb-4">
            <div className="col-md-4">
                <div className="card p-3 text-center">
                    <h5>Contratti Attivi</h5>
                    <p>5</p>
                </div>
            </div>
            <div className="col-md-4">
                <div className="card p-3 text-center">
                    <h5>Pagamenti in Scadenza</h5>
                    <p>2</p>
                </div>
            </div>
            <div className="col-md-4">
                <div className="card p-3 text-center">
                    <h5>Importo Totale</h5>
                    <p>€12,500</p>
                </div>
            </div>
        </div>
    );
};

export default Dashboard;
