import React, { useState } from 'react';

const DocumentManagementPanel = () => {
    const [documents, setDocuments] = useState([
        { id: 1, nome: 'Documento Identità', stato: 'Completo', verificato: true },
        { id: 2, nome: 'Contratto Leasing', stato: 'Incompleto', verificato: false },
        { id: 3, nome: 'Prova di Residenza', stato: 'Completo', verificato: true },
    ]);

    const handleVerification = (id) => {
        setDocuments((prevDocuments) =>
            prevDocuments.map((doc) =>
                doc.id === id ? { ...doc, verificato: !doc.verificato } : doc
            )
        );
    };

    return (
        <div className="card p-3 mb-4">
            <h5>Gestione Documentale</h5>
            <table className="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Nome Documento</th>
                    <th scope="col">Stato</th>
                    <th scope="col">Verifica</th>
                </tr>
                </thead>
                <tbody>
                {documents.map((doc) => (
                    <tr key={doc.id}>
                        <td>{doc.nome}</td>
                        <td>{doc.stato}</td>
                        <td>
                            <button
                                className={`btn btn-sm ${doc.verificato ? 'btn-success' : 'btn-warning'}`}
                                onClick={() => handleVerification(doc.id)}
                            >
                                {doc.verificato ? 'Verificato' : 'Non Verificato'}
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default DocumentManagementPanel;
