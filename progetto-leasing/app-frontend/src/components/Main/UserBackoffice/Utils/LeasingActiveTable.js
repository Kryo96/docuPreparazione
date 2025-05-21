function LeasingActiveTable({data}) {
    return (
        <table className="table table-bordered table-striped">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Identificativo Unico</th>
                <th>Oggetto</th>
                <th>Data inizio</th>
                <th>Data fine</th>
            </tr>
            </thead>
            <tbody>

            {data.map((client) => (
                <tr key={client.id}>
                    <td>{client.name}</td>
                    <td>{client.email}</td>
                    <td>{client.uniqueStringForIdentity}</td>
                    <td>{client.object}</td>
                    <td>{client.init}</td>
                    <td>{client.end}</td>
                </tr>
            ))}
            </tbody>
        </table>
    )
}

export default LeasingActiveTable;