import React, { useState } from 'react';

const AdvancedFilters = () => {
    const [filters, setFilters] = useState({
        stato: '',
        cliente: '',
        importoMin: '',
        importoMax: '',
        dataInizio: '',
        dataFine: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFilters({ ...filters, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Filtri applicati:', filters);
    };

    return (
        <div className="card p-3 mb-4">
            <h5>Filtri Avanzati</h5>
            <form onSubmit={handleSubmit}>
                <div className="row">
                    <div className="col-md-4">
                        <label htmlFor="stato" className="form-label">Stato</label>
                        <select
                            id="stato"
                            name="stato"
                            className="form-select"
                            value={filters.stato}
                            onChange={handleChange}
                        >
                            <option value="">Seleziona Stato</option>
                            <option value="In attesa">In attesa</option>
                            <option value="In elaborazione">In elaborazione</option>
                            <option value="Completato">Completato</option>
                            <option value="Anomalia">Anomalia</option>
                        </select>
                    </div>

                    <div className="col-md-4">
                        <label htmlFor="cliente" className="form-label">Cliente</label>
                        <input
                            type="text"
                            id="cliente"
                            name="cliente"
                            className="form-control"
                            value={filters.cliente}
                            onChange={handleChange}
                            placeholder="Nome Cliente"
                        />
                    </div>

                    <div className="col-md-4">
                        <label htmlFor="importoMin" className="form-label">Importo Minimo</label>
                        <input
                            type="number"
                            id="importoMin"
                            name="importoMin"
                            className="form-control"
                            value={filters.importoMin}
                            onChange={handleChange}
                            placeholder="Importo Minimo"
                        />
                    </div>

                    <div className="col-md-4 mt-3">
                        <label htmlFor="importoMax" className="form-label">Importo Massimo</label>
                        <input
                            type="number"
                            id="importoMax"
                            name="importoMax"
                            className="form-control"
                            value={filters.importoMax}
                            onChange={handleChange}
                            placeholder="Importo Massimo"
                        />
                    </div>

                    <div className="col-md-4 mt-3">
                        <label htmlFor="dataInizio" className="form-label">Data Inizio</label>
                        <input
                            type="date"
                            id="dataInizio"
                            name="dataInizio"
                            className="form-control"
                            value={filters.dataInizio}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="col-md-4 mt-3">
                        <label htmlFor="dataFine" className="form-label">Data Fine</label>
                        <input
                            type="date"
                            id="dataFine"
                            name="dataFine"
                            className="form-control"
                            value={filters.dataFine}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="col-12 mt-3">
                        <button type="submit" className="btn btn-primary w-100">
                            Applica Filtri
                        </button>
                    </div>
                </div>
            </form>
        </div>
    );
};

export default AdvancedFilters;
