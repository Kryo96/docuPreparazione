import { useState } from "react";

function RegistrationForm() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [name, setName] = useState("");

    function handleSubmit(e) {
        e.preventDefault();

        fetch("http://localhost:8080/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: username,
                password: password,
                email: email,
                name: name
            })
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Registrazione fallita");
                }
                return response.json();
            })
            .then((data) => {
                console.log("Registrazione riuscita:", data);
            })
            .catch((error) => {
                console.error("Errore nella registrazione:", error);
            });
    }

    return (
        <div className="row justify-content-center align-items-center">
            <div className="col-md-6">
                <h1 className="mb-4">Registrazione Utente</h1>
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label htmlFor="name" className="form-label">Nome</label>
                        <input
                            type="text"
                            className="form-control"
                            id="name"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label htmlFor="email" className="form-label">Email</label>
                        <input
                            type="email"
                            className="form-control"
                            id="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label htmlFor="username" className="form-label">Username</label>
                        <input
                            type="text"
                            className="form-control"
                            id="username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
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
                            required
                        />
                    </div>

                    <button type="submit" className="btn btn-success w-100">Registrati</button>
                </form>

                <p className="mt-3">
                    Hai già un account? <a href="/login">Accedi</a>
                </p>
            </div>
        </div>
    );
}

export default RegistrationForm;
