import React from 'react';

const ContactWidget = () => {
    return (
        <div className="card p-3 mb-4">
            <h5>Contatta l'Assistenza</h5>
            <div className="d-flex gap-2">
                <button className="btn btn-info">Invia Messaggio</button>
                <button className="btn btn-warning">Chiamaci</button>
            </div>
        </div>
    );
};

export default ContactWidget;
