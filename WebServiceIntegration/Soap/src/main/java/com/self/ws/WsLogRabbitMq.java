package com.self.ws;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Component("wsLogRabbitMq")
public class WsLogRabbitMq {
	private static final Logger LOG = LoggerFactory.getLogger(WsLogRabbitMq.class);
	@Value("${camel.rabbitmq.exchange}")
	private String exchange;
	@Value("${camel.rabbitmq.routingKey}")
	private String routingKey;
	@Autowired
	private ConnectionFactory rabbitConnectionFactory;
	
	public boolean send(Exchange camelExchange) throws IOException, TimeoutException {
		Connection conn = rabbitConnectionFactory.newConnection();
		Channel channel = conn.createChannel();
		//channel.queueBind(queue, exchange, routingKey);
		String message = camelExchange.getIn().getBody(String.class);
		LOG.debug("WsLogRabbitMq Got error message:{}",message);
		channel.basicPublish(exchange, routingKey, null, message.getBytes());
		
		return true;
	}
}
