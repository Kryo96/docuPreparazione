// ContractRenewals.js
import React, { useState } from 'react';

const ContractRenewals = () => {
    const [contracts, setContracts] = useState([
        { id: 1, nome: 'Leasing Auto BMW', scadenza: '2025-06-15', rinnovoImminente: true },
        { id: 2, nome: 'Leasing Immobile Milano', scadenza: '2025-07-01', rinnovoImminente: false },
        { id: 3, nome: 'Leasing Macchinario', scadenza: '2025-05-20', rinnovoImminente: true },
    ]);

    const handleRenewal = (id) => {
        setContracts((prevContracts) =>
            prevContracts.map((contract) =>
                contract.id === id ? { ...contract, rinnovoImminente: false } : contract
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
                    <th scope="col">Rinnovo Imminente</th>
                    <th scope="col">Azione</th>
                </tr>
                </thead>
                <tbody>
                {contracts.map((contract) => (
                    <tr key={contract.id}>
                        <td>{contract.nome}</td>
                        <td>{contract.scadenza}</td>
                        <td>
                <span
                    className={`badge bg-${contract.rinnovoImminente ? 'danger' : 'success'} rounded-pill`}
                >
                  {contract.rinnovoImminente ? 'Rinnovo Imminente' : 'Rinnovato'}
                </span>
                        </td>
                        <td>
                            {contract.rinnovoImminente && (
                                <button
                                    className="btn btn-primary btn-sm"
                                    onClick={() => handleRenewal(contract.id)}
                                >
                                    Rinnova
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

export default ContractRenewals;
