import {useState} from "react";

function SearchForm({ title, placeholder }) {

    const [searchTerm, setSearchTerm] = useState("");

    function handleSubmit(e) {
        e.preventDefault();

        fetch("http://localhost:8080/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                searchTerm: searchTerm,
            })
        })
        .then((response) => {
            if (!response.ok) {
                throw new Error("Login fallito");
            }
            return response.json();
        })
        .then((data) => {
            console.log("Login riuscito:", data);
        })
        .catch((error) => {
            console.error("Errore nel login:", error);
        });
    }

    return (
        <div className="p-3 bg-light rounded mb-3">
            <h4>{title}</h4>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="searchTerm" className="form-label">Parola chiave</label>
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
    );
}
export default SearchForm;