import React from 'react';
import Dashboard from '../components/Main/User/Dashboard';
import LeasingContractsCarousel from '../components/Main/User/LeasingContractsCarousel';
import Calendar from '../components/Main/User/Calendar';
import PaymentGraph from '../components/Main/User/PaymentGraph';
import QuickActions from '../components/Main/User/QuickActions';
import NotificationsWidget from '../components/Main/User/NotificationWidget';
import PaymentStatusPanel from '../components/Main/User/PaymentStatusPanel';
import AdvancedSearch from '../components/Main/User/AdvancedSearch';
import CustomOffers from '../components/Main/User/CustomOffers';
import ContactWidget from '../components/Main/User/ContactWidget';
import FAQ from '../components/Main/User/FAQ';
import RecentDocuments from '../components/Main/User/RecentDocuments';
import ProgressBar from '../components/Main/User/ProgressBar';
import LeasingSimulator from '../components/Main/User/LeasingSimulator';
import FinancialWeatherWidget from '../components/Main/User/FinancialWeatherWidget';

const UserHomePage = () => {
    return (
        <div className="container mt-4">
            <Dashboard />
            <LeasingContractsCarousel />
            <Calendar />
            <PaymentGraph />
            <QuickActions />
            <NotificationsWidget />
            <PaymentStatusPanel />
            <AdvancedSearch />
            <CustomOffers />
            <ContactWidget />
            <FAQ />
            <RecentDocuments />
            <ProgressBar />
            <LeasingSimulator />
            <FinancialWeatherWidget />
        </div>
    );
};

export default UserHomePage;
