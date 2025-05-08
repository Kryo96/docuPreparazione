import 'bootstrap/dist/css/bootstrap.min.css';
import Title from "./NavbarElements/Title";
import Button from "./NavbarElements/Button";
import Items from "./NavbarElements/Items";


function Navbar () {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container-fluid">
            <Title />
            <Button />
            <Items />
            </div>
        </nav>
    )
}

export default Navbar;