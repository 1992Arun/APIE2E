package org.step;


import io.cucumber.java.Before;

public class HooksClass {
	
	
	
	 
	@Before("@put or @delete")
	public void beforeMethod() {
				
		StepDefinition ss = new StepDefinition();
		
		ss.i_add_the_baseURI();

		ss.payload_of_and_needs_to_be_added_for_POST_Request("Login not working", "Unable to login");
		
		ss.call_api_with_HTTP_method("createissue", "POST");
		

	}

}
