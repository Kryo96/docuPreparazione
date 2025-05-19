Hello
docker usage: 
``` docker-compose build & docker-compose up -d ```


### Creazione Jar 
 ```shell
maven clean install
```
 
### Creazione connessione immagini sql server + back-front

```shell 
docker network create mynetwork
```

Dopo aver creato la rete custom bisogna collegarla all'immagine di sql server 

```shell 
docker network connect mynetwork sql_server
```
Nel docker-compose invece va aggiunta la parte di env e l'abilitazine a rete esterna 

```yaml
    networks:
      - app-network
      - pocnetwork
    environment:
      SPRING_DATASOURCE_URL: "jdbc:sqlserver://nomeimmaginedocker:1433;databaseName=nomedb;encrypt=true;trustServerCertificate=true;loginTimeout=30;"
      SPRING_DATASOURCE_USERNAME: nomeutente
      SPRING_DATASOURCE_PASSWORD: psw

networks:
  app-network:
    driver: bridge
  pocnetwork:
    external: true
```


