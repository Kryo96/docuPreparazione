import {useAuth} from "../../../provider/AuthProvider";

function Items () {

    const {token} = useAuth();
    const isLoggedIn = !!token;

    return (
        <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav ms-auto">
                {
                    isLoggedIn ? (
                        <>
                            <li className="nav-item">
                                <a className="nav-link" href="/profile">Profile</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="/leasing-active">Leasing Active</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="/leasing-create">Leasing Create</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="/balance">Balance</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="/avatar">
                                    <img
                                        src="https://placehold.co/600x600"
                                        alt="User"
                                        className="rounded-circle"
                                        width="30"
                                        height="30"
                                    />
                                </a>
                            </li>
                        </>
                    ) : (
                        <>
                            <li className="nav-item">
                                <a className="nav-link" href="#">Login</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#">Registrati</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#">Car</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#">Real Estate</a>
                            </li>
                        </>
                    )
                }

            </ul>
        </div>
    )
}

export default Items;