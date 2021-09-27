package com.self.ws;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.camel.Exchange;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class BindyCsvDataFormatWrapper implements DataFormat {

	private BindyCsvDataFormat bindy = null;
	private static final Logger LOG = LoggerFactory.getLogger(BindyCsvDataFormatWrapper.class);
	
	public BindyCsvDataFormatWrapper(Class<?> claz) {
		bindy = new BindyCsvDataFormat(claz);
	}
	@Override
	public void start() {
		bindy.start();
	}

	@Override
	public void stop() {
		bindy.stop();
	}

	@Override
	public void marshal(Exchange exchange, Object graph, OutputStream stream) throws Exception {
		bindy.marshal(exchange, graph, stream);		
	}

	@Override
	public Object unmarshal(Exchange exchange, InputStream stream)  {
		String result = null;
		try {
			result = IOUtils.toString(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Object tranformedObject = null;
		try {
			tranformedObject = bindy.unmarshal(exchange, stream);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			LOG.error("Fatal Exception:{}",sw);
			LOG.debug("Exception body:{}",result);
			exchange.setProperty("ExchangeFailed",true);
			String[] data = result.split(",");
			ErrorDataBean error = new ErrorDataBean();
			error.setErrorMessage(sw.toString());
			error.setFailed(true);
			error.setRecordNo(data[0]);
			tranformedObject = error;
		}
		return tranformedObject;
	}

}
