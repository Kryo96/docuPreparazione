import { useState } from "react";
import HttpError from "../../../../Errors/HttpError";

function SearchForm({ title, placeholder }) {
    const [searchTerm, setSearchTerm] = useState("");
    const [searchType, setSearchType] = useState("clients");
    const [error, setError] = useState({
        show: false,
        message: '',
        type: 'network'
    });

    function dismissError() {
        setError({...error, show: false});
    }

    async function handleSubmit(e) {
        e.preventDefault();

        try{
            const response = await fetch("http://localhost:8080/clients", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    searchTerm: searchTerm,
                    searchType: searchType,
                })
            });

            const data = await response.json();

            if(!response.ok) {
                setError({
                    show: true,
                    message: data.detail || "Errore server",
                    type: "network"
                })
            }

        }catch(err){
            setError({
                show: true,
                message: err.message || "Errore di rete",
                type: "network"
            })
        }
    }

    return (
        <>

            <HttpError
                message={error.message}
                type={error.type}
                show={error.show}
                onDismiss={dismissError}
                autoHide={true}
            />

            <div className="p-3 bg-light rounded mb-3">
                <h4>{title}</h4>
                <div className="btn-group mb-3 w-100" role="group">
                    <button
                        type="button"
                        className={`btn btn-outline-primary ${searchType === "clients" ? "active" : ""}`}
                        onClick={() => setSearchType("clients")}
                    >
                        Utenti
                    </button>
                    <button
                        type="button"
                        className={`btn btn-outline-primary ${searchType === "leasing active" ? "active" : ""}`}
                        onClick={() => setSearchType("leasing")}
                    >
                        Utenti leasing
                    </button>
                    <button
                        type="button"
                        className={`btn btn-outline-primary ${searchType === "leasing expired" ? "active" : ""}`}
                        onClick={() => setSearchType("expired")}
                    >
                        Utenti leasing scaduto
                    </button>
                </div>

                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label htmlFor="searchTerm" className="form-label">utente o email</label>
                        <input
                            type="text"
                            className="form-control"
                            id="searchTerm"
                            value={searchTerm}
                            onChange={(e) => setSearchTerm(e.target.value)}
                            placeholder={placeholder}
                        />
                    </div>
                    <button type="submit" className="btn btn-primary w-100">
                        Cerca
                    </button>
                </form>
            </div>
        </>
    );
}

export default SearchForm;
