package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {
	
	public static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException{
		
		if(req==null){
		PrintStream log = new PrintStream(new FileOutputStream("log.text"));
		
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI")).
				addFilter(RequestLoggingFilter.logRequestTo(log)).
				addFilter(ResponseLoggingFilter.logResponseTo(log)).
				build();
		
		return req;}
		return req;
	}
	
	public String getGlobalValue(String key) throws IOException{
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties");
		props.load(fis);
		
		return props.getProperty(key);
	}
	

}
