import { useState, useEffect } from "react";

function LeasesList() {
    const [leases, setLeases] = useState([]);
    const [filters, setFilters] = useState({
        cliente: "",
        tipo: "",
        dataInizio: "",
        durata: "",
    });
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchLeases = async () => {
            try {
                const response = await fetch("http://localhost:8080/contracts/approved");
                if (!response.ok) throw new Error("Errore nel caricamento dei contratti");
                const data = await response.json();
                setLeases(data);
            } catch (err) {
                setError(err.message);
            }
        };

        fetchLeases();
    }, []);

    const handleFilterChange = (e) => {
        const { name, value } = e.target;
        setFilters((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const filteredLeases = leases.filter((lease) => {
        return (
            (!filters.cliente || lease.cliente.includes(filters.cliente)) &&
            (!filters.tipo || lease.tipo.includes(filters.tipo)) &&
            (!filters.dataInizio || lease.dataInizio.includes(filters.dataInizio)) &&
            (!filters.durata || lease.durata === parseInt(filters.durata))
        );
    });

    return (
        <div className="container mt-4">
            <h2 className="mb-4">Contratti di Leasing Stipulati</h2>

            {error && <div className="alert alert-danger">{error}</div>}

            <div className="mb-4">
                <input
                    type="text"
                    className="form-control mb-2"
                    placeholder="Filtra per Cliente"
                    name="cliente"
                    value={filters.cliente}
                    onChange={handleFilterChange}
                />
                <input
                    type="text"
                    className="form-control mb-2"
                    placeholder="Filtra per Tipo"
                    name="tipo"
                    value={filters.tipo}
                    onChange={handleFilterChange}
                />
                <input
                    type="date"
                    className="form-control mb-2"
                    placeholder="Filtra per Data Inizio"
                    name="dataInizio"
                    value={filters.dataInizio}
                    onChange={handleFilterChange}
                />
                <input
                    type="number"
                    className="form-control mb-2"
                    placeholder="Filtra per Durata (mesi)"
                    name="durata"
                    value={filters.durata}
                    onChange={handleFilterChange}
                />
            </div>

            {filteredLeases.length === 0 ? (
                <p>Nessun contratto trovato con i criteri selezionati.</p>
            ) : (
                <table className="table table-bordered">
                    <thead className="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Cliente</th>
                        <th>Tipo</th>
                        <th>Dettagli</th>
                        <th>Durata</th>
                        <th>Data Inizio</th>
                    </tr>
                    </thead>
                    <tbody>
                    {filteredLeases.map((lease) => (
                        <tr key={lease.id}>
                            <td>{lease.id}</td>
                            <td>{lease.cliente}</td>
                            <td>{lease.tipo}</td>
                            <td>{lease.tipo === "macchina" ? lease.veicolo : lease.indirizzo}</td>
                            <td>{lease.durata} mesi</td>
                            <td>{lease.dataInizio}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default LeasesList;
