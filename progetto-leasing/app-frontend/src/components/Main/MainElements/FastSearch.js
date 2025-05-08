function FastSearch(props) {
    return (
        <div className="col-md-4">
            <div className="p-3 bg-light rounded">
                <h4>Ricerca Leasing Maccina</h4>
                <form onSubmit={(e) => {
                    e.preventDefault();
                    // gestisci la ricerca qui
                    console.log("Ricerca inviata");
                }}>
                    <div className="mb-3">
                        <label htmlFor="searchTerm" className="form-label">Parola chiave</label>
                        <input
                            type="text"
                            className="form-control"
                            id="searchTerm"
                            placeholder="Es. Audi A4, BMW, 36 mesi..."
                        />
                    </div>
                    <button type="submit" className="btn btn-primary w-100">
                        Cerca
                    </button>
                </form>
            </div>

            <div className="p-3 bg-light rounded">
                <h4>Ricerca Leasing Casa</h4>
                <form onSubmit={(e) => {
                    e.preventDefault();
                    // gestisci la ricerca qui
                    console.log("Ricerca inviata");
                }}>
                    <div className="mb-3">
                        <label htmlFor="searchTerm" className="form-label">Parola chiave</label>
                        <input
                            type="text"
                            className="form-control"
                            id="searchTerm"
                            placeholder="Es. Audi A4, BMW, 36 mesi..."
                        />
                    </div>
                    <button type="submit" className="btn btn-primary w-100">
                        Cerca
                    </button>
                </form>
            </div>
        </div>
    )
}

export default FastSearch;