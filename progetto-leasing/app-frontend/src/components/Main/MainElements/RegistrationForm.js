import { useState } from "react";
import {useNavigate} from "react-router-dom";
import { useAuth } from "../../../provider/AuthProvider";
import RegistrationError from "../../../Errors/RegistrationError";

function RegistrationForm() {
    const { setToken } = useAuth();
    const navigate = useNavigate();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [name, setName] = useState("");

    const [error, setError] = useState({
        show: false,
        message: '',
        type: 'auth',
    });

    function dismissError() {
        setError({ ...error, show: false });
    }

    const handleWhoIsInput = (e, callback) => {
        e.preventDefault();

        const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        const usernameRegex = /^[a-zA-Z0-9_]{3,}$/;
        const passwordRegex = /^.*(?=.{8,})/;

        console.log(email.length)
        if (email.includes('@') && email.includes('.') || email.length === 0) {

            if (!emailRegex.test(email)) {

                setError({
                    show: true,
                    message: "Email non valida!",
                    type: 'validation',
                });
                return;
            }
        }


        if (!usernameRegex.test(username)) {
            setError({
                show: true,
                message: "Username non valido!",
                type: 'validation',
            });
            return;
        }


        if (!passwordRegex.test(password)) {
            setError({
                show: true,
                message: "Password non valida!",
                type: 'validation',
            });
            return;
        }

        callback(e);
    };

    function handleSubmit(e) {
        e.preventDefault();

        fetch("http://localhost:8080/auth/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                username,
                password,
                email,
                name,
            }),
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Registrazione fallita");
                }
                return response.json();
            })
            .then((data) => {
                if (data.token) {
                    setToken(data.token);
                    window.location.href = "/";
                }
            })
            .catch((error) => {
                setError({
                    show: true,
                    message: error.message || "Errore nella registrazione",
                    type: "auth",
                });
            });
    }

    return (
        <>
            <div className="p-0">
                <RegistrationError
                    show={error.show}
                    message={error.message}
                    type={error.type}
                    onDismiss={dismissError}
                    autoHide={true}
                />
            </div>

            <div className="col-md-8 p-3">
                <form onSubmit={(event) => handleWhoIsInput(event, handleSubmit)}>
                    <div className="mb-3">
                        <label htmlFor="name" className="form-label">Nome</label>
                        <input
                            type="text"
                            className="form-control"
                            id="name"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            placeholder="Nome completo"
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
                            placeholder="nome@esempio.com"
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
                            placeholder="Nome utente"
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
                        />
                    </div>

                    <button type="submit" className="btn btn-primary w-100">Registrati</button>
                </form>

                <p className="mt-3">
                    Hai già un account? <a href="/login">Accedi</a>
                </p>
            </div>
        </>
    );
}

export default RegistrationForm;
