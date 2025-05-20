import React, { useState } from 'react';

const SupplierMonitoring = () => {
    const [suppliers, setSuppliers] = useState([
        { id: 1, nome: 'Fornitore A', asset: 'Auto', stato: 'Attivo', scadenza: '2025-06-15' },
        { id: 2, nome: 'Fornitore B', asset: 'Macchinario', stato: 'In Ritardo', scadenza: '2025-05-30' },
        { id: 3, nome: 'Fornitore C', asset: 'Immobile', stato: 'Attivo', scadenza: '2025-07-01' },
    ]);

    const handleStatusChange = (id, nuovoStato) => {
        setSuppliers((prevSuppliers) =>
            prevSuppliers.map((supplier) =>
                supplier.id === id ? { ...supplier, stato: nuovoStato } : supplier
            )
        );
    };

    return (
        <div className="card p-3 mb-4">
            <h5>Monitoraggio Fornitori/Asset in Leasing</h5>
            <table className="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Fornitore</th>
                    <th scope="col">Asset</th>
                    <th scope="col">Stato</th>
                    <th scope="col">Data Scadenza</th>
                    <th scope="col">Azione</th>
                </tr>
                </thead>
                <tbody>
                {suppliers.map((supplier) => (
                    <tr key={supplier.id}>
                        <td>{supplier.nome}</td>
                        <td>{supplier.asset}</td>
                        <td>
                <span className={`badge bg-${getSupplierBadge(supplier.stato)} rounded-pill`}>
                  {supplier.stato}
                </span>
                        </td>
                        <td>{supplier.scadenza}</td>
                        <td>
                            {supplier.stato === 'In Ritardo' && (
                                <button
                                    className="btn btn-primary btn-sm"
                                    onClick={() => handleStatusChange(supplier.id, 'Attivo')}
                                >
                                    Attiva
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

// Funzione per determinare il badge di stato
function getSupplierBadge(stato) {
    switch (stato) {
        case 'Attivo':
            return 'success';
        case 'In Ritardo':
            return 'danger';
        default:
            return 'secondary';
    }
}

export default SupplierMonitoring;
