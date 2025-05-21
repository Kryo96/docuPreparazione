import React, { useState } from 'react';

const ProductSearch = () => {
    const [query, setQuery] = useState({
        name: '',
        model: '',
        organization: '',
        agency: ''
    });
    const [results, setResults] = useState([]);
    const [loading, setLoading] = useState(false);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setQuery((prevQuery) => ({
            ...prevQuery,
            [name]: value
        }));
    };

    const searchProducts = async () => {
        setLoading(true);
        try {
            const queryString = new URLSearchParams(
                Object.entries(query).filter(([_, value]) => value)
            ).toString();

            const response = await fetch(`http://localhost:8080/products/search?${queryString}`);
            const data = await response.json();
            setResults(data);
        } catch (error) {
            console.error('Error fetching products:', error);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="container mt-5">
            <h2 className="mb-4">Search Products</h2>

            {/* Form for searching */}
            <div className="mb-3">
                <div className="row">
                    <div className="col-md-3">
                        <input
                            type="text"
                            className="form-control"
                            name="name"
                            placeholder="Product Name"
                            value={query.name}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="col-md-3">
                        <input
                            type="text"
                            className="form-control"
                            name="model"
                            placeholder="Model"
                            value={query.model}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="col-md-3">
                        <input
                            type="text"
                            className="form-control"
                            name="organization"
                            placeholder="Organization"
                            value={query.organization}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="col-md-3">
                        <input
                            type="text"
                            className="form-control"
                            name="agency"
                            placeholder="Agency"
                            value={query.agency}
                            onChange={handleInputChange}
                        />
                    </div>
                </div>
                <button
                    className="btn btn-primary mt-3"
                    onClick={searchProducts}
                    disabled={loading}
                >
                    {loading ? 'Searching...' : 'Search'}
                </button>
            </div>

            {/* Loading indicator */}
            {loading && <p>Loading...</p>}

            {/* Table of results */}
            <div className="table-responsive mt-4">
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Model</th>
                        <th>Organization</th>
                        <th>Agency</th>
                        <th>Contact Info</th>
                    </tr>
                    </thead>
                    <tbody>
                    {results.length > 0 ? (
                        results.map((product) => (
                            <tr key={product.id}>
                                <td>{product.name}</td>
                                <td>{product.model}</td>
                                <td>{product.organization}</td>
                                <td>{product.agency}</td>
                                <td>{product.contact_info}</td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="5" className="text-center">No products found</td>
                        </tr>
                    )}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default ProductSearch;
