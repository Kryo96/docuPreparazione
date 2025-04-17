
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
| **Separatore batch**         | `GO`                             | (non usato)                                                |     |         |
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


### Spring Core Annotations – Mappa Mentale

#### Bean Management

- **@Component**
  - Marca una classe come bean da gestire nel context di Spring.
  - Viene rilevata automaticamente tramite component scanning.
  - Varianti:
    - `@Service` → semantica per i servizi.
    - `@Repository` → semantica per i DAO.
    - `@Controller` → semantica per i controller web.

- **@Bean**
  - Marca un metodo che restituisce un oggetto da registrare come bean.
  - Deve essere dentro una classe con `@Configuration`.

- **@Configuration**
  - Indica che la classe contiene definizioni di bean (Java-based config).
  - I metodi annotati con `@Bean` verranno eseguiti per istanziare i bean.

- **@Lazy**
  - Ritarda l'istanziazione del bean fino a quando non viene richiesto.

- **@Primary**
  - Specifica quale bean usare come default in caso di ambiguità.

#### Dependency Injection

- **@Autowired**
  - Inietta automaticamente un bean nel punto indicato (costruttore, field, setter).
  - Funziona per tipo (by type).
  - Può essere opzionale: `@Autowired(required = false)`

- **@Qualifier**
  - Specifica esattamente quale bean iniettare (by name).
  - Usato con `@Autowired` per risolvere conflitti.

#### Web e REST (Spring MVC)

- **@Controller**
  - Marca una classe come controller MVC (restituisce una view).
  
- **@RestController**
  - È un mix di `@Controller` + `@ResponseBody` (restituisce JSON/XML).

- **@RequestMapping**
  - Mappa richieste HTTP a metodi.
  - Varianti:
    - `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`

- **@PathVariable**
  - Estrae valori dinamici dal path dell’URL.
  
- **@RequestParam**
  - Estrae parametri della query string (`?id=123`).
  
- **@RequestBody**
  - Mappa il body della richiesta HTTP a un oggetto Java.

#### Altre utili

- **@Value**
  - Inietta valori da `application.properties` o `application.yml`.

- **@PostConstruct / @PreDestroy**
  - Metodi eseguiti subito dopo l’inizializzazione / prima della distruzione del bean.

- **@Profile**
  - Definisce bean che devono essere attivi solo in certi profili (`dev`, `prod`, etc).

### ActiveMQ 

Apache ActiveMQ è un message broker open-source, scritto in Java, che implementa il protocollo JMS (Java Message Service). E' quindi un sistema di messaggistica che permette a più applicazioni o servizi di cominicare tra loro in modo asincrono, disaccoppiato, e affidabile. 

![[Pasted image 20250416154350.png]]
##### Come funziona ? 

si basa su un architettura producer/consumer e point-to-point (P2P), il producer che si può considerare e astrarre come se fosse un servizio esterno, invia un messaggio (o notifica, o update, o semplice testo...ecc.). Il **Broker (ActiveMQ)** riceve il messaggio, lo tiene in coda e lo inoltra quando è possibile. Il consumatore ovviamente riceve il messaggio e lo consuma. 

##### Modalità di comunicazione 

| Modalità      | Descrizione                                                                  |
| ------------- | ---------------------------------------------------------------------------- |
| Queue P2P     | 1 producer, 1 consumer. Una volta ricevuto, il messaggio sparisce dalla coda |
| Topic pub/sub | 1 producer, N consumer. Tutti ricevono il messaggio                          |
_Nota: il tutto avviene in modo asincrono, il producer non aspetta che il consumer riceva il messaggio. Il broker in ogni caso garantisce la consegna anche se il consumer non è attivo al momento._

##### Modelli di comunicazione ActiveMQ

supporta diversi protocolli di trasporto, tra cui **TCP** che è quello di default e **OpenWire** che è proprietario di ActiveMQ 

**OpenWire** é un protocollo binario per ottimizzare la comunicazione tra i client e il broker di messaggi. Offre alta efficienza in termini di prestazioni, supporto completo a tutte le funzionalità di activeMQ e una comunicazione persistente e affidabile.

Funziona strutturando ogni messaggio come una sequenza di comandi (Command-based Protocol). Ogni comando ha un identificatore ('MessageSend', 'MessageAck','ConnectionInfo'), viene serializzato e inviato tramite canale TCP

#### Applicazione pratica 

Un utilizzo pratico in java, usando la libreria JMS di ActiveMQ: 

```java
ActiveMQConnectionFactory connectionFactory =
  new ActiveMQConnectionFactory("tcp://localhost:61616");

Connection connection = connectionFactory.createConnection();
Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
Destination destination = session.createQueue("test-queue");
MessageProducer producer = session.createProducer(destination);
TextMessage message = session.createTextMessage("Hello, OpenWire!");
producer.send(message);
```

La connessione in questo caso è fatta tramite OpenWire sulla porta 61616

Invece utilizzando Spring come nel codice fornito da Lukas la connessione avviene tramite la creazione di un @Bean

```java 
@Bean  
public ActiveMQConnectionFactory activeMQConnectionFactory() {  
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();  
    connectionFactory.setBrokerURL("tcp://localhost:61616"); // Inserisci l'URL del broker ActiveMQ  
    connectionFactory.setUserName("admin"); // Se necessario  
    connectionFactory.setPassword("admin"); // Se necessario  
    return connectionFactory;  
}
```


##### Sintesi consumer codice Lukas 

Questo codice configura una connessione a un broker ActiveMQ, definisce una coda da cui consumare i messaggi, configura un adattatore per ascoltare i messaggi in arrivo e definisce un contenitore di ascoltatori che gestisce la concorrenza per l'elaborazione dei messaggi. L'architettura è orientata alla gestione di più consumatori di messaggi in modo efficiente, con l'uso di una connessione cache e un numero di thread variabile.

### Maven (dallo yiddish "esperto" o "accumulatore di conoscenza)

Maven è un progetto open-source per l'organizzazione di progetti java. Può benissimo essere paragonato ad ant, ma offre funzionalità più avanzate come ad esempio :

- Standardizzazione della struttura di un progetto compilazione;
- test ed esportazione automatizzate 
- gestione e download auto delle librerie per il progetto
- creazione auto di un sito di gestione progetto contenente infos 

#### Scope 

Il goal principale di Maven è provvedere a:

 - Un modello per progetti facile da comprendere, che sia ri-usabile e mantenibile
 - Plugins o tools che interagiscano con questo modello dichiarativo 

La struttura progetto di Maven e i contenuti sono dichiarati nel file _pom.xml_
che si riferisce a Project Object Model (POM), che è l'unità fondamentale dell'intero sistema maven. 

#### Convention Vs. Configuration 

Maven preferisce utilizzare le convenzioni invece che le configurazioni, questo vuol dire che al programmatore verrà richiesto il minimo numero di configurazioni. Maven fornisce infatti, dei default behavior per i progetti. Quando un progetto viene creato maven crea una struttura di default, richiedendo al programmatore di inserire i file nelle posizioni suggerite. 

Un esempio dei valori di default per la project structure: 

| Item               | Default                       |
| ------------------ | ----------------------------- |
| source code        | ${basedir}/src/main/java<br>  |
| Resources          | ${basedir}/src/main/resources |
| Compiled Byte code | ${basedir}/target             |
ecc..

#### Maven Feature 

- project setup semplice che segue le best practices
- Uso consistente tra i progetti 
- Gestione delle dipendenze includendo l'update auto 
- Un largo e crescente bacino di librerie 
- Estendibile, con l'abilità di scrivere facilmente plugins in java o script lang 
- Accesso istante a nuove feature con piccola o nessuna configurazione 
- **Model-based builds** - Maven è abilitato a produrre qualsiasi numero di progetti in un tipo di output predefinito come jar,war,metadata.
- **Coherent site of project information** - Usando gli stessi metadata per il build process, maven è in grado di generare un sito e un PDF includendo la documentazione completa. 
- **Release management and distribution publication** - Senza configurazioni aggiuntive, maven si integrerà con il tuo source control system come CSV (credo che si intenda a detta di vava control system versioning o comunque qualcosa riferito a quello) e gestirà la release del progetto. 
- **Backward Compatibility** - Puoi facilmente portare i moduli di un progetto a Maven 3 da una vecchia versione.
- **Automatic parent versioning** - Non necessario specificare il parent nel sub module per la manutenzione 
- **Parallel  builds** - analizza il grafo delle dipendenze di progetto e ti abilita a build schedule modules in parallelo. 
- **Better Error e Integrity Reporting** 

#### POM Project Object Model 

E' una parte fondamentale di Maven che merita di essere visto in modo approfondito. Il POM contiene tutte le informazioni riguardante il progetto e i vari dettagli di configurazione usati da Maven per buildare il progetto.

POM contiene anche i goals e i plugins. Mentre esegue un task o un goal, Maven cerca il POM nella directory corrente, legge il POM e prende le informazioni di configurazioni necessarie, successivamente esegue il task o il goal.

Prima di creare un POM, dobbiamo decidere un project group _groupId_, il suo nome _artifactId_ e la versione 

Esempio di POM : 

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>
    <groupId>com.companyname.project-group</groupId>
    <artifactId>project</artifactId>
    <version>1.0</version>
</project>
```


**TUTTI** i file POM richiedo il project element e i 3 campi obbligatori: groupId, artifactId, version. 

##### Super POM 

il super POM è il POM di default di maven, ogni POMs eredita da un default, in questo caso dal super POM, e contiene dei valori ereditati di default. 

### Cos'è il Build Lifecycle? 

Un Build Lifecycle è una sequenza ben definita di fasi, che definiscono l'ordine in cui i goals vengono eseguiti. Ma come potrebbe essere un un tipico build lifecycle di maven? 

| Phase             | Handles                    | Description                                                                                  |
| ----------------- | -------------------------- | -------------------------------------------------------------------------------------------- |
| prepare-resources | resource copying           | il resource copying può essere personalizzato in questa fase                                 |
| validate          | validating the information | Validazione se il progetto è corretto e se tutte le informazioni necessarie sono disponibili |
| compile           | compilation                | il codice sorgente compilato è pronto in questa fase                                         |
| test              | testing                    | test il codice sorgente compilato adatto per il testing framework                            |
| package           | packaging                  | questa fase crea il JAR/WAR package come menzionato nel 'packaging' nel file POM.xml         |
| install           | installation               | questa fase installa i package nel local/remote maven repository                             |
| deploy            | deploying                  | copia il package finale nella repository remota                                              |
Ci sono sempre fasi **pre** e **post** per registrare i **goals**, che devono essere eseguiti prima di, o dopo una fase particolare. 

##### Un goal :
rappresenta uno specifico task che contribuisce al building e alla gestione del progetto. Un goal non è legato ad uno specifico lifecycle potrebbe benissimo essere eseguito al di fuori con una invocazione diretta. 

#### Maven Default (or Build) lifecycle 

|Sr.No.|Lifecycle Phase & Description|
|---|---|
|1|**validate**<br><br>Validates whether project is correct and all necessary information is available to complete the build process.|
|2|**initialize**<br><br>Initializes build state, for example set properties.|
|3|**generate-sources**<br><br>Generate any source code to be included in compilation phase.|
|4|**process-sources**<br><br>Process the source code, for example, filter any value.|
|5|**generate-resources**<br><br>Generate resources to be included in the package.|
|6|**process-resources**<br><br>Copy and process the resources into the destination directory, ready for packaging phase.|
|7|**compile**<br><br>Compile the source code of the project.|
|8|**process-classes**<br><br>Post-process the generated files from compilation, for example to do bytecode enhancement/optimization on Java classes.|
|9|**generate-test-sources**<br><br>Generate any test source code to be included in compilation phase.|
|10|**process-test-sources**<br><br>Process the test source code, for example, filter any values.|
|11|**test-compile**<br><br>Compile the test source code into the test destination directory.|
|12|**process-test-classes**<br><br>Process the generated files from test code file compilation.|
|13|**test**<br><br>Run tests using a suitable unit testing framework (Junit is one).|
|14|**prepare-package**<br><br>Perform any operations necessary to prepare a package before the actual packaging.|
|15|**package**<br><br>Take the compiled code and package it in its distributable format, such as a JAR, WAR, or EAR file.|
|16|**pre-integration-test**<br><br>Perform actions required before integration tests are executed. For example, setting up the required environment.|
|17|**integration-test**<br><br>Process and deploy the package if necessary into an environment where integration tests can be run.|
|18|**post-integration-test**<br><br>Perform actions required after integration tests have been executed. For example, cleaning up the environment.|
|19|**verify**<br><br>Run any check-ups to verify the package is valid and meets quality criteria.|
|20|**install**<br><br>Install the package into the local repository, which can be used as a dependency in other projects locally.|
|21|**deploy**<br><br>Copies the final package to the remote repository for sharing with other developers and projects.|
Possiamo anche pensare di attaccare un plugin ad una delle fasi come ad esempio _maven-antrun-plugin:run goal_, questo ci permetterà di visualizzare tramite echo text i messaggi a schermo delle fasi nel lifecycle. 

### Build Profile 

Un build profile è un set di valori di configurazione, che possono essere usati per impostare o overridare valori di default del maven build. Puoi quindi personalizzare le build per ambienti differenti come produzione v/s development.

##### Tipi di Build Profile 
|Type|Where it is defined|
|---|---|
|Per Project|Defined in the project POM file, pom.xml|
|Per User|Defined in Maven settings xml file (%USER_HOME%/.m2/settings.xml)|
|Global|Defined in Maven global settings xml file (%M2_HOME%/conf/settings.xml)|
##### Profile Activation 

Un maven build profile può essere attivato in modi differenti:
- esplicitamente usando un comando da shell
- attraverso i maven settings 
- basandoci sulle variabili d'ambiente (User/System)
- OS setting 
- file presenti/mancanti 

### Repositories 

Nella terminologia Maven un repository è una directory dove tutte le jars, library jar, plugins o qualsiasi altro artefatto è salvato e può essere usato da Maven facilmente. 

Possono essere ti 3 tipi 

- locale
- centrale
- remota

### Plug-in Maven 

Maven è un plugin execution framework dove ogni task è attualmente fatta da plugin. I plugin in ogni caso vengono generalmente usati per 

- Creare file jar
- Creare file war 
- Compilare files di codice 
- Unit testing del codice
- Creare documentazione del progetto 
- Creare reports del progetto 

I plugin generalmente forniscono un set di goals, che possono essere eseguiti con la seguente sintassi 

```shell
mvn [plugin-name]:[goal-name]
```

##### Plugin Types 
|Sr.No.|Type & Description|
|---|---|
|1|**Build plugins**<br><br>They execute during the build process and should be configured in the <build/> element of pom.xml.|
|2|**Reporting plugins**<br><br>They execute during the site generation process and they should be configured in the <reporting/> element of the pom.xml.|
