import React from 'react';

const FinancialWeatherWidget = () => {
    const trends = [
        { title: 'Tasso d\'interesse', value: '3.5%', trend: 'In aumento' },
        { title: 'Inflazione', value: '1.2%', trend: 'Stabile' },
    ];

    return (
        <div className="card p-3 mb-4">
            <h5>Metereologico Finanziario</h5>
            <ul className="list-group">
                {trends.map((item, index) => (
                    <li key={index} className="list-group-item">
                        <strong>{item.title}:</strong> {item.value} - {item.trend}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default FinancialWeatherWidget;
