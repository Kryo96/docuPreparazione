// PaymentLineChart.js
import React from 'react';
import {
    LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer
} from 'recharts';

const data = [
    { month: 'Gen', amount: 500 },
    { month: 'Feb', amount: 700 },
    { month: 'Mar', amount: 600 },
    { month: 'Apr', amount: 800 },
    { month: 'Mag', amount: 1000 },
];

const PaymentLineChart = () => {
    return (
        <div className="card p-3 mb-4">
            <h5>Andamento Pagamenti</h5>
            <ResponsiveContainer width="100%" height={300}>
                <LineChart data={data}>
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="month" />
                    <YAxis />
                    <Tooltip />
                    <Legend />
                    <Line type="monotone" dataKey="amount" stroke="#0d6efd" strokeWidth={2} />
                </LineChart>
            </ResponsiveContainer>
        </div>
    );
};

export default PaymentLineChart;
