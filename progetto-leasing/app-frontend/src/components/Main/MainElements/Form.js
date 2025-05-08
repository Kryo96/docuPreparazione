function Form() {
    return (
        <div className="col-md-8">
            <h1 className="mb-4">Login Utente</h1>
            <form onSubmit={(e) => {
                e.preventDefault();
                // logica di login da aggiungere qui
                console.log("Login inviato");
            }}>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">Email</label>
                    <input
                        type="email"
                        className="form-control"
                        id="email"
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