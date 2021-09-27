package com.self.ws;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.camel.Exchange;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.spi.DataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JacksonDataFormatWrapper implements DataFormat{
	private JacksonDataFormat jsonDataFormat = null;
	private static final Logger LOG = LoggerFactory.getLogger(JacksonDataFormatWrapper.class);
	
	public JacksonDataFormatWrapper(Class<?> claz) {
		jsonDataFormat = new JacksonDataFormat(claz);
	}
	
	@Override
	public void start() {
		jsonDataFormat.start();		
	}

	@Override
	public void stop() {
		jsonDataFormat.stop();		
	}

	@Override
	public void marshal(Exchange exchange, Object graph, OutputStream stream) throws Exception {
		LOG.debug("Got Body Object:{}",graph);
		LOG.debug("Got Exchange Body Object:{}",exchange.getIn().getBody());
		jsonDataFormat.marshal(exchange,graph,stream);		
	}

	@Override
	public Object unmarshal(Exchange exchange, InputStream stream) throws Exception {
		return jsonDataFormat.unmarshal(exchange, stream);
	}

}
