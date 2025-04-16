package com.lance.mq.producer.web;

import java.util.Date;
import java.util.Random;

import javax.jms.JMSException;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lance.mq.producer.info.OrderInfo;
import com.lance.mq.producer.service.ProducerService;

@Controller
public class IndexController {

	@Autowired
	private ProducerService producerService;

	@RequestMapping(value = { "/", "", "index" })
	public String index() {
		return "index";
	}

	@ResponseBody
	@RequestMapping("click")
	public void click() {
		String message = "Hello Queue Message. Date: " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");

		// Invia il messaggio senza aspettare risposta
		producerService.sendTextQueueMessage(message);

		// Invia il messaggio e riceve risposta
		try {
			String result = producerService.sendTextQueueMessageAndReceive(message);
			System.out.println("Result: " + result + ", isSuccess: " + result.equals("SUCCESS"));
		} catch (JMSException e) {
			e.printStackTrace();
			System.err.println("Errore nell'invio alla coda (sendTextQueueMessageAndReceive): " + e.getMessage());
		}

		// Invia un oggetto OrderInfo e riceve risposta
		try {
			OrderInfo info = new OrderInfo(new Random().nextInt(100), message, new Random().nextFloat());
			String result = producerService.sendObjectQueueMessage(info);
			System.out.println("Result (oggetto): " + result + ", isSuccess: " + result.equals("SUCCESS"));
		} catch (JMSException e) {
			e.printStackTrace();
			System.err.println("Errore nell'invio alla coda (sendObjectQueueMessage): " + e.getMessage());
		}
	}
}
