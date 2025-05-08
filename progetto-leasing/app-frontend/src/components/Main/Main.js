import 'bootstrap/dist/css/bootstrap.min.css';

import FastSearch from './MainElements/FastSearch';
import LoginForm from "./MainElements/LoginForm";
import RegistrationForm from "./MainElements/RegistrationForm";
import ContractFastSearch from "./MainElements/ContractFastSearch";
import AdvancedCarContractSearch from "./MainElements/AdvancedCarContractSearch";
import AdvancedContractSearch from "./MainElements/AdvancedContractSearch";

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

            <div className="row">
                <ContractFastSearch />
                <AdvancedCarContractSearch />
                <AdvancedContractSearch />
            </div>

        </main>

    )
}

export default Main;