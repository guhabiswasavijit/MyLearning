package com.self.ws;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataAggregationStrategyRabbitMQ implements AggregationStrategy {
	private static final Logger LOG = LoggerFactory.getLogger(DataAggregationStrategyRabbitMQ.class);
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		String newBody = newExchange != null?newExchange.getIn().getBody(String.class):null;
		LOG.debug("New exchange body:{}",newBody);
		String oldBody = oldExchange!= null?oldExchange.getIn().getBody(String.class):null;
		LOG.debug("Old exchange body:{}",oldBody);
		if (newBody != null && newExchange.getException() != null) {
			newExchange.setProperty("ExchangeFailed",true);
			String errorMessage = newExchange.getException().getMessage();
			LOG.debug("Exception occured:{}",errorMessage);
			newExchange.setException(null);
			String[] data = newBody.split(",");
			ErrorDataBean error = new ErrorDataBean();
			error.setErrorMessage(errorMessage);
			error.setFailed(true);
			error.setRecordNo(data[0]);
			newExchange.getIn().setBody(error);			
		}
		return newExchange;
	}

}

