import { useState } from "react";

function AdvancedCarContractSearch() {
    const [cliente, setCliente] = useState("");
    const [veicolo, setVeicolo] = useState("");
    const [targa, setTarga] = useState("");
    const [durata, setDurata] = useState("");
    const [dataInizio, setDataInizio] = useState("");
    const [results, setResults] = useState([]);
    const [error, setError] = useState(null);

    function handleSubmit(e) {
        e.preventDefault();

        const searchParams = {
            cliente,
            veicolo,
            targa,
            durata,
            dataInizio
        };

        fetch("http://localhost:8080/contracts/advanced-search", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(searchParams)
        })
            .then((res) => {
                if (!res.ok) throw new Error("Errore nella ricerca");
                return res.json();
            })
            .then((data) => {
                setResults(data);
                setError(null);
            })
            .catch((err) => {
                setError(err.message);
                setResults([]);
            });
    }

    return (
        <div className="container mt-4">
            <h2 className="mb-4">Ricerca Avanzata Contratti Macchina</h2>
            <form onSubmit={handleSubmit}>
                <div className="row g-3">
                    <div className="col-md-6">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Nome cliente"
                            value={cliente}
                            onChange={(e) => setCliente(e.target.value)}
                        />
                    </div>
                    <div className="col-md-6">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Veicolo"
                            value={veicolo}
                            onChange={(e) => setVeicolo(e.target.value)}
                        />
                    </div>
                    <div className="col-md-4">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Targa"
                            value={targa}
                            onChange={(e) => setTarga(e.target.value)}
                        />
                    </div>
                    <div className="col-md-4">
                        <input
                            type="number"
                            className="form-control"
                            placeholder="Durata (mesi)"
                            value={durata}
                            onChange={(e) => setDurata(e.target.value)}
                        />
                    </div>
                    <div className="col-md-4">
                        <input
                            type="date"
                            className="form-control"
                            placeholder="Data inizio"
                            value={dataInizio}
                            onChange={(e) => setDataInizio(e.target.value)}
                        />
                    </div>
                </div>

                <button type="submit" className="btn btn-primary w-100 mt-4">
                    Cerca
                </button>
            </form>

            {error && <div className="alert alert-danger mt-4">{error}</div>}

            {results.length > 0 && (
                <ul className="list-group mt-4">
                    {results.map((contract, idx) => (
                        <li className="list-group-item" key={idx}>
                            <strong>ID:</strong> {contract.id} – <strong>Cliente:</strong> {contract.cliente} – <strong>Veicolo:</strong> {contract.veicolo} – <strong>Targa:</strong> {contract.targa}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default AdvancedCarContractSearch;
