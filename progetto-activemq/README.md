Ciao questo è un semplice progetto che invia messaggi da producer a message broker che li gira verso il consumer.

Praticamente è stata una fatica unica non ho capito in pieno come funziona il producer domani ci guardo meglio..

Come prima cosa metti su apache activemq con comando: ```cd /apache-activemq-6.1.6/bin && ./activemq start```
Poi il processo di activemq sta fisso sulla porta 61616

dopo accendi il producer quindi entri sulla cartella e starti il progetto producer
dopo accendi il consumer quindi entri sulla cartella e starti il progetto consumer

orva vai su http://localhost:8161/admin/queues.jsp  credenziali admin:admin

ora vai su localhost:80/index . C'è un bottone "Invia Messaggio", cliccalo
ora vai sui log del consumer e ci dovrebbe essere loggato il messaggio ricevuto...
inoltre dalla console admin vedi quanti messaggi hai in queue e quanti letti...

molto base nei prossimi giorni cerco di capirci qualcosa in più.

 
