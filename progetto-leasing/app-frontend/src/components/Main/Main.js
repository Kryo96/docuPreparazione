import 'bootstrap/dist/css/bootstrap.min.css';

import FastSearch from './MainElements/FastSearch';
import LoginForm from "./MainElements/LoginForm";
import RegistrationForm from "./MainElements/RegistrationForm";
import ContractFastSearch from "./MainElements/ContractSearchForm/ContractFastSearch";
import AdvancedCarContractSearch from "./MainElements/ContractSearchForm/AdvancedCarContractSearch";
import AdvancedContractSearch from "./MainElements/ContractSearchForm/AdvancedContractSearch";
import AdvancedHouseContractSearch from "./MainElements/ContractSearchForm/AdvancedHouseContractSearch";
import PendingLeases from "./MainElements/LeasingOperations/PendingLeases";
import LeasesList from "./MainElements/LeasingViews/LeasesList";
import ExpiredLeasesList from "./MainElements/LeasingViews/ExpiredLeasesList";
import CreateLeaseContract from "./MainElements/LeasingOperations/CreateLeaseContract";

function Main() {
    return (


        <main className="container my-4">
            <div className="row">
                <LoginForm />
            </div>
        </main>
    )
}

export default Main;