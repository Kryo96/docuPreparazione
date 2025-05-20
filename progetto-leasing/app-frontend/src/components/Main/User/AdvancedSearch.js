import React from 'react';

const AdvancedSearch = () => {
    return (
        <div className="card p-3 mb-4">
            <h5>Cerca Contratti di Leasing</h5>
            <input type="text" className="form-control" placeholder="Cerca per nome, stato, data..." />
        </div>
    );
};

export default AdvancedSearch;
