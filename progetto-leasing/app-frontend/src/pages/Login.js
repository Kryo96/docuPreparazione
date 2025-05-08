import {useNavigate} from "react-router-dom";
import {useAuth} from "../provider/AuthProvider";
import LoginForm from "../components/Main/MainElements/LoginForm";
import Main from "../components/Main/Main";

function Login() {
    const { setToken } = useAuth();
    const navigate = useNavigate();

    //function handleLogin(){
      //  setToken("this is a test token");
        //navigate("/", { replace: true });
    //}

    //setTimeout(() => {
      //  handleLogin();
    //}, 3 * 1000);

    return (
        <>
            <Main children={<LoginForm />} />
        </>
    );
}

export default Login;