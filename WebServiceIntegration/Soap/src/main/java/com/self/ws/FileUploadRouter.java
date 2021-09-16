package com.self.ws;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.self.wsIntegration.types.UploadRequest;
import com.self.wsIntegration.types.UploadResonse;

public class FileUploadRouter extends RouteBuilder {

	private static final Logger LOG = LoggerFactory.getLogger(FileUploadRouter.class);

	@Override
	public void configure() throws Exception {
		BindyCsvDataFormat bindy = new BindyCsvDataFormat(EmployeeRecord.class);

		JaxbDataFormat requestDataFormat = new JaxbDataFormat();
    	JaxbDataFormat responseDataFormat = new JaxbDataFormat();
		JAXBContext requestContext = null;
		JAXBContext responseContext = null;
		try {
			requestContext = JAXBContext.newInstance(UploadRequest.class);
			responseContext = JAXBContext.newInstance(UploadResonse.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		requestDataFormat.setContext(requestContext);
		responseDataFormat.setContext(responseContext);
        from("cxf:bean:cxfFileUploader?dataFormat=PAYLOAD&wsdlURL=WSIntegrationDemo.wsdl")
        .log(LoggingLevel.DEBUG, "About to Process Exchange")
        .unmarshal(requestDataFormat)
        .process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				LOG.debug("About to process message");
				UploadRequest uploadRequest = exchange.getIn().getBody(UploadRequest.class);
				String fileName = uploadRequest.getTitle();
				LOG.debug("Processing file:{}",fileName);
				exchange.getIn().setBody(uploadRequest.getFileData());
				LOG.debug("Copy file Successful");
			}        	
        }).log("About to process file :"+body())
		.split(body().tokenize("/n"))
		.streaming().unmarshal(bindy)
		.log("Finished Transformation:"+body()).end();

	}
}
