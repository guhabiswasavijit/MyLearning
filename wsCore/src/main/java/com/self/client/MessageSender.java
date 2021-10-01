package com.self.client;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Component(value = "messageSender")
public class MessageSender {
	
	@Value("${camel.rabbitmq.exchange}")
	private String exchange;
	@Value("${camel.rabbitmq.routingKey}")
	private String routingKey;
	@Autowired
	private ConnectionFactory rabbitConnectionFactory;
	
	public boolean send() throws IOException, TimeoutException {
		Connection conn = rabbitConnectionFactory.newConnection();
		Channel channel = conn.createChannel();
		//channel.queueBind(queue, exchange, routingKey);
		String message = "Hello World!";
		channel.basicPublish(exchange, routingKey, null, message.getBytes());
		
		return true;
	}
	

}
