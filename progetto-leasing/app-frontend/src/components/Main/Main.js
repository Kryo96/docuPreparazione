import 'bootstrap/dist/css/bootstrap.min.css';

import FastSearch from './MainElements/FastSearch';
import LoginForm from "./MainElements/LoginForm";
import RegistrationForm from "./MainElements/RegistrationForm";

function Main() {
    return (
        <main className="container my-4">
            <div className="row">
            <LoginForm />
            <FastSearch />
            </div>

            <div className="row">
                <RegistrationForm />

            </div>
        </main>

    )
}

export default Main;