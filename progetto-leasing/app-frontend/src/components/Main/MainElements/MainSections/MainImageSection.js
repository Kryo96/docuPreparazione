import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

function MainImageSection() {
    return (
        <div className="container p-0">
            <div className="h-75 w-100 overflow-hidden">
                <img
                    alt="Immagine principale"
                    className="img-fluid object-fit-cover h-100 w-100"
                    src="https://placehold.co/1200x600"
                />
            </div>


            <div className="position-relative h-25">
                <div className="position-relative top-0 start-0 w-100 h-100 d-flex align-items-center z-1">
                    <div className="container">
                        <div className="row align-items-center">

                            <div className="col-md">
                                <div className="text-black ms-md-4 ms-lg-5">
                                    <h1>
                                        <span style={{display: "block"}}>MySite</span>
                                        <span style={{display: "block"}}>Leasing</span>
                                    </h1>
                                    <p className="mb-0 fs-5">
                                        Finanziamo i tuoi bisogni con formalità minime, rapidità e assistenza costante
                                    </p>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <div className="position-absolute w-100 h-100 top-0 start-0 overflow-hidden">
                    <img
                        alt="Immagine con overlay"
                        className="img-fluid object-fit-cover h-100 w-100"
                        src="https://placehold.co/1200x600"
                    />
                </div>
            </div>
        </div>
    );
}

export default MainImageSection;
