import React from 'react';
import { FaCalendarAlt } from 'react-icons/fa';

const TeamCalendar = () => {
    const appuntamenti = [
        { id: 1, titolo: 'Scadenza Pratica 123', data: '2025-05-22', descrizione: 'Scadenza pagamento' },
        { id: 2, titolo: 'Incontro con cliente ABC', data: '2025-05-23', descrizione: 'Discussione rinnovo' },
        { id: 3, titolo: 'Controllo documenti', data: '2025-05-25', descrizione: 'Verifica documentazione' },
    ];

    return (
        <div className="card p-3 mb-4">
            <h5>
                <FaCalendarAlt className="me-2" />
                Calendario Condiviso
            </h5>
            <div className="list-group">
                {appuntamenti.map((appuntamento) => (
                    <div key={appuntamento.id} className="list-group-item d-flex justify-content-between align-items-center">
                        <div>
                            <strong>{appuntamento.titolo}</strong> – {appuntamento.data}
                            <p className="mb-0">{appuntamento.descrizione}</p>
                        </div>
                        <button className="btn btn-sm btn-info">Dettagli</button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default TeamCalendar;
