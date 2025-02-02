package org.utility;

public enum APIRequests {
	
	createissue("rest/api/2/issue/"),
	updateissue("rest/api/2/issue/"),
	deleteissue("rest/api/2/issue/"),
	getissue("rest/api/2/search?jql=assignee=Arun");
		
	String resource;
	
	APIRequests(String resource){
		
		this.resource= resource;
		
	}
	
	
	public String getResource() {
		
		return resource;
	}

}
