/**
 * Ci sono due metodi per fare in modo che la classe ActiveMQConnectionFactory si inizializzi
 * 1) aggiungere Bean ActiveMQConnectionFactory tipo:
     * @Bean
     * public ActiveMQConnectionFactory activeMQConnectionFactory() {
     *     ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
     *     factory.setBrokerURL("tcp://localhost:61616");
     *     factory.setUserName("admin"); // se necessario
     *     factory.setPassword("admin"); // se necessario
     *     return factory;
     * }
     * poi passare alle classi successive direttamente la oggetto ActiveMQConnectionFactory
 * 2) aggiungere Bean automaticamente da configurazione contesto Spring tipo:
     * in pom.xml
     * <dependency>
     *     <groupId>org.springframework.boot</groupId>
     *     <artifactId>spring-boot-starter-activemq</artifactId>
     * </dependency>
     *
     * in application.properties
     * spring.activemq.broker-url=tcp://localhost:61616
     * spring.activemq.user=admin
     * spring.activemq.password=admin
 * punto due ha dato problemi non funziona provo punto 1
 */


package com.lance.mq.consumer.config;

import javax.jms.Queue;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

/**
 * Configurazione manuale di ActiveMQ.
 * Utilizza ActiveMQConnectionFactory e CachingConnectionFactory definiti manualmente.
 */
@Configuration
@Description(value = "ActiveMQ Configuration")
public class ActiveMQConfig {

	public static final String QUEUE_HELLO = "queue.hello";

	// Creazione del bean per la Queue
	@Bean(name="helloQueue")
	public Queue helloQueue() {
		return new ActiveMQQueue(QUEUE_HELLO);
	}

	// Creazione del MessageListenerAdapter
	@Bean(name="textMessageListenerAdapter")
	public MessageListenerAdapter messageListenerAdapter() {
		MessageListenerAdapter adapter = new MessageListenerAdapter();
		adapter.setMessageConverter(new SimpleMessageConverter());
		adapter.setDelegate(textConsumerListener());
		return adapter;
	}

	// Creazione di ActiveMQConnectionFactory manuale
	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL("tcp://localhost:61616"); // Inserisci l'URL del broker ActiveMQ
		connectionFactory.setUserName("admin"); // Se necessario
		connectionFactory.setPassword("admin"); // Se necessario
		return connectionFactory;
	}

	// Creazione del CachingConnectionFactory utilizzando ActiveMQConnectionFactory
	@Bean
	public CachingConnectionFactory cachingConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
		return new CachingConnectionFactory(activeMQConnectionFactory);
	}

	// Creazione del MessageListenerContainer che gestisce la concorrenza
	@Bean
	public MessageListenerContainer messageListenerContainer(CachingConnectionFactory cachingConnectionFactory) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(cachingConnectionFactory); // Usa il CachingConnectionFactory
		container.setDestination(helloQueue());
		container.setMessageListener(messageListenerAdapter());
		container.setConcurrency("10-50"); // Gestisce il numero di thread per i consumatori
		return container;
	}

	// Creazione del ConsumerListener
	@Bean
	public ConsumerListener textConsumerListener() {
		return new ConsumerListener();
	}
}
