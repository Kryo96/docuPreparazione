import React, { useState } from 'react';

const CreditAssessmentTools = () => {
    const [score, setScore] = useState(null);
    const [clientData, setClientData] = useState({
        nome: 'Luca Bianchi',
        reddito: 45000,
        debiti: 5000,
        storicoPagamenti: 'Eccellente',
    });

    const calculateScore = () => {
        let calculatedScore = 0;
        if (clientData.reddito > 40000) calculatedScore += 30;
        if (clientData.debiti < 10000) calculatedScore += 40;
        if (clientData.storicoPagamenti === 'Eccellente') calculatedScore += 30;
        setScore(calculatedScore);
    };

    return (
        <div className="card p-3 mb-4">
            <h5>Valutazione del Credito</h5>
            <div className="mb-3">
                <p><strong>Nome Cliente:</strong> {clientData.nome}</p>
                <p><strong>Reddito Annuo:</strong> €{clientData.reddito}</p>
                <p><strong>Debiti:</strong> €{clientData.debiti}</p>
                <p><strong>Storico Pagamenti:</strong> {clientData.storicoPagamenti}</p>
            </div>
            <button className="btn btn-primary" onClick={calculateScore}>
                Calcola Punteggio Credito
            </button>
            {score !== null && (
                <div className="mt-3">
                    <h6>Punteggio di Credito: {score}</h6>
                    <p>
                        {score >= 70
                            ? 'Credito Eccellente!'
                            : score >= 50
                                ? 'Credito Buono'
                                : 'Credito a Rischio'}
                    </p>
                </div>
            )}
        </div>
    );
};

export default CreditAssessmentTools;
