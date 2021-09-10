package com.self.client;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self.wsIntegration.types.UploadRequest;
import com.wsintegration.services.fileuploader.FileUploadFault;
import com.wsintegration.services.fileuploader.FileUploaderEndpoint;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
    	@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("client-ApplicationContext.xml");
    	FileUploaderEndpoint client = (FileUploaderEndpoint)context.getBean("fileUploaderServiceClient");
    	UploadRequest uploadRequest = new UploadRequest();
    	try {
    		File inputFile = new File("C:\\SampleData\\employees.csv");
    		FileDataSource fileDataSource = new FileDataSource(inputFile);
    	    DataHandler dataHandler = new DataHandler(fileDataSource);
    	    uploadRequest.setTitle("Desert");
    	    uploadRequest.setFileData(dataHandler);
			client.uploadFile(uploadRequest);
		} catch (FileUploadFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("Successfully Called Service");
        SpringApplication.run(App.class, args);
    }
}