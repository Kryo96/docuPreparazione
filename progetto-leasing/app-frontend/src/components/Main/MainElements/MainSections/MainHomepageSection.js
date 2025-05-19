import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

const slides = [
    {
        title: "Richiedi preventivo",
        description: (
            <>
                Se preferisci <strong>parlare con un nostro consulente commerciale</strong> e trovare insieme la <strong>migliore soluzione per te, chiama</strong> il numero sotto.
            </>
        ),
        ctaText: "800737475",
        ctaLink: "tel:+0039800737475"
    },
    {
        title: "Noleggio nuovo e usato",
        description: (
            <>
                <strong>Semplice</strong>, <strong>flessibile</strong>, <strong>16 servizi inclusi</strong> in un canone mensile fisso.
                Non dovrai preoccuparti di nulla, solo <strong>goderti la guida</strong>.
            </>
        ),
        ctaText: "Vai alla vetrina",
        ctaLink: "https://noleggio.ayvens.com/it-it/noleggio-lungo-termine/"
    },
    {
        title: "Noleggio mensile",
        description: (
            <>
                Ayvens Flex il noleggio flessibile a partire <strong>da 1 mese</strong> e <strong>senza vincoli</strong> di durata.
            </>
        ),
        ctaText: "Vai alla vetrina",
        ctaLink: "https://noleggio.ayvens.com/it-it/noleggio-medio-termine/"
    },
    {
        title: "Noleggio veicoli commerciali",
        description: (
            <>
                La soluzione di noleggio dedicata ai <strong>veicoli commerciali</strong> con servizi pensati per <strong>sostenere il tuo business</strong>.
            </>
        ),
        ctaText: "Vai alla vetrina",
        ctaLink: "https://noleggio.ayvens.com/it-it/noleggio-lungo-termine/veicoli-commerciali/"
    },
    {
        title: "Ayvens electric",
        description: (
            <>
                Passa all’elettrico: in un unico canone di noleggio avrai tutti i <strong>vantaggi del noleggio auto elettriche</strong> e tanti servizi per la ricarica.
            </>
        ),
        ctaText: "Vai alla vetrina",
        ctaLink: "https://noleggio.ayvens.com/it-it/noleggio-lungo-termine/green/"
    }
];

function MainHomepageSection() {
    return (
        <div className="container py-4 h-100">
            <div className="mb-4 h-25">
                <h1 className="text-center">
                    Noleggio a lungo termine auto, moto, furgoni, flotte aziendali e molto altro
                </h1>
            </div>

            {/* Carousel */}
            <div id="mainCarousel" className="carousel slide h-75" data-bs-ride="carousel">
                <div className="carousel-indicators">
                    {slides.map((_, i) => (
                        <button
                            key={i}
                            type="button"
                            data-bs-target="#mainCarousel"
                            data-bs-slide-to={i}
                            className={i === 0 ? 'active' : ''}
                            aria-current={i === 0 ? 'true' : undefined}
                            aria-label={`Slide ${i + 1}`}
                        ></button>
                    ))}
                </div>

                {/* Slides dinamiche */}
                <div className="carousel-inner h-100">
                    {slides.map((slide, i) => (
                        <div key={i} className={`carousel-item ${i === 0 ? 'active' : ''} h-100`}>
                            <div className="border d-flex flex-column align-items-center justify-content-center p-4 h-100 text-center">
                                <h3 className="p-2 m-0">{slide.title}</h3>
                                <p className="p-2 m-0">{slide.description}</p>
                                <a className="btn btn-primary m-2 p-2" href={slide.ctaLink} target="_blank" rel="noopener noreferrer">
                                    <strong>{slide.ctaText}</strong>
                                </a>
                            </div>
                        </div>
                    ))}
                </div>

                {/* Controlli */}
                <button className="carousel-control-prev" type="button" data-bs-target="#mainCarousel" data-bs-slide="prev">
                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Precedente</span>
                </button>
                <button className="carousel-control-next" type="button" data-bs-target="#mainCarousel" data-bs-slide="next">
                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Successiva</span>
                </button>
            </div>
        </div>
    );
}

export default MainHomepageSection;
