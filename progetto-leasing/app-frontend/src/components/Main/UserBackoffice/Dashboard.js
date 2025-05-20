// Dashboard.js
import React from 'react';

const Dashboard = () => {
    const kpi = {
        praticheInAttesa: 27,
        tempoMedioEvasione: '3.2 giorni',
        tassoApprovazione: '84%',
    };

    return (
        <div className="card p-3 mb-4">
            <h5>Dashboard Operativa</h5>
            <div className="row text-center">
                <div className="col-md-4">
                    <div className="border rounded p-3 bg-light">
                        <h6>Pratiche in Attesa</h6>
                        <p className="display-6">{kpi.praticheInAttesa}</p>
                    </div>
                </div>
                <div className="col-md-4">
                    <div className="border rounded p-3 bg-light">
                        <h6>Tempo Medio di Evasione</h6>
                        <p className="display-6">{kpi.tempoMedioEvasione}</p>
                    </div>
                </div>
                <div className="col-md-4">
                    <div className="border rounded p-3 bg-light">
                        <h6>Tasso di Approvazione</h6>
                        <p className="display-6">{kpi.tassoApprovazione}</p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Dashboard;
