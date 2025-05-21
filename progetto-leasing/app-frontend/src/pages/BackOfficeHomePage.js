import React from 'react';
import Dashboard from '../components/Main/UserBackoffice/Dashboard';
import WorkQueue from '../components/Main/UserBackoffice/WorkQueue';
import ClientRequestsTable from '../components/Main/UserBackoffice/ClientRequestTable';
import AdvancedFilters from '../components/Main/UserBackoffice/AdvancedFilters';
import TeamCalendar from '../components/Main/UserBackoffice/TeamCalendar';
import SLAMonitor from '../components/Main/UserBackoffice/SLAMonitor';
import QualityControlPanel from '../components/Main/UserBackoffice/QualityControlPanel';
import QuickReporting from '../components/Main/UserBackoffice/QuickReporting';
import ExceptionManagement from '../components/Main/UserBackoffice/ExceptionManagement';
import InternalCommunication from '../components/Main/UserBackoffice/InternalCommunication';
import CreditAssessmentTools from '../components/Main/UserBackoffice/CreditAssessmentTools';
import DocumentManagementPanel from '../components/Main/UserBackoffice/DocumentManagementPanel';
import SupplierMonitoring from '../components/Main/UserBackoffice/SupplierMonitoring';
import AlertSystem from '../components/Main/UserBackoffice/AlertSystem';
import ContractTemplates from '../components/Main/UserBackoffice/ContractTemplates';
import WorkflowVisual from '../components/Main/UserBackoffice/WorkflowVisual';
import ApprovalTracking from '../components/Main/UserBackoffice/ApprovalTracking';
import RenewalsAndClosures from '../components/Main/UserBackoffice/RenewalsAndClosures';
import SearchForm from "../components/Main/MainElements/SearchForm/SearchForm";
import MultipleSearchForm from "../components/Main/UserBackoffice/Common/MultipleSearchForm";

const BackofficeUserHomePage = () => {
    return (
        <div className="container mt-4">
            <MultipleSearchForm />
            <Dashboard />
            <WorkQueue />
            <ClientRequestsTable />
            <AdvancedFilters />
            <TeamCalendar />
            <SLAMonitor />
            <QualityControlPanel />
            <QuickReporting />
            <ExceptionManagement />
            <InternalCommunication />
            <CreditAssessmentTools />
            <DocumentManagementPanel />
            <ApprovalTracking />
            <SupplierMonitoring />
            <AlertSystem />
            <ContractTemplates />
            <WorkflowVisual />
            <RenewalsAndClosures />
        </div>
    );
};

export default BackofficeUserHomePage;
