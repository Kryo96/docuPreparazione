import {useAuth} from "../../../provider/AuthProvider";

function Items () {

    const {token} = useAuth();
    const isLoggedIn = !!token;


    return (
        <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav ms-auto">
                <li className="nav-item">
                    <a className="nav-link" href="#">Car</a>
                </li>
                <li className="nav-item">
                    <a className="nav-link" href="#">Real Estate</a>
                </li>
                {
                    isLoggedIn ? (
                        <li className="nav-item">
                            <a className="nav-link" href="#">Profile</a>
                        </li>
                    ) : (
                        <>
                            <li className="nav-item">
                                <a className="nav-link" href="#">Login</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#">Registrati</a>
                            </li>
                        </>
                    )
                }

            </ul>
        </div>
    )
}

export default Items;