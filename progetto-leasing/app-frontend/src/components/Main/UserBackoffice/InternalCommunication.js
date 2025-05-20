import React, { useState } from 'react';

const InternalCommunication = () => {
    const [message, setMessage] = useState('');
    const [messages, setMessages] = useState([
        { id: 1, sender: 'Luca B.', content: 'Verifica pratica 123.' },
        { id: 2, sender: 'Giulia M.', content: 'Aggiunta documento richiesta.' },
    ]);

    const handleSendMessage = () => {
        if (message.trim()) {
            setMessages([...messages, { id: messages.length + 1, sender: 'Operatore', content: message }]);
            setMessage('');
        }
    };

    return (
        <div className="card p-3 mb-4">
            <h5>Comunicazione Interna</h5>
            <div className="list-group mb-3" style={{ maxHeight: '200px', overflowY: 'auto' }}>
                {messages.map((msg) => (
                    <div key={msg.id} className="list-group-item">
                        <strong>{msg.sender}:</strong> {msg.content}
                    </div>
                ))}
            </div>
            <div className="input-group">
                <input
                    type="text"
                    className="form-control"
                    placeholder="Scrivi un messaggio..."
                    value={message}
                    onChange={(e) => setMessage(e.target.value)}
                />
                <button className="btn btn-primary" onClick={handleSendMessage}>
                    Invia
                </button>
            </div>
        </div>
    );
};

export default InternalCommunication;
