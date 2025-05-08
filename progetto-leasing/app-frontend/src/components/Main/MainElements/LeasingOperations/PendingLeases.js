import { useEffect, useState } from "react";

function PendingLeases() {
  const [leases, setLeases] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch("http://localhost:8080/contracts/pending")
      .then((res) => {
        if (!res.ok) throw new Error("Errore nel caricamento dei contratti");
        return res.json();
      })
      .then((data) => {
        setLeases(data);
      })
      .catch((err) => {
        setError(err.message);
      });
  }, []);

  function handleApprove(id) {
    fetch(`http://localhost:8080/contracts/${id}/approve`, {
      method: "POST"
    })
      .then((res) => {
        if (!res.ok) throw new Error("Errore durante l'approvazione");
        // Aggiorna lista rimuovendo il contratto approvato
        setLeases((prev) => prev.filter((l) => l.id !== id));
      })
      .catch((err) => {
        alert("Errore: " + err.message);
      });
  }

  return (
    <div className="container mt-4">
      <h2 className="mb-4">Contratti di Leasing da Approvare</h2>

      {error && <div className="alert alert-danger">{error}</div>}

      {leases.length === 0 ? (
        <p>Nessun contratto da approvare al momento.</p>
      ) : (
        <table className="table table-bordered">
          <thead className="table-light">
            <tr>
              <th>ID</th>
              <th>Cliente</th>
              <th>Tipo</th>
              <th>Dettagli</th>
              <th>Durata</th>
              <th>Azioni</th>
            </tr>
          </thead>
          <tbody>
            {leases.map((lease) => (
              <tr key={lease.id}>
                <td>{lease.id}</td>
                <td>{lease.cliente}</td>
                <td>{lease.tipo}</td>
                <td>{lease.tipo === "macchina" ? lease.veicolo : lease.indirizzo}</td>
                <td>{lease.durata} mesi</td>
                <td>
                  <button
                    className="btn btn-success btn-sm"
                    onClick={() => handleApprove(lease.id)}
                  >
                    Approva
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default PendingLeases;
