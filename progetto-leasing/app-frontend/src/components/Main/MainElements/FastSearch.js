import SearchForm from "../MainElements/SearchForm/SearchForm";

function FastSearch() {
    return (
        <div className="col-md-4">
            <SearchForm
                title="Ricerca Leasing Macchina"
                placeholder="Es. Audi A4, BMW, 36 mesi..."
            />
            <SearchForm
                title="Ricerca Leasing Casa"
                placeholder="Es. Appartamento, Villa, 3 camere..."
            />
        </div>
    );
}

export default FastSearch;