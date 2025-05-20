import React from 'react';

const ProgressBarComponent = () => {
    const progress = 60;

    return (
        <div className="card p-3 mb-4">
            <h5>Stato del Contratto</h5>

            <div className="progress mb-2">
                <div
                    className="progress-bar"
                    role="progressbar"
                    style={{ width: `${progress}%` }}
                    aria-valuenow={progress}
                    aria-valuemin="0"
                    aria-valuemax="100"
                >
                    {progress}%
                </div>
            </div>

            <p>Stato: In fase di approvazione</p>
        </div>
    );
};

export default ProgressBarComponent;
