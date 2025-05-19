import {useState} from "react";
import {useNavigate} from "react-router-dom";
import {useAuth} from "../../../provider/AuthProvider";
import LoginError from "../../../Errors/LoginError";

function LoginForm() {
    const { setToken } = useAuth();
    const navigate = useNavigate();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState({
        show: false,
        message: '',
        type: 'auth'
    });

    function dismissError() {
        setError({...error, show: false});
    }

    async function handleSubmit(e) {
        e.preventDefault();

        try {
            const response = await fetch("http://localhost:8080/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    username,
                    password,
                }),
            });

            const data = await response.json();

            if (!response.ok) {
                setError({
                    show: true,
                    message: data.detail || "Errore di autenticazione",
                    type: "auth",
                });
                return;
            }

            setToken(data.token);
            navigate("/", { replace: true });

        } catch (error) {
            setError({
                show: true,
                message: error.message || "Errore di rete",
                type: "auth",
            });
        }
    }

    function handleWhoIsInput(e, callback){
        e.preventDefault();
        const emailregex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        const usernameRegex = /^[a-zA-Z0-9_]{3,}$/;
        const passwordRegex = /^.*(?=.{8,})/;

        if (username.includes('@') && username.includes('.')) {
            if (!emailregex.test(username)) {
                setError({
                    show: true,
                    message: "Email non valida!",
                    type: 'validation'
                })
                return
            }
        } else {
            if(!usernameRegex.test(username)) {
                setError({
                    show: true,
                    message: "Username non valida!",
                    type: 'validation'
                })
                return
            }
        }

        if(!passwordRegex.test(password)) {
            setError({
                show: true,
                message: "Password non valida!",
                type: 'validation'
            })
            return
        }

        callback(e);
    }

    return (
        <>
        <div className="p-0">
            <LoginError
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
                    <label htmlFor="email" className="form-label">Email</label>
                    <input
                        className="form-control"
                        id="email"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        placeholder="nome@esempio.com"
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

                <button type="submit" className="btn btn-primary w-100">Accedi</button>
            </form>

            <p className="mt-3">
                Non hai un account? <a href="/registrazione">Registrati</a>
            </p>
        </div>
        </>
    )
}

export default LoginForm;