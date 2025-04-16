package com.lance.mq.producer.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lance.mq.producer.info.OrderInfo;

@Component
public class ProducerService {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private ActiveMQQueue helloQueue;

	/**
	 * Invia un messaggio testuale alla coda
	 */
	public void sendTextQueueMessage(final String message) {
		jmsTemplate.send(helloQueue, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

	/**
	 * Invia un messaggio e riceve la risposta
	 */
	public String sendTextQueueMessageAndReceive(final String message) throws JMSException {
		Message replyMessage = jmsTemplate.sendAndReceive(helloQueue, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});

		TextMessage textMessage = (TextMessage) replyMessage;
		return textMessage.getText();
	}

	/**
	 * Invia un oggetto OrderInfo e riceve la risposta
	 */
	public String sendObjectQueueMessage(final OrderInfo info) throws JMSException {
		Message message = jmsTemplate.sendAndReceive(helloQueue, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(info);
			}
		});

		TextMessage textMessage = (TextMessage) message;
		return textMessage.getText();
	}
}
