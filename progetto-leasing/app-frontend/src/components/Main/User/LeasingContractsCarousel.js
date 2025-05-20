import React from 'react';

const LeasingContractsCarousel = () => {
    const contracts = [
        { name: 'Leasing Auto - BMW', status: 'Attivo', dueDate: '12 Giugno 2025' },
        { name: 'Leasing Casa - Milano', status: 'In Ritardo', dueDate: '20 Maggio 2025' },
    ];

    return (
        <div id="leasingContractsCarousel" className="carousel slide" data-bs-ride="carousel">
            <div className="carousel-inner">
                {contracts.map((contract, index) => (
                    <div key={index} className={`carousel-item ${index === 0 ? 'active' : ''}`}>
                        <div className="card p-3">
                            <h5>{contract.name}</h5>
                            <p>Status: {contract.status}</p>
                            <p>Prossima Scadenza: {contract.dueDate}</p>
                        </div>
                    </div>
                ))}
            </div>
            <button className="carousel-control-prev" type="button" data-bs-target="#leasingContractsCarousel" data-bs-slide="prev">
                <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                <span className="visually-hidden">Previous</span>
            </button>
            <button className="carousel-control-next" type="button" data-bs-target="#leasingContractsCarousel" data-bs-slide="next">
                <span className="carousel-control-next-icon" aria-hidden="true"></span>
                <span className="visually-hidden">Next</span>
            </button>
        </div>
    );
};

export default LeasingContractsCarousel;
