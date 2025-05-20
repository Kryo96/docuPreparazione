import React, { useState } from 'react';

const RenewalsAndClosures = () => {
    const [contracts, setContracts] = useState([
        { id: 1, nome: 'Leasing Auto BMW', scadenza: '2025-06-15', rinnovoImminente: true, tipo: 'Rinnovo' },
        { id: 2, nome: 'Leasing Immobile Milano', scadenza: '2025-07-01', rinnovoImminente: false, tipo: 'Chiusura' },
        { id: 3, nome: 'Leasing Macchinario', scadenza: '2025-05-20', rinnovoImminente: true, tipo: 'Rinnovo' },
    ]);

    const handleRenewalOrClosure = (id, tipo) => {
        setContracts((prevContracts) =>
            prevContracts.map((contract) =>
                contract.id === id ? { ...contract, tipo } : contract
            )
        );
    };

    return (
        <div className="card p-3 mb-4">
            <h5>Gestione Rinnovi e Chiusure Contrattuali</h5>
            <table className="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Contratto</th>
                    <th scope="col">Data Scadenza</th>
                    <th scope="col">Tipo</th>
                    <th scope="col">Azione</th>
                </tr>
                </thead>
                <tbody>
                {contracts.map((contract) => (
                    <tr key={contract.id}>
                        <td>{contract.nome}</td>
                        <td>{contract.scadenza}</td>
                        <td>
                <span className={`badge bg-${contract.rinnovoImminente ? 'danger' : 'success'} rounded-pill`}>
                  {contract.tipo}
                </span>
                        </td>
                        <td>
                            {contract.rinnovoImminente && (
                                <button
                                    className="btn btn-primary btn-sm"
                                    onClick={() => handleRenewalOrClosure(contract.id, 'Rinnovo')}
                                >
                                    Rinnova
                                </button>
                            )}
                            {!contract.rinnovoImminente && (
                                <button
                                    className="btn btn-warning btn-sm"
                                    onClick={() => handleRenewalOrClosure(contract.id, 'Chiusura')}
                                >
                                    Chiudi
                                </button>
                            )}
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default RenewalsAndClosures;
