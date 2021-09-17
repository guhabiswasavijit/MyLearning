package com.self.ws;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ctc.wstx.exc.WstxUnexpectedCharException;
import com.self.wsIntegration.types.UploadRequest;
import com.self.wsIntegration.types.UploadResonse;
@Component
public class FileUploadRouter extends RouteBuilder {

	private static final Logger LOG = LoggerFactory.getLogger(FileUploadRouter.class);

	@Override
	public void configure() throws Exception {
		
		onException(WstxUnexpectedCharException.class).process(new Processor() {
            public void process(Exchange exchange) throws Exception {
            	LOG.debug("Got error at:"+body());
            }
        }).log("Handled error").handled(true);
		
		BindyCsvDataFormat bindy = new BindyCsvDataFormat(EmployeeRecord.class);

		JaxbDataFormat requestDataFormat = new JaxbDataFormat();
    	JaxbDataFormat responseDataFormat = new JaxbDataFormat();
		JAXBContext requestContext = null;
		JAXBContext responseContext = null;
		try {
			requestContext = JAXBContext.newInstance(UploadRequest.class);
			responseContext = JAXBContext.newInstance(UploadResonse.class);
		} catch (JAXBException ex) {
			LOG.error("Exception occured:",ex);
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
				
				LOG.debug("Copy file Successful");
				StringBuilder textBuilder = new StringBuilder();
			    try (Reader reader = new BufferedReader(new InputStreamReader
			      (uploadRequest.getFileData().getInputStream(), Charset.forName(StandardCharsets.UTF_8.name())))) {
			        int c = 0;
			        while ((c = reader.read()) != -1) {
			            textBuilder.append((char) c);
			        }
			    }
			    exchange.getIn().setBody(textBuilder.toString());
			}        	
        }).log("About to process file :"+body())
		.split(body().tokenize("/n"))
		.unmarshal(bindy)
		.log("Finished Transformation:"+body())
		.end()
		.to("bean:wsSuccessResponseBuilder?method=buildResponse")
        .marshal(responseDataFormat);;

	}
}
