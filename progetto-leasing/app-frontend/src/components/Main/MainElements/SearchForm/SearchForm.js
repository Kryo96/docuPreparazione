function SearchForm({ title, placeholder }) {
    function handleSubmit(e) {
        e.preventDefault();
        console.log("Ricerca inviata per:", placeholder);
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