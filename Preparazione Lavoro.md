
### Microsoft SQL server 

E' un db relazionale RDBMS. Le applicazioni si connettono ad un istanza o a un DB si SQL Server e comunicano tramite **Transact-SQL**
#### Come installare SQL server su Mac M3 usando docker

per prima cosa scarichiamo l'immagine di SQL server (opzionale si può usare direttamente il secondo comando e verranno scaricate le immagini in modo automatico)

```shell
docker pull mcr.microsoft.com/azure-sql-edge
```

Poi inizializziamo il container con : 

```shell
docker run --cap-add 
SYS_PTRACE 
-e 'ACCEPT_EULA=1' 
-e 'MSSQL_SA_PASSWORD=yourStrong(!)Password' 
-p 1433:1433 
--name azuresqledge 
-d mcr.microsoft.com/azure-sql-edge
```


_**Note: E' importante (al momento) avere una versione di docker <= della 4.32/4.33 perché le altre spaccano tutto**_


##### Transact SQL 

Sono per farla breve le operazioni come SELECT, INSERT, UPDATE E DELETE. La sintassi è quella che ci si aspetta da mySql 

```sql
<SELECT statement> ::=
    [ WITH { [ XMLNAMESPACES , ] [ <common_table_expression> [ , ...n ] ] } ]
    <query_expression>
    [ ORDER BY <order_by_expression> ]
    [ <FOR Clause> ]
    [ OPTION ( <query_hint> [ , ...n ] ) ]
<query_expression> ::=
    { <query_specification> | ( <query_expression> ) }
    [  { UNION [ ALL ] | EXCEPT | INTERSECT }
        <query_specification> | ( <query_expression> ) [ ...n ] ]
<query_specification> ::=
SELECT [ ALL | DISTINCT ]
    [ TOP ( expression ) [ PERCENT ] [ WITH TIES ] ]
    <select_list>
    [ INTO new_table ]
    [ FROM { <table_source> } [ , ...n ] ]
    [ WHERE <search_condition> ]
    [ <GROUP BY> ]
    [ HAVING <search_condition> ]
[ ; ]
```

Esempio concreto : 

```sql
SELECT FirstName,
       LastName,
       StartDate AS FirstDay
FROM DimEmployee
ORDER BY LastName;
```

### Differenze sostanziali 

#SQL Server vs PostgreSQL - Sintassi a confronto

| Funzione/Clausola            | SQL Server (T-SQL)               | PostgreSQL (PL/pgSQL)                                      |     |         |
| ---------------------------- | -------------------------------- | ---------------------------------------------------------- | --- | ------- |
| **Seleziona primi N record** | `SELECT TOP 10 * FROM tabella`   | `SELECT * FROM tabella LIMIT 10`                           |     |         |
| **Database corrente**        | `USE nome_db` + `GO`             | `\c nome_db` nel terminale, oppure con connessione diretta |     |         |
| **Separatore batch**         | `GO`                             | ❌ (non usato)                                              |     |         |
| **Data/Ora corrente**        | `GETDATE()`                      | `NOW()`                                                    |     |         |
| **Lunghezza stringa**        | `LEN(campo)`                     | `LENGTH(campo)`                                            |     |         |
| **Null check con fallback**  | `ISNULL(campo, valore)`          | `COALESCE(campo, valore)`                                  |     |         |
| **Auto increment**           | `INT IDENTITY(1,1)`              | `SERIAL` / `GENERATED ALWAYS AS IDENTITY`                  |     |         |
| **Booleano**                 | `BIT` (0 o 1)                    | `BOOLEAN` (`true` / `false`)                               |     |         |
| **Concatenazione stringhe**  | `campo1 + campo2`                | `campo1                                                    |     | campo2` |
| **Esecuzione procedura**     | `EXEC nome_procedura`            | `CALL nome_funzione()`                                     |     |         |
| **Stored Procedure**         | `CREATE PROCEDURE`               | `CREATE FUNCTION ... RETURNS void`                         |     |         |
| **Gestione JSON**            | `JSON_VALUE(json, '$.campo')`    | `json->'campo'`, `json->>'campo'`                          |     |         |
| **Case-insensitive?**        | Sì (di default)                  | No (sensibile se si usano "doppi apici")                   |     |         |
| **Commenti**                 | `-- commento` / `/* commento */` | `-- commento` / `/* commento */`                           |     |         |

---

##### Note aggiuntive

- **SQL Server** è molto legato all’ambiente Microsoft (es. .NET, Windows).
- **PostgreSQL** è più open e ha estensioni potenti come PostGIS, JSONB, ecc.
- Entrambi supportano transazioni, viste, trigger, CTE, ecc., ma la sintassi può variare.


#### SELECT 

la sintassi : 

```sql
SELECT [ ALL | DISTINCT ]  
[ TOP ( expression ) [ PERCENT ] [ WITH TIES ] ]   
<select_list>   
<select_list> ::=   
    {   
      *   
      | { table_name | view_name | table_alias }.*   
      | {  
          [ { table_name | view_name | table_alias }. ]  
               { column_name | $IDENTITY | $ROWGUID }   
          | udt_column_name [ { . | :: } { { property_name | field_name }   
            | method_name ( argument [ ,...n] ) } ]  
          | expression  
         }  
        [ [ AS ] column_alias ]   
      | column_alias = expression   
    } [ ,...n ]
```


##### Argomenti possibili 

**ALL** 
specifica di ritornare anche i duplicati tutto assieme seee daje roma 

**DISTINCT** 
righe univoche same as usual 

**TOP[PERCENT]   [WITH TIES]** 
restituisce un set limitato (quando usi LIMIT su postgres) o una prima percentuale di righe specificate il valore può essere espresso come intero o come percentuale. E' importante prestare attenzione quando si utilizza questa clausola con UNION,UNION ALL, EXCEPT o INTERSECT poiché l'ordine in cui vengono elaborate queste ops è poco intuitivo. 

Ora per esempio se dovessi trovare nel nostro DB, con la tabella Cars

```sql 
CREATE TABLE dbo.Cars
(
    Model VARCHAR (15),
    Price MONEY,
    Color VARCHAR (10)
);

INSERT dbo.Cars
VALUES ('sedan', 10000, 'red'),
    ('convertible', 15000, 'blue'),
    ('coupe', 20000, 'red'),
    ('van', 8000, 'blue');
```

Potremmo utilizzare con UNION ALL due SELECT che prendono il TOP(1) delle macchine ordinate ASC e DESC per prendere la macchina blu più costosa e quella rossa meno costosa (oltretutto sulla guida microsoft questo esempio è sbagliato lol)

quindi useremo :

```sql
SELECT TOP (1) Model, Color, Price
FROM dbo.Cars
WHERE Color = 'red'
UNION ALL
SELECT TOP (1) Model, Color, Price
FROM dbo.Cars
WHERE Color = 'blue'
ORDER BY Price ASC;
GO
```

**SBAGLIATO** poiché la clausola TOP viene eseguita prima della ORDER BY, che oltretutto in questo caso agisce su tutta la UNION (che comunque è doppiamente sbagliato anche questo esempio, perché così ti prende entrambe con il prezzo più basso)

Utilizzando i invece TOP e ORDER BY in una SELECT secondaria assicura che i risultati di ORDER BY vengano applicati alla clausola TOP invece che alla UNION. 

```sql 
SELECT Model, Color, Price
FROM (SELECT TOP (1) Model, Color, Price
FROM dbo.Cars
WHERE Color = 'red'
ORDER BY Price DESC) AS a
UNION ALL
SELECT Model, Color, Price
FROM (SELECT TOP (1) Model, Color, Price
FROM dbo.Cars
WHERE Color = 'blue'
ORDER BY Price ASC) AS b;

GO
```

(Ho fixato anche la logica che era sbagliata vabè non ho parole guarda)
**_Table_name_|_view_name_|_table_alias.*_** Limita l'ambito di * alla tabella o vista specifica
(evito di riscrivere argomenti banali che sappiamo già)



