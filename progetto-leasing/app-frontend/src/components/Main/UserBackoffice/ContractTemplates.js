import React from 'react';

const ContractTemplates = () => {
    const templates = [
        { id: 1, nome: 'Contratto Leasing Auto', formato: 'PDF', link: '#', descrizione: 'Modello di contratto per leasing auto.' },
        { id: 2, nome: 'Contratto Leasing Immobiliare', formato: 'PDF', link: '#', descrizione: 'Modello di contratto per leasing immobiliare.' },
        { id: 3, nome: 'Contratto Leasing Macchinari', formato: 'PDF', link: '#', descrizione: 'Modello di contratto per leasing di macchinari.' },
    ];

    return (
        <div className="card p-3 mb-4">
            <h5>Modelli di Contratto e Documentazione</h5>
            <table className="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Nome Contratto</th>
                    <th scope="col">Formato</th>
                    <th scope="col">Descrizione</th>
                    <th scope="col">Download</th>
                </tr>
                </thead>
                <tbody>
                {templates.map((template) => (
                    <tr key={template.id}>
                        <td>{template.nome}</td>
                        <td>{template.formato}</td>
                        <td>{template.descrizione}</td>
                        <td>
                            <a href={template.link} className="btn btn-info btn-sm" download>
                                Scarica
                            </a>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default ContractTemplates;
