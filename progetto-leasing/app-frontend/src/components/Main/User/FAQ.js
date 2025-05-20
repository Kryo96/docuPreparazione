import React from 'react';

const FAQ = () => {
    const faqs = [
        { question: 'Cos\'è un leasing?', answer: 'Il leasing è un contratto di locazione con opzione di acquisto al termine del periodo di leasing.' },
        { question: 'Quali sono i vantaggi del leasing?', answer: 'Il leasing consente di utilizzare un bene senza doverlo acquistare, mantenendo liquidità disponibile.' },
        { question: 'Come posso richiedere un leasing?', answer: 'Puoi richiedere un leasing direttamente attraverso il nostro sito, compilando il modulo di richiesta.' },
    ];

    return (
        <div className="card p-3 mb-4">
            <h5>Domande Frequenti (FAQ)</h5>
            <ul className="list-group">
                {faqs.map((faq, index) => (
                    <li key={index} className="list-group-item">
                        <strong>{faq.question}</strong>
                        <p>{faq.answer}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default FAQ;
