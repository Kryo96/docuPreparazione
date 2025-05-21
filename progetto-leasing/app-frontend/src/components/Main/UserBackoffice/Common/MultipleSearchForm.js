import axios from "axios";
import { useState } from "react";
import HttpError from "../../../../Errors/HttpError";


function SearchForm({ title, placeholder }) {
    const [searchTerm, setSearchTerm] = useState("");
    const [searchType, setSearchType] = useState("clients");
    const [data, setData] = useState({});

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

        const queryParams = new URLSearchParams({
            term: searchTerm
        }).toString();

        const url = `http://localhost:8080/clients/search?${queryParams}`;

        try {
            const response = await axios.get(url);
            console.log(response);
            setData(response.data);

        } catch (err) {
            const message =
                err.response?.data?.detail ||
                err.response?.data?.message ||
                err.message ||
                "Errore di rete";
            setError({
                show: true,
                message,
                type: "network"
            });
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

                { data.length > 0 && (
                    <h1>Ci sono i dati</h1>
                    )
                }
            </div>
        </>
    );
}

export default SearchForm;
