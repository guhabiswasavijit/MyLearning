package com.self.ws;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorPredicate {
	private static final Logger LOG = LoggerFactory.getLogger(ErrorPredicate.class);
	public boolean isErrorOccured(Exchange exchange) {
		LOG.debug("Inside Predicate");
		Boolean isFailed = exchange.getProperty("ExchangeFailed") != null;
		if(isFailed) {
			LOG.debug("Got Exception Inside Predicate");
			ErrorDataBean errorData = exchange.getIn().getBody(ErrorDataBean.class);
			return errorData.isFailed();
		}
		return false;
	}

}
