import 'bootstrap/dist/css/bootstrap.min.css';

import Form from './MainElements/Form';
import FastSearch from './MainElements/FastSearch';

function Main() {
    return (
        <main className="container my-4">
            <div className="row">
            <Form />
            <FastSearch />
            </div>
        </main>

    )
}

export default Main;