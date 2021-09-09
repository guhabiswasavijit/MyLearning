package com.self.ws;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.self.wsIntegration.types.UploadRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class MySpringBootRouter extends RouteBuilder {
	
	private static final Logger LOG = LoggerFactory.getLogger(MySpringBootRouter.class);
	
	private static String outputDirectory = "";
	
	@Value("${out.directory}")
	public void setOutputDirectory(String t_outputDirectory){
		outputDirectory = t_outputDirectory;
	}

    @Override
    public void configure() {
        from("cxf:bean:cxfFileUploader?dataFormat=PAYLOAD&wsdlURL=WSIntegrationDemo.wsdl")
        .log(LoggingLevel.DEBUG, "About to Write file to:"+outputDirectory)
        .process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				LOG.debug("Getting Output Directory:{}",outputDirectory);
				UploadRequest uploadRequest = exchange.getIn().getBody(UploadRequest.class);
				String fileName = uploadRequest.getTitle();
				StringBuilder filePath = new StringBuilder(outputDirectory);
				filePath.append(fileName);
				filePath.append(".csv");
				File targetFile = new File(filePath.toString());
				LOG.debug("Creating file:{}",filePath.toString());
				InputStream initialStream = uploadRequest.getFileData().getInputStream();
				java.nio.file.Files.copy(initialStream, targetFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
				LOG.debug("Copy file Successful");
				initialStream.close();
				
			}        	
        }).log("Writing file to:"+outputDirectory);
    }

}
