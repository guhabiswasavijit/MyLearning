package com.self.ws;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataAggregationStrategy implements AggregationStrategy {

	private static final Logger LOG = LoggerFactory.getLogger(DataAggregationStrategy.class);
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if (newExchange.getException() != null) {
			LOG.debug("Exception occured:{}",newExchange.getException().getMessage());
			return oldExchange;
		}
		if (oldExchange == null) {
			return newExchange;
		}
		String body = newExchange.getIn().getBody(String.class);
		LOG.debug("NewExchange Body:{}",body);
		String existing = oldExchange.getIn().getBody(String.class);
		LOG.debug("OldExchange Body:{}",existing);
		oldExchange.getIn().setBody(existing + "\n" + body);
		return oldExchange;
	}

}
