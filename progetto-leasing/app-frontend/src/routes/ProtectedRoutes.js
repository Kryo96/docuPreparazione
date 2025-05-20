import {Navigate, Outlet, useLocation} from "react-router-dom";
import { useAuth } from "../provider/AuthProvider";

export const ProtectedRoutes = () => {
    const { token } = useAuth();
    const location = useLocation();

    // Check if the user is authenticated
    if (!token) {
        // If not authenticated, redirect to the login page, storing the current location
        return <Navigate to="/login" />;
    }

    // If authenticated, render the child routes
    return <Outlet />;
};