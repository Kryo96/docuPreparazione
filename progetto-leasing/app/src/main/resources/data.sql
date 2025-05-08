INSERT INTO client (id, name, email)
VALUES (1, 'Mario Rossi', 'mario.rossi@example.com');

INSERT INTO real_estate (id, address, surface_area, includes_maintenance)
VALUES (1, 'Via Milano 10, Roma', 120.5, true);


INSERT INTO leasing_contract (
    id,
    client_id,
    description,
    init_date,
    active,
    contract_code,
    contract_type,
    real_estate_id,
    start_date,
    end_date,
    file_name,
    file_type,
    upload_date
)
VALUES (
    1,
    1,
    'Leasing Immobiliare esempio',
    '2025-05-07',
    TRUE,
    'CONTRACT-123456789',
    'REAL_ESTATE',
    1,
    '2025-05-07',
    '2030-05-07',
    'documento_contratto.pdf',
    'pdf',
    '2025-05-07'
);


