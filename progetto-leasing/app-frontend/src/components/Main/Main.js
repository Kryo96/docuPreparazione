import 'bootstrap/dist/css/bootstrap.min.css';

import FastSearch from './MainElements/FastSearch';
import LoginForm from "./MainElements/LoginForm";
import MainImageSection from './MainElements/MainSections/MainImageSection';
import MainHomepageSection from './MainElements/MainSections/MainHomepageSection';

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

        <main className="container my-12">

            <div className="row justify-content-center" style={{height: "600px"}}>
                <MainImageSection />
            </div>

            <div className="row justify-content-center" style={{height: "600px"}}>
                <MainHomepageSection />
            </div>

            <div className="row justify-content-center">
                <LoginForm />
            </div>
        </main>
    )
}

export default Main;