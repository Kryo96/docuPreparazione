import React from 'react';

const CustomOffers = () => {
    return (
        <div className="card p-3 mb-4">
            <h5>Offerte Personalizzate</h5>
            <div className="d-flex gap-3">
                <div className="card" style={{ width: '18rem' }}>
                    <img src="offer1.jpg" className="card-img-top" alt="Offerta 1" />
                    <div className="card-body">
                        <h5 className="card-title">Leasing Auto - Offerta Speciale</h5>
                        <p className="card-text">Sconto del 5% sul leasing auto</p>
                        <button className="btn btn-primary">Scopri Offerta</button>
                    </div>
                </div>
                <div className="card" style={{ width: '18rem' }}>
                    <img src="offer2.jpg" className="card-img-top" alt="Offerta 2" />
                    <div className="card-body">
                        <h5 className="card-title">Leasing Moto - Tasso Sconto</h5>
                        <p className="card-text">Tasso ridotto al 2.8% per leasing moto</p>
                        <button className="btn btn-primary">Scopri Offerta</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default CustomOffers;
