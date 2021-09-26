package com.self.ws;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataAggregationStrategy implements AggregationStrategy {
	private static final Logger LOG = LoggerFactory.getLogger(DataAggregationStrategy.class);
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		String newBody = newExchange != null?newExchange.getIn().getBody(String.class):null;
		LOG.debug("New exchange body:{}",newBody);
		String oldBody = oldExchange!= null?oldExchange.getIn().getBody(String.class):null;
		LOG.debug("Old exchange body:{}",oldBody);
		if (newExchange.getException() != null) {
			LOG.debug("Exception occured:{}", newExchange.getException().getMessage());
			newExchange.setException(null);
			newExchange.getIn().setBody("Failed");			
		}
		/*
		 * String newBody = newExchange.getIn().getBody(String.class);
		 * LOG.debug("New exchange body:{}",newBody); if (oldExchange == null) {
		 * Set<String> set = new HashSet<String>(); set.add(newBody);
		 * newExchange.getIn().setBody(set); return newExchange; } else {
		 * 
		 * @SuppressWarnings("unchecked") Set<String> set =
		 * Collections.checkedSet(oldExchange.getIn().getBody(Set.class), String.class);
		 * LOG.debug("Old exchange body:{}",set); set.add(newBody); return oldExchange;
		 * }
		 */
		return newExchange;
	}

}
