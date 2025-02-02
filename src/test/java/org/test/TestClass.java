package org.test;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Ignore;
import org.junit.Test;
import org.postresPojo.Parent;
import org.utility.APIRequests;
import org.utility.Builder;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestClass extends Builder {

	
	public static void main(String[] args) {
		
		APIRequests req = APIRequests.deleteissue;
		
		System.out.println(req.getResource());
		
		
		
	}
	
	
	@Ignore
	@Test() 
	public void test1() {

		Response response = RestAssured.given().spec(gerRequestSpec()).when().get("rest/api/2/search?jql=assignee=Arun")

				.then().assertThat()

				// .body("total", Matchers.equalTo(127))

				.body("issues[1].self", Matchers.containsString("https://arun12.atlassian.net/rest/api/2/issue"))

				.spec(getResponseSpec(200))

				.extract().response();

		
	}

	@Ignore
	@Test
	public void test2() throws ParseException {

		Response response = RestAssured.given()

				.spec(gerRequestSpec()).body(postRequestBody("","")).when().post("rest/api/2/issue/").then().assertThat()
				.spec(getResponseSpec(201))
				.body("self", Matchers.containsString("https://arun12.atlassian.net/rest/api/2/issue"))
				.body("key", Matchers.containsString("SCRUM"))
				.extract().response();
		
		Parent data = response.as(Parent.class);

		response.then().assertThat().body("key", Matchers.containsString(data.getKey()));
		
		response.then().assertThat().body("self", Matchers.containsString(data.getSelf()));
		
		JSONParser jp = new JSONParser();
		
		String responseBody = getResponseBody(response);
		
		 Object parse = jp.parse(responseBody);
		 
		 JSONObject jo =(JSONObject)parse;		

		System.out.println(jo.get("self"));

		JsonPath p = new JsonPath(getResponseBody(response));
		
		response.then().assertThat().body("self", Matchers.containsString(p.getString("self")));


	}
	
	@Ignore
	@Test
	public void test3() {
				
		Response put = RestAssured.given()
		.spec(gerRequestSpec())
		.body(putRequestBody())
		.when().put("rest/api/2/issue/SCRUM-89")
		.then().assertThat().spec(getResponseSpec(204)).extract().response();
		
		System.out.println(getResponseCode(put));
		
	}

}
