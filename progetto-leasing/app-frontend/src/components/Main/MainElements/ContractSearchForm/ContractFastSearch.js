import { useState } from "react";

function ContractFastSearch() {
    const [query, setQuery] = useState("");
    const [results, setResults] = useState([]);
    const [error, setError] = useState(null);

    function handleSubmit(e) {
        e.preventDefault();

        fetch(`http://localhost:8080/contracts/search?q=${encodeURIComponent(query)}`)
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
            <h2 className="mb-3">Cerca Contratti di Leasing</h2>
            <form onSubmit={handleSubmit} className="mb-4">
                <div className="input-group">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Inserisci nome, targa o ID contratto"
                        value={query}
                        onChange={(e) => setQuery(e.target.value)}
                        required
                    />
                    <button className="btn btn-primary" type="submit">
                        Cerca
                    </button>
                </div>
            </form>

            {error && <div className="alert alert-danger">{error}</div>}

            {results.length > 0 && (
                <ul className="list-group">
                    {results.map((contract, index) => (
                        <li className="list-group-item" key={index}>
                            <strong>ID:</strong> {contract.id} – <strong>Cliente:</strong> {contract.cliente} – <strong>Veicolo:</strong> {contract.veicolo}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default ContractFastSearch;
