package org.utility;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Builder extends UtilityClass {
	
	public static RequestSpecification gerRequestSpec(){
		
		PrintStream p = null;
		
		if(p==null){
		
		try {
			
			 p = new PrintStream("log.txt");
			
			
			
		} catch (FileNotFoundException e) {
			
		}
		
		}
		
		RequestSpecBuilder req= new RequestSpecBuilder();
				
		req.setBaseUri(getProperty("BaseURI"));
		
		req.setContentType(ContentType.JSON);
		
		req.addFilter(RequestLoggingFilter.logRequestTo(p));
		
		req.addFilter(ResponseLoggingFilter.logResponseTo(p));
				
		PreemptiveBasicAuthScheme auth =new PreemptiveBasicAuthScheme ();
		
		auth.setUserName(getProperty("UserName"));
		
		auth.setPassword(getProperty("Password"));
		
		req.setAuth(auth);
		
		RequestSpecification build = req.build();
			
		return build;
		
	}
	
	
public static ResponseSpecification getResponseSpec(int code){
		
		
	ResponseSpecification res= new ResponseSpecBuilder()
		
		
		.expectStatusCode(code).build();

	
		return res;
		
	}



public static void main(String[] args) {
	
	
	
}


}
