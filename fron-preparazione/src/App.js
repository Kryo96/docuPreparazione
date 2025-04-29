import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import RegisterComponent from './RegisterComponent';
import LoginComponent from './LoginComponent';

const App = () => {
    return (
        <Router>
            <div className="App">
                <Routes>
                    <Route path="/" element={<LoginComponent />} />
                    <Route path="/register" element={<RegisterComponent />} />
                    <Route path="/login" element={<LoginComponent />} />
                </Routes>
            </div>
        </Router>
    );
};

export default App;
