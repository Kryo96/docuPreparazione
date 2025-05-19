import {useState, useEffect} from "react";
import {FiAlertOctagon, FiCloudOff} from 'react-icons/fi'
const LoginError = ({
                        message= "Si è verificato un errore durante il login",
                        type = "auth",
                        show = false,
                        onDismiss = () => {},
                        className = '',
                        autoHide = false,
                        autoHideDelay = 5000
    }) => {
    useEffect(() => {
        let timer;
        if (show && autoHide) {
            timer = setTimeout(() => {
                onDismiss()
            }, autoHideDelay);
        }

        return () => {
            if (timer) clearTimeout(timer);
        };
    }, [show, autoHide, autoHideDelay, onDismiss]);

if (!show) return null;

const errorTypeClasses = {
    auth: "bg-danger-subtle border border-danger text-danger",
    validation: "bg-warning-subtle border border-warning text-warning",
    server: "bg-secondary-subtle border border-secondary text-secondary",
    network: "bg-primary-subtle border border-primary text-primary",
    default: "bg-light border border-dark text-dark"
}

const typeClass = errorTypeClasses[type] || errorTypeClasses.default;

    const errorIcons = {
        auth: (
            <FiAlertOctagon />
        ),
        validation: (
            <FiAlertOctagon />
        ),
        server: (
            <FiCloudOff />
        ),
        network: (
            <FiCloudOff />
        )
    };

    const icon = errorIcons[type] || errorIcons.auth;

    return (
        <div
            className={` h-auto mt-2 p-1 w-100 alert position-absolute z-1 d-flex align-items-start border-start border-2 rounded ${typeClass} ${className}`}
            role="alert"
            style={{ marginBottom: '0px' }}
        >
            <div className="flex-grow-1">
                <p className="fw-medium mb-0">{icon} {message}</p>
            </div>
            <button
                onClick={onDismiss}
                type="button"
                className="btn-close ms-auto"
                aria-label="Chiudi"
            ></button>
        </div>

    );
};

export default LoginError;