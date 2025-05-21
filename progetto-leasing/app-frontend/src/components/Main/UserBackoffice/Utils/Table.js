import LeasingActiveTable from "./LeasingActiveTable";
import UserTable from "./UserTable";
import LeasingExpiredTable from "./LeasingExpiredTable";

function Table ({data, searchType}) {
    console.log(searchType)
    return (
        <div className="table-responsive mt-4">

            {
                searchType === 'clients' ? (
                    <UserTable data={data} />
                ) : searchType === "leasing" ? (
                    <LeasingActiveTable data={data} />
                ) : (
                    <LeasingExpiredTable data={data} />
                )
            }

        </div>
    )
}
export default Table;