import {useState} from "react";


function Form() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    function handleSubmit(e) {
        e.preventDefault();

        fetch("http://localhost:8080/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: username,
                password: password
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
                // qui puoi salvare il token, fare redirect, ecc.
            })
            .catch((error) => {
                console.error("Errore nel login:", error);
            });
    }

    return (
        <div className="col-md-8">
            <h1 className="mb-4">Login Utente</h1>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">Email</label>
                    <input
                        type="email"
                        className="form-control"
                        id="email"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        placeholder="nome@esempio.com"
                        required
                    />
                </div>

                <div className="mb-3">
                    <label htmlFor="password" className="form-label">Password</label>
                    <input
                        type="password"
                        className="form-control"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder="Password"
                        required
                    />
                </div>

                <button type="submit" className="btn btn-primary w-100">Accedi</button>
            </form>

            <p className="mt-3">
                Non hai un account? <a href="/registrazione">Registrati</a>
            </p>
        </div>
    )
}

export default Form;