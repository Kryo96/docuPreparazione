import 'bootstrap/dist/css/bootstrap.min.css';

import FastSearch from './MainElements/FastSearch';

import ContractFastSearch from "./MainElements/ContractSearchForm/ContractFastSearch";
import AdvancedCarContractSearch from "./MainElements/ContractSearchForm/AdvancedCarContractSearch";
import AdvancedContractSearch from "./MainElements/ContractSearchForm/AdvancedContractSearch";
import AdvancedHouseContractSearch from "./MainElements/ContractSearchForm/AdvancedHouseContractSearch";
import PendingLeases from "./MainElements/LeasingOperations/PendingLeases";
import LeasesList from "./MainElements/LeasingViews/LeasesList";
import ExpiredLeasesList from "./MainElements/LeasingViews/ExpiredLeasesList";
import CreateLeaseContract from "./MainElements/LeasingOperations/CreateLeaseContract";



function Main({ sections }) {
    return (

        <main className="container my-12">
            {
                sections?.map((section, index) => (
                    <div key={index} className={section.Classname} style={{height: section.Style.height}} >
                        <section.Component />
                    </div>
                ))
            }
        </main>
    )
}

export default Main;