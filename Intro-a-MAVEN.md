# INTRO

### ✅ **Cos'è Maven e a cosa serve**
- Tool di build per Java e altri linguaggi JVM (es. Kotlin)
- Automatizza il download delle dipendenze
- Fornisce struttura al progetto tramite convenzioni
- Supporta plugin per check di qualità, deploy, test ecc.

### 🔧 **Setup iniziale**
Un progetto Maven ha solitamente:
- Un file `pom.xml`
- Una cartella `src/`

Per installare Maven:
- **Windows:** `choco install maven`
- **MacOS:** `brew install maven`
- **Ubuntu:** `apt install maven`
- Oppure scarica manualmente da [apache.org](https://maven.apache.org/) ed estrai, poi aggiungi il path di `bin/` alle variabili d’ambiente

### 🧰 **Maven Wrapper**
- File `mvnw` e `mvnw.cmd` permettono di usare Maven senza installarlo globalmente
- Per aggiungerlo: `mvn wrapper:wrapper`
- Utile quando condividi progetti

### 🗂 **Struttura di un progetto Maven**
- `pom.xml`: file di configurazione principale
- `src/main/java`: codice sorgente
- `src/test/java`: codice di test
- `src/main/resources`: file statici (config, immagini…)
- `src/test/resources`: file di test statici

### 📄 **Contenuto del `pom.xml`**
- `groupId`: identifica il gruppo (es. com.codestorm.study)
- `artifactId`: nome del progetto (es. mio-progetto)
- `version`: es. 1.0-SNAPSHOT (versione in sviluppo)
- **Properties:** configurazioni riutilizzabili (es. Java version, versioni librerie)
- **Dependencies:** librerie da scaricare (con scope `compile` o `test`)
- **Plugins:** per testing, compilazione, deploy ecc.
- **Licenses:** indica la licenza del progetto
- **SCM (Source Control Management):** info sul repository del codice sorgente

### 📦 **Comandi Maven più comuni**
- `mvn clean`: elimina la cartella `target/`
- `mvn compile`: compila i file sotto `src/main/java`
- `mvn test`: compila e esegue i test da `src/test/java`
- `mvn package`: crea un JAR del progetto
- `mvn install`: installa il JAR nel repository locale (`~/.m2/repository`)
- Puoi anche combinare: `mvn clean install`

### 🔍 **Aggiungere dipendenze**
- Trova le librerie su:
  - [search.maven.org](https://search.maven.org/)
  - GitHub (cerca progetti Maven, copia tag `dependency`)
- IntelliJ suggerisce e importa automaticamente le dipendenze (`Alt+Enter`)

### 📚 **Ereditarietà e progetti multi-modulo**
- I progetti complessi possono avere:
  - Un **parent POM** (con packaging `pom`)
  - Vari **moduli figli** con propri `pom.xml`
  - I moduli ereditano proprietà e dipendenze dal parent
  - I moduli possono dipendere l’uno dall’altro

### 🧠 **Altre cose utili da sapere**
- Le dipendenze sono salvate in `~/.m2/repository`
- Maven usa un repository centrale per scaricare le librerie
- I test e le build sono eseguiti tramite plugin come `maven-surefire-plugin`
 
### Cos'è Maven?

Possiamo dire, innanzitutto, che Maven è un *build tool*, uno strumento di build. Se hai familiarità con strumenti come `make` o `ant`, sai che il loro compito è prendere il codice sorgente e trasformarlo in una libreria distribuibile, come un file `.jar`, `.war` o `.ear`.

Ma Maven è anche uno **_strumento di gestione delle dipendenze_**. Questa è spesso considerata la caratteristica preferita dagli utenti.  
**Quando costruiamo applicazioni, queste fanno uso di librerie di terze parti, come Spring o Hibernate.**  
Grazie a Maven, il modo in cui otteniamo e gestiamo queste dipendenze cambia radicalmente:

- diventa più semplice, automatizzato e centralizzato.

Oltre a ciò, Maven può essere considerato uno **_strumento di gestione del progetto_**, anche se non si tratta della sua funzione principale. Ci consente comunque di includere alcune informazioni sul software che stiamo sviluppando, come il **numero di versione** o i **nomi dei collaboratori**.  
Offre inoltre una panoramica ordinata di tutte le risorse e dipendenze impiegate all’interno del progetto.

Un altro aspetto fondamentale di Maven è che fornisce un **_approccio standardizzato alla costruzione del software_**.  
Questo significa che se abbiamo cinque progetti diversi e in tutti usiamo Maven, noteremo un approccio coerente nella struttura e nel processo di build di ciascuno. Questa coerenza ci fa risparmiare tempo e aumenta l’efficienza, proprio perché ci muoviamo in un ambiente prevedibile.

### Cosa fa Maven come build tool

Per capire Maven a fondo, è essenziale comprendere bene cosa fa un **_build tool_**. Il suo obiettivo principale è prendere i file sorgente e trasformarli in **artefatti deploiabili**, come `.jar` o `.war`.

Ma in realtà, ci sono molti altri passaggi che potremmo voler includere nel processo di build: **esecuzione dei test automatici, generazione della documentazione (JavaDoc), analisi della qualità del codice e così via**. Un processo di build ben fatto dovrebbe automatizzare tutti questi passaggi in modo che nulla venga dimenticato o eseguito in modo errato.

**Ed è proprio qui che entrano in gioco gli strumenti di build come Maven.** Ci permettono di avere un processo automatizzato e ripetibile, così che ogni sviluppatore del team possa lanciare la stessa build in modo consistente.  
Questo **elimina l’errore umano e rende l’integrazione** di nuovi membri nel progetto molto più semplice: basta conoscere lo strumento di build, e si è in grado di costruire l’intero progetto fin da subito.

**_Una delle funzionalità fondamentali_** di un build tool come Maven è la possibilità di **generare artefatti** a partire dal codice sorgente. Questo processo porta con sé numerosi vantaggi, tra cui l’automazione della fase di deploy: ad esempio, un file `.war` o `.ear` può essere automaticamente inviato a un server, oppure un file `.jar` può essere salvato in un repository da cui potrà essere recuperato in futuro.  
Tutto questo fa parte delle capacità offerte da Maven come strumento di build.

- È naturale pensare: “Posso fare lo stesso usando il mio IDE o il compilatore Java, creare manualmente un `.jar` o un `.war`...”. Tuttavia, il vero valore aggiunto di Maven sta nella sua capacità di fornire un processo uniforme, indipendente dall’ambiente di sviluppo.  
  Se tu stai usando Eclipse per generare un `.jar`, e un tuo collega usa NetBeans per fare la stessa cosa, ognuno sta seguendo un processo diverso. Maven unifica questo approccio, rendendo il processo di build agnostico rispetto all’IDE e, soprattutto, ripetibile.

Un altro vantaggio di Maven è l’integrazione con altri strumenti di build automation, come Hudson o Bamboo. Utilizzando Maven, possiamo facilmente collaborare con queste piattaforme per ottenere funzionalità aggiuntive come l’integrazione continua e il deployment automatico.

### Cosa fa Maven con la gestione delle dipendenze?

Immagina di voler utilizzare un framework come Spring.

Con Maven, non serve più andare a cercare manualmente i file `.jar` sul sito ufficiale, scaricarli e importarli nel progetto. Basta dichiarare la dipendenza nel file `pom.xml`, e Maven si occuperà automaticamente di recuperarla da un repository online.

Un ulteriore vantaggio è la gestione delle **_dipendenze transitive_**:

- se Spring dipende a sua volta da altre librerie, Maven sarà in grado di rilevare e scaricare anche queste, grazie alle informazioni contenute nel `pom.xml` di Spring stesso.
- con Maven possiamo anche specificare lo **scope** delle dipendenze.
  - Ad esempio, potremmo aver bisogno di una libreria solo in fase di test e non durante il deployment dell’applicazione: lo scope consente di gestire questi casi in modo preciso e condizionale.

Maven ha anche una componente di **_project management_**, semplice ma utile.  
Il cuore della configurazione di ogni progetto è il `pom.xml`, un file XML che contiene tutte le informazioni rilevanti: versione del progetto, sviluppatori coinvolti, URL del sito web del progetto, e così via.  
Possiamo anche **collegarlo a un sistema di versionamento come Git**, generare changelog in base alle differenze tra versioni, o produrre automaticamente documentazione JavaDoc.

Questo è particolarmente utile se stiamo creando una libreria che verrà utilizzata da altri sviluppatori.  
Inoltre, possiamo anche generare **report** su aspetti come la copertura del codice (ad esempio con strumenti come Clover), ottenendo informazioni preziose su cosa è stato testato e cosa no.

#### Cosa fa Maven per la standardizzazione del processo build?
Se utilizziamo Maven in diversi progetti, notiamo una grande uniformità tra loro. Questa standardizzazione si basa sull’idea delle ***convenzioni***. Non parliamo qui di design pattern complessi, ma di una struttura predefinita per i progetti. 
Maven si aspetta, ad esempio, che il codice sorgente sia sempre collocato in una determinata cartella (`src/main/java`). Questo approccio è noto come ***"convention over configuration"***: 
- invece di configurare tutto manualmente, accettiamo le convenzioni imposte da Maven, ottenendo così coerenza e riducendo la complessità.

Maven assume quindi un certo **"atteggiamento opinato"** ci suggerisce: 
- dove mettere il codice.
- come strutturare il progetto.
- in che ordine eseguire le fasi di build. 
Le fasi stesse — compilazione, test, deploy — sono standardizzate all’interno del cosiddetto ***ciclo di vita del progetto***. Questo porta a una forte coerenza tra i progetti Maven, che tendono ad assomigliarsi in termini di struttura e di processo.

#### Quali sono i componenti principali di Maven?
Componenti principali che costituiscono l’ambiente Maven, un insieme che definiamo **"Maven Landscape"**. 
Il primo componente fondamentale è il **POM**, acronimo di ***Project Object Model***. 
Il `pom.xml` è un file XML che descrive la configurazione e le personalizzazioni del nostro progetto. Contiene le dipendenze, i plugin, i profili e altre impostazioni chiave. Al suo interno troviamo anche un sistema di coordinate, composto da `groupId`, `artifactId` e `version`, che definisce univocamente ogni artefatto (come una libreria o un'applicazione) e ne consente il recupero dai repository. 
I ***repository sono un altro elemento essenziale***. Contengono le dipendenze e gli artefatti necessari ai progetti. 
Esistono due tipi principali: 
- **repository locale**, che risiede sul nostro computer.
- **repository remoto**, accessibile via rete, ad esempio tramite HTTP. 

Quando Maven scarica una dipendenza da un repository remoto, come ***Maven Central***, questa viene salvata nella cache locale. Da quel momento, se un altro progetto richiede la stessa dipendenza, Maven la userà direttamente dalla cache locale, senza bisogno di scaricarla di nuovo. Questo meccanismo migliora le prestazioni e garantisce coerenza nella gestione delle dipendenze.

Parlando di repository, un aspetto importante da sottolineare è che non servono solo per scaricare le dipendenze, ma anche i ***plugin***.

A questo punto potresti chiederti: ***cosa sono esattamente i plugin in Maven?***  
In realtà sono piuttosto semplici da comprendere. Un plugin è una ***collezione di “goals”***, ovvero azioni specifiche che vengono eseguite durante il processo di build. Se vogliamo usare un’analogia con Java, possiamo pensare a un plugin come a una classe, e ai goals come ai suoi metodi. Ad esempio, il plugin del compilatore (`compiler plugin`) ha due goals principali: uno per compilare il codice sorgente, e uno per compilare i test. Quindi, in questo contesto, i goals sono come metodi che eseguono azioni concrete per costruire il nostro progetto.

***Tutte le operazioni che Maven esegue su un progetto dalla compilazione alla creazione dell’artefatto sono svolte tramite plugin e goals.***  
Possiamo richiamarli in vari modi. Uno di questi è legarli a un ***life cycle***, ovvero un ciclo di vita che rappresenta le fasi del processo di build. Ogni fase del ciclo può attivare uno o più goals di un plugin specifico. Un’altra modalità è eseguire direttamente un goal o una fase del ciclo, bypassando il legame automatico.

#### ***Ma cos'è esattamente un life cycle?***  
Possiamo immaginarlo come una sequenza ordinata di eventi, proprio come il ciclo di vita di una farfalla: da larva a crisalide fino a farfalla. 
Nel contesto del software, Maven ha fasi come `compile`, `test`, `install` e `deploy`. Ognuna rappresenta un passo nella trasformazione del codice sorgente in un artefatto pronto all’uso. 

In Maven esistono **tre life cycle principali**:
- `clean`, usato per pulire il progetto
- `default`, che è il cuore della build e contiene la maggior parte delle fasi (circa 23)
- `site`, utilizzato per la generazione della documentazione.

***La maggior parte delle nostre operazioni avverrà all’interno del default life cycle.*** I plugin si agganciano a fasi specifiche di questo ciclo: ad esempio, durante la fase `compile`, Maven attiverà il goal di compilazione del codice sorgente.

È importante sapere che quando eseguiamo una ***fase specifica del life cycle*** (ad esempio `install`), Maven esegue **tutte le fasi precedenti** in sequenza.  
Quindi, chiamare `deploy` significa anche eseguire `compile`, `test`, `package`, `install` e così via, fino ad arrivare a `deploy`. 

In sostanza, Maven è composto da diversi elementi: il `pom.xml`, le dipendenze, i plugin, e i life cycle. La vera potenza dello strumento emerge dal modo in cui questi componenti interagiscono per completare il processo di build e produrre artefatti utilizzabili.

#### Facciamo un esempio di caso pratico!
Quando usiamo Maven — tramite linea di comando o attraverso un IDE come Eclipse — possiamo, ad esempio, eseguire il comando `mvn install`. Questo comando richiama la fase `install` del default life cycle. A questo punto, Maven inizia leggendo il file `pom.xml` per raccogliere tutte le informazioni necessarie: dati del progetto (come le coordinate `groupId`, `artifactId` e `version`), le dipendenze richieste, e i plugin da eseguire.

Per recuperare le dipendenze e i plugin, Maven utilizza il suo ***dependency manager.*** Questo componente verifica se gli artefatti sono già presenti nel **repository locale**; se non li trova, si collega ai **repository remoti** (come Maven Central o Nexus) per scaricarli. Una volta scaricati, questi artefatti vengono memorizzati localmente, così non sarà necessario riscaricarli in futuro.
- Una volta ottenute tutte le risorse necessarie, Maven avvia il ciclo di vita. Poiché abbiamo richiesto la fase `install`, Maven eseguirà anche tutte le fasi precedenti: per semplicità possiamo immaginare che esegua `compile`, poi `test`, infine `install`.
- Durante ogni fase, vengono attivati i goals corrispondenti dei plugin. Per esempio, il plugin del compilatore sarà attivato nelle fasi `compile` e `test`, mentre un altro plugin potrebbe essere legato alla fase `install`. Tutto questo avviene in modo sequenziale e automatizzato. Il `pom.xml` ***agisce come una regia, coordinando l’intero processo***: specifica le informazioni, i plugin, le fasi a cui legarli, e le dipendenze da usare.

> Questa panoramica ti dà un’idea chiara di cosa succede “sotto il cofano” quando eseguiamo una build con Maven. È utile tenerla a mente per capire il funzionamento generale del tool, specialmente man mano che il progetto cresce in complessità.

#### Spiegiamo meglio Convetion Over Configuration!
Partiamo con un esempio concreto. Apriamo il terminale e lanciamo il comando:
```bash
mvn compile
```
Maven compilerà il nostro codice e, aggiornando la struttura del progetto, vedremo la comparsa della cartella `target` contenente i file `.class`. Ma viene spontaneo chiedersi: ***come ha fatto Maven a sapere dove trovare i file*** * * `__.java__` * * ***da compilare?***  
Non abbiamo mai specificato un percorso nel nostro `pom.xml`.

La risposta è semplice: Maven si basa sul principio di ***“convention over configuration”***. In altre parole, invece di obbligarci a configurare ogni minimo dettaglio, assume che seguiremo delle convenzioni ben definite. Una di queste convenzioni è la ***struttura standard delle directory***, che Maven conosce e usa di default. 
Ecco alcune delle directory più comuni:
- `src/main/java`: dove vanno i file sorgente `.java`
- `src/main/resources`: per file di configurazione (es. `application.properties`, `log4j.xml`)
- `src/main/webapp`: la root di un’applicazione web (es. HTML, JSP, CSS, JS)
- `src/test/java`: codice dei test (JUnit, ecc.)
- `src/test/resources`: risorse usate nei test
- `LICENSE.txt`, `README.txt`: documentazione del progetto

> Questa struttura è riconosciuta automaticamente da Maven. Tuttavia, in alcuni casi — ad esempio, se stai lavorando su un progetto legacy — potresti dover personalizzare i percorsi.

#### Allora come faccio a personalizzare le Directory come fanno con i Legacy? 

Creiamo una nuova cartella `src/non-standard/java` e ci inseriamo una classe chiamata `Application2`. 
Ora dobbiamo istruire Maven a cercare i sorgenti in questa cartella. 
Apriamo il `pom.xml` e aggiungiamo una sezione `<build>` in cui specifichiamo:

```xml
<build>
  <sourceDirectory>src/non-standard/java</sourceDirectory>
  <outputDirectory>my-target</outputDirectory>
</build>
```

Questo dice a Maven di cercare i file `.java` nella nuova directory e di posizionare i `.class` in una cartella `my-target`.

#### Ma l'eredità dei file POM?
Un’altra funzionalità importante è l’***ereditarietà tra file POM***, un concetto molto simile all’ereditarietà tra classi in Java. 
Così come una classe figlia può ereditare attributi e metodi da una classe padre, anche un POM figlio può ereditare configurazioni da un POM padre.

Tutti i file POM ereditano automaticamente da un ***super POM***, incluso in Maven stesso. 
Possiamo trovarlo nel file `maven-model-builder-*.jar`, dentro la cartella `lib` di Maven. 
All’interno, c’è un file chiamato `pom-4.0.0.xml` che contiene valori predefiniti come:
- i repository centrali
- la struttura delle directory standard
- alcuni plugin
- configurazioni di report e profili

Possiamo visualizzare il POM finale — cioè quello con tutte le configurazioni ereditate dal super POM — usando:
```bash
mvn help:effective-pom
```

#### Ma il mio personale POM?
Non siamo limitati al ***super POM***. Possiamo creare un ***POM personalizzato*** che funga da genitore per altri progetti. Questo è molto utile per le organizzazioni che vogliono applicare regole e configurazioni comuni a tutti i progetti.

Per farlo:
- creiamo un nuovo progetto Maven che funge da genitore e lo installiamo nel repository locale con:
```bash
mvn install 
```
- nei progetti figli, aggiungiamo la sezione `<parent>` al `pom.xml`, specificando `groupId`, `artifactId` e `version` del POM genitore.
- rimuoviamo dal POM figlio i tag già ereditati dal padre (es. `groupId`, `version`).

Ora, se rieseguiamo `mvn help:effective-pom`, vedremo tutte le configurazioni del POM padre correttamente ereditate. Questo approccio è comodo perché centralizza configurazioni comuni: sviluppatori, licenze, URL, ecc. Basta modificare il POM padre per propagare i cambiamenti a tutti i figli.

#### Parliamo dei profili Maven?
Infine, parliamo di un’altra caratteristica fondamentale: i **profili** (`profiles`). Nella realtà lavoriamo spesso con più ambienti: sviluppo, test, produzione… e ognuno ha bisogno di configurazioni specifiche.

Con i profili possiamo ***personalizzare dinamicamente il comportamento del build*** in base all’ambiente. Per esempio, possiamo definire un profilo con ID “production” nel nostro `pom.xml`:
```xml
<profiles>
  <profile>
    <id>production</id>
    <build>
      <directory>/path/to/production/output</directory>
    </build>
  </profile>
</profiles>
```

Questo profilo sovrascrive la cartella di output predefinita solo quando viene attivato. 
Per attivarlo:
```bash
mvn package -P production
```

#### Perchè usare Archetypes su Maven?
In Maven, ***il plugin Archetype*** aiuta a creare rapidamente progetti utilizzando modelli predefiniti (archetypes), che stabiliscono una struttura di directory standard per il progetto. 
Ad esempio, con il comando `mvn archetype:generate`, possiamo creare un nuovo progetto basato sull'archetype "maven-archetype-quickstart". 
Durante la creazione, Maven ci chiede di specificare delle **coordinate** come il **Group ID** e l'**Artifact ID**, che sono utilizzati per identificare univocamente il nostro progetto.

#### Lo scope delle dipendenze Maven!
Lo **scope** di una dipendenza in Maven definisce in quali fasi del ciclo di vita del progetto la libreria sarà disponibile. 

Per esempio, la dipendenza di **JUnit** ha uno scope di tipo `test`, il che significa che viene utilizzata solo durante la fase di test e non sarà inclusa nel **runtime** del progetto.

Questa gestione permette di ottimizzare il progetto evitando di includere librerie non necessarie nelle fasi di esecuzione. Ad esempio, non includendo JUnit nel runtime, si riduce la dimensione finale del progetto (come il file JAR). Maven supporta altri tipi di scope come:
- **compile**: la libreria è disponibile in tutte le fasi.
- **provided**: la libreria è disponibile durante il build, ma non nel runtime (ad esempio, quando il contenitore web fornisce una libreria).
- **runtime**: la libreria è necessaria solo al runtime.
- **system**: la libreria è fornita dal sistema, non scaricata da un repository.
- **import**: usato per importare dipendenze da un altro `pom.xml`.
- **test**: durante i test. 

#### Gestione delle dipendenze transitive in Maven
Una delle caratteristiche più potenti è la gestione delle ***dipendenze transitive***. 

Ciò significa che quando una libreria A dipende da una libreria B, e B a sua volta dipende da una libreria C, Maven si occupa di recuperare anche la libreria C senza che l'utente debba specificarlo manualmente. 
Questo è molto utile quando si lavora con librerie complesse, come **Spring**, che ha una lunga lista di dipendenze. 

Grazie a Maven, non è necessario gestire manualmente tutte le versioni di queste dipendenze. Maven risolve automaticamente ogni dipendenza e la scarica da uno dei suoi repository remoti.

Maven recupera le dipendenze da ***repository remoti***, come il ***Maven Central Repository***, che contiene un vasto numero di artefatti. 

Ogni volta che specifichiamo una dipendenza nel nostro file `pom.xml`, Maven cerca l'artefatto richiesto in uno di questi repository. Se l'artefatto non è disponibile nel repository centrale, possiamo configurare Maven per cercarlo in repository aggiuntivi.

Per fare questo, dobbiamo modificare il file `settings.xml` di Maven. 

Questo file si trova nella directory di installazione di Maven e contiene configurazioni globali. Aggiungendo un ***profile*** con un nuovo repository, possiamo dire a Maven dove cercare specifiche dipendenze. 

Ad esempio, per aggiungere il ***repository remoto Spring***, creiamo un profile chiamato `spring_remote` e specifichiamo l'ID e l'URL del repository Spring. Una volta fatto ciò, Maven saprà cercare anche in questo repository oltre che nel central repository.
