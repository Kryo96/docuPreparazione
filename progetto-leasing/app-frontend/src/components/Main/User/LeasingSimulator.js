import React, { useState } from 'react';

const LeasingSimulator = () => {
    const [amount, setAmount] = useState(0);
    const [duration, setDuration] = useState(12);
    const [interestRate, setInterestRate] = useState(3.5);
    const [monthlyPayment, setMonthlyPayment] = useState(0);

    const calculatePayment = () => {
        const interest = (amount * (interestRate / 100)) * (duration / 12);
        const totalAmount = amount + interest;
        const payment = totalAmount / duration;
        setMonthlyPayment(payment.toFixed(2));
    };

    return (
        <div className="card p-3 mb-4">
            <h5>Simulazione Leasing</h5>
            <div className="mb-3">
                <label>Importo del Leasing (€):</label>
                <input type="number" className="form-control" value={amount} onChange={(e) => setAmount(e.target.value)} />
            </div>
            <div className="mb-3">
                <label>Durata (mesi):</label>
                <input type="number" className="form-control" value={duration} onChange={(e) => setDuration(e.target.value)} />
            </div>
            <div className="mb-3">
                <label>Tasso di Interesse (%):</label>
                <input type="number" className="form-control" value={interestRate} onChange={(e) => setInterestRate(e.target.value)} />
            </div>
            <button className="btn btn-primary" onClick={calculatePayment}>Calcola</button>
            {monthlyPayment > 0 && (
                <div className="mt-3">
                    <h5>Pagamento Mensile: €{monthlyPayment}</h5>
                </div>
            )}
        </div>
    );
};

export default LeasingSimulator;
