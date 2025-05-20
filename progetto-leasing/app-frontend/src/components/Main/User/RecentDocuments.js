import React from 'react';

const RecentDocuments = () => {
    const documents = [
        { name: 'Contratto Leasing Auto', date: '12 Maggio 2025', url: '/documents/contratto_auto.pdf' },
        { name: 'Preventivo Leasing Casa', date: '10 Maggio 2025', url: '/documents/preventivo_casa.pdf' },
    ];

    return (
        <div className="card p-3 mb-4">
            <h5>Documenti Recenti</h5>
            <ul className="list-group">
                {documents.map((doc, index) => (
                    <li key={index} className="list-group-item">
                        <a href={doc.url} download>{doc.name}</a> - {doc.date}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default RecentDocuments;
