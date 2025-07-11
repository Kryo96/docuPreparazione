# Configurazione Driver JDBC in WildFly

## 1. Installazione Driver DB2

### Crea la struttura delle directory:
```bash
mkdir -p $WILDFLY_HOME/modules/system/layers/base/com/ibm/db2/main
```

### Copia il driver:
```bash
cp $JAVA_PROJECT_DIRECTORY/wildfly-drivers/jcc.jar $WILDFLY_HOME/modules/system/layers/base/com/ibm/db2/main/
```

### Crea il file module.xml:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.3" name="com.ibm.db2">
    <resources>
        <resource-root path="jcc.jar"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
        <module name="sun.jdk"/>
    </dependencies>
</module>
```

## 2. Installazione Driver AS400 (JT400)

### Crea la struttura delle directory:
```bash
mkdir -p $WILDFLY_HOME/modules/system/layers/base/com/ibm/as400/main
```

### Copia il driver:
```bash
cp $JAVA_PROJECT_DIRECTORY/wildfly-drivers/jt400.jar $WILDFLY_HOME/modules/system/layers/base/com/ibm/as400/main/
```

### Crea il file module.xml:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.3" name="com.ibm.as400">
    <resources>
        <resource-root path="jt400.jar"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
        <module name="java.logging"/>
        <module name="sun.jdk"/>
    </dependencies>
</module>
```

## 3. Registrazione Driver in standalone.xml

Aggiungi nella sezione `<drivers>` del file `standalone.xml`:

```xml
<subsystem xmlns="urn:jboss:domain:datasources:6.0">
    <datasources>
        <!-- I tuoi datasource qui -->
    </datasources>
    <drivers>
        <!-- Driver DB2 -->
        <driver name="db2" module="com.ibm.db2">
            <driver-class>com.ibm.db2.jcc.DB2Driver</driver-class>
            <xa-datasource-class>com.ibm.db2.jcc.DB2XADataSource</xa-datasource-class>
        </driver>
        
        <!-- Driver AS400 -->
        <driver name="as400" module="com.ibm.as400">
            <driver-class>com.ibm.as400.access.AS400JDBCDriver</driver-class>
            <xa-datasource-class>com.ibm.as400.access.AS400JDBCXADataSource</xa-datasource-class>
        </driver>
    </drivers>
</subsystem>
```

## 4. Verifica Installazione

Avvia WildFly e controlla i log:
```bash
$WILDFLY_HOME/bin/standalone.sh
```

Dovresti vedere nei log:
```
INFO  [org.jboss.as.connector.subsystems.datasources] Deployed JDBC driver: db2
INFO  [org.jboss.as.connector.subsystems.datasources] Deployed JDBC driver: as400
```

## 5. Test delle Connessioni

### Test DB2 (Development):
```bash
# Imposta variabili d'ambiente
export APP_ENV=development
export DB2_HOST=localhost
export DB2_PORT=50000
export DB2_DATABASE=TESTDB
export DB2_USER=db2inst1
export DB2_PASSWORD=password

# Testa con gradle
./gradlew testDB2Connection
```

### Test AS400 (quando disponibile):
```bash
# Imposta variabili d'ambiente
export APP_ENV=production
export AS400_HOST=your-as400-host
export AS400_USER=youruser
export AS400_PASSWORD=yourpass
export AS400_LIBRARIES=YOURLIB

# Testa con gradle
./gradlew testAS400Connection -Pas400.host=$AS400_HOST -Pas400.user=$AS400_USER -Pas400.password=$AS400_PASSWORD
```

## Note Importanti

1. **Entrambi i driver devono essere installati in WildFly** anche se ne usi solo uno alla volta
2. **Non includere i driver nel WAR** (usa `providedCompile` in Gradle)
3. **La selezione del driver avviene a runtime** basandosi sulla variabile `app.env`
4. **Nessuna modifica al codice** necessaria per passare da DB2 ad AS400
