function UserTable({data}) {
    return (
        <table className="table table-bordered table-striped">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Identificativo Unico</th>
            </tr>
            </thead>
            <tbody>

            {data.map((client) => (
                <tr key={client.id}>
                    <td>{client.name}</td>
                    <td>{client.email}</td>
                    <td>{client.uniqueStringForIdentity}</td>
                </tr>
            ))}
            </tbody>
        </table>
    )
}
export default UserTable;