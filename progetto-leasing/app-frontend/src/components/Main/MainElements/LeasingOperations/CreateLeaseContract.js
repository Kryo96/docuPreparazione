import { useState } from "react";

function CreateLeaseContract() {
    const [tipo, setTipo] = useState("macchina");
    const [cliente, setCliente] = useState("");
    const [durata, setDurata] = useState("");
    const [dataInizio, setDataInizio] = useState("");
    const [veicolo, setVeicolo] = useState("");
    const [targa, setTarga] = useState("");
    const [indirizzo, setIndirizzo] = useState("");
    const [metratura, setMetratura] = useState("");
    const [message, setMessage] = useState("");

    function handleSubmit(e) {
        e.preventDefault();

        const contractData = {
            tipo,
            cliente,
            durata,
            dataInizio,
            ...(tipo === "macchina" ? { veicolo, targa } : { indirizzo, metratura }),
        };

        fetch("http://localhost:8080/contracts", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(contractData),
        })
            .then((res) => {
                if (!res.ok) throw new Error("Errore nella creazione del contratto");
                return res.json();
            })
            .then((data) => {
                setMessage(`Contratto creato con successo (ID: ${data.id})`);
            })
            .catch((err) => {
                setMessage(`Errore: ${err.message}`);
            });
    }

    return (
        <div className="container mt-4">
            <h2 className="mb-4">Crea Nuovo Contratto di Leasing</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label className="form-label">Tipo Contratto</label>
                    <select className="form-select" value={tipo} onChange={(e) => setTipo(e.target.value)}>
                        <option value="macchina">Macchina</option>
                        <option value="casa">Casa</option>
                    </select>
                </div>

                <div className="mb-3">
                    <label className="form-label">Cliente</label>
                    <input
                        type="text"
                        className="form-control"
                        value={cliente}
                        onChange={(e) => setCliente(e.target.value)}
                        required
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label">Durata (mesi)</label>
                    <input
                        type="number"
                        className="form-control"
                        value={durata}
                        onChange={(e) => setDurata(e.target.value)}
                        required
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label">Data Inizio</label>
                    <input
                        type="date"
                        className="form-control"
                        value={dataInizio}
                        onChange={(e) => setDataInizio(e.target.value)}
                        required
                    />
                </div>

                {tipo === "macchina" ? (
                    <>
                        <div className="mb-3">
                            <label className="form-label">Veicolo</label>
                            <input
                                type="text"
                                className="form-control"
                                value={veicolo}
                                onChange={(e) => setVeicolo(e.target.value)}
                                required
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Targa</label>
                            <input
                                type="text"
                                className="form-control"
                                value={targa}
                                onChange={(e) => setTarga(e.target.value)}
                                required
                            />
                        </div>
                    </>
                ) : (
                    <>
                        <div className="mb-3">
                            <label className="form-label">Indirizzo</label>
                            <input
                                type="text"
                                className="form-control"
                                value={indirizzo}
                                onChange={(e) => setIndirizzo(e.target.value)}
                                required
                            />
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Metratura (mq)</label>
                            <input
                                type="number"
                                className="form-control"
                                value={metratura}
                                onChange={(e) => setMetratura(e.target.value)}
                                required
                            />
                        </div>
                    </>
                )}

                <button type="submit" className="btn btn-primary w-100">Crea Contratto</button>
            </form>

            {message && <div className="alert alert-info mt-3">{message}</div>}
        </div>
    );
}

export default CreateLeaseContract;
