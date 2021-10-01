package com.self.client;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RabbitMqClient {

	public static void main(String[] args) throws IOException, TimeoutException {
	
		ConfigurableApplicationContext context = SpringApplication.run(RabbitMqClient.class, args);
		MessageSender sender = (MessageSender)context.getBean("messageSender");
		sender.send();

	}

}
