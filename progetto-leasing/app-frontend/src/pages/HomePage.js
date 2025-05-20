import Main from "../components/Main/Main";
import LoginForm from "../components/Main/MainElements/LoginForm";
import MainImageSection from '../components/Main/MainElements/MainSections/MainImageSection';
import MainHomepageSection from '../components/Main/MainElements/MainSections/MainHomepageSection';

function HomePage() {
    const sections = [
        {Component: MainImageSection, Classname:"row justify-content-center mb-4", Style:{height: "600px"}},
        {Component: MainHomepageSection, Classname: "row justify-content-center mb-4", Style:{height: "600px"}},
        {Component: LoginForm, Classname: "row justify-content-center", Style:{height: "auto"}},
    ]

    return (
        <Main sections={sections} />
    )
}
export default HomePage;