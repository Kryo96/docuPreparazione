import RegistrationForm from "../components/Main/MainElements/RegistrationForm";
import Main from "../components/Main/Main";

function RegistrationPage() {
    const sections = [
        {
            Component: RegistrationForm, Classname: "row", Style: {height: "600px"}
        }
    ]
    return (
        <>
            <Main sections={sections} />
        </>
    )
}
export default RegistrationPage;