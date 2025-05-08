import axios from "axios";
import {
    createContext,
    useContext,
    useEffect,
    useMemo,
    useState,
} from "react";

// In summary, this code sets up the authentication context using React's context API.
// It provides the authentication token and the setToken function to child components through the context.
// It also ensures that the default authorization header in axios is updated with the authentication token whenever it changes.


const AuthContext = createContext('security');

function AuthProvider({ children }){
    // State to hold the authentication token
    const [token, setToken_] = useState(localStorage.getItem("token"));

    // Function to set the authentication token
    function setToken(newToken){
        setToken_(newToken);
    }

    useEffect(() => {
        if (token) {
            axios.defaults.headers.common["Authorization"] = "Bearer " + token;
            localStorage.setItem('token',token);
        } else {
            delete axios.defaults.headers.common["Authorization"];
            localStorage.removeItem('token')
        }
    }, [token]);

    // Memoized value of the authentication context
    const contextValue = useMemo(
        () => ({
            token,
            setToken,
        }),
        [token]
    );

    // Provide the authentication context to the children components
    return (
        <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>
    );
}

export function useAuth(){
    return useContext(AuthContext);
}

export default AuthProvider;