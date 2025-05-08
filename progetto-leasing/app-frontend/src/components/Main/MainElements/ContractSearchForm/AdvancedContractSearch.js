import { useState } from "react";

function AdvancedContractSearch() {
    const [tipoOggetto, setTipoOggetto] = useState("macchina");
    const [formData, setFormData] = useState({
        cliente: "",
        targa: "",
        veicolo: "",
        durata: "",
        dataInizio: "",
        indirizzo: "",
        metratura: ""
    });
    const [results, setResults] = useState([]);
    const [error, setError] = useState(null);

    function handleChange(e) {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    }

    function handleSubmit(e) {
        e.preventDefault();

        // Filtra i dati in base al tipo selezionato
        const filteredData =
            tipoOggetto === "macchina"
                ? {
                    tipo: "macchina",
                    cliente: formData.cliente,
                    targa: formData.targa,
                    veicolo: formData.veicolo,
                    durata: formData.durata,
                    dataInizio: formData.dataInizio
                }
                : {
                    tipo: "casa",
                    cliente: formData.cliente,
                    indirizzo: formData.indirizzo,
                    metratura: formData.metratura,
                    durata: formData.durata,
                    dataInizio: formData.dataInizio
                };

        fetch("http://localhost:8080/contracts/advanced-search", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(filteredData)
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
            <h2 className="mb-4">Ricerca Avanzata Contratti</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label className="form-label">Tipo oggetto</label>
                    <select
                        className="form-select"
                        value={tipoOggetto}
                        onChange={(e) => setTipoOggetto(e.target.value)}
                    >
                        <option value="macchina">Macchina</option>
                        <option value="casa">Casa</option>
                    </select>
                </div>

                <div className="mb-3">
                    <input
                        type="text"
                        name="cliente"
                        className="form-control"
                        placeholder="Nome cliente"
                        value={formData.cliente}
                        onChange={handleChange}
                    />
                </div>

                {tipoOggetto === "macchina" && (
                    <>
                        <div className="mb-3">
                            <input
                                type="text"
                                name="targa"
                                className="form-control"
                                placeholder="Targa"
                                value={formData.targa}
                                onChange={handleChange}
                            />
                        </div>
                        <div className="mb-3">
                            <input
                                type="text"
                                name="veicolo"
                                className="form-control"
                                placeholder="Modello veicolo"
                                value={formData.veicolo}
                                onChange={handleChange}
                            />
                        </div>
                    </>
                )}

                {tipoOggetto === "casa" && (
                    <>
                        <div className="mb-3">
                            <input
                                type="text"
                                name="indirizzo"
                                className="form-control"
                                placeholder="Indirizzo immobile"
                                value={formData.indirizzo}
                                onChange={handleChange}
                            />
                        </div>
                        <div className="mb-3">
                            <input
                                type="number"
                                name="metratura"
                                className="form-control"
                                placeholder="Metratura (mq)"
                                value={formData.metratura}
                                onChange={handleChange}
                            />
                        </div>
                    </>
                )}

                <div className="mb-3">
                    <input
                        type="number"
                        name="durata"
                        className="form-control"
                        placeholder="Durata (mesi)"
                        value={formData.durata}
                        onChange={handleChange}
                    />
                </div>

                <div className="mb-3">
                    <input
                        type="date"
                        name="dataInizio"
                        className="form-control"
                        value={formData.dataInizio}
                        onChange={handleChange}
                    />
                </div>

                <button type="submit" className="btn btn-primary w-100">
                    Cerca
                </button>
            </form>

            {error && <div className="alert alert-danger mt-3">{error}</div>}

            {results.length > 0 && (
                <ul className="list-group mt-4">
                    {results.map((contratto, idx) => (
                        <li className="list-group-item" key={idx}>
                            <strong>ID:</strong> {contratto.id} –{" "}
                            <strong>Cliente:</strong> {contratto.cliente} –{" "}
                            <strong>Tipo:</strong> {contratto.tipo}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default AdvancedContractSearch;
