import logo from './logo.svg';
import './App.css';
import Navbar from "./components/Navbar/Navbar";
import Main from "./components/Main/Main";
import AuthProvider from './provider/AuthProvider';
import Routes from "./routes";

function App() {
    return (
        <AuthProvider>
            <Navbar />
            <Routes />
        </AuthProvider>
    );
}

export default App;
