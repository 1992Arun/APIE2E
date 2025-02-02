package org.utility;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.postreqPojo.Fields;
import org.postreqPojo.Issuetype;
import org.postreqPojo.PostRequest;
import org.postreqPojo.Project;
import org.putPojo.Add;
import org.putPojo.Comment;
import org.putPojo.Parent;
import org.putPojo.Update;

import io.restassured.response.Response;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class UtilityClass {
	
	
	public static int getResponseCode(Response response) {
		
		
		return response.getStatusCode();
		
	}
	
	
	
public static String getResponseBody(Response response) {
		
		
		return response.getBody().asString();
		
	}


 public static String getProperty(String value) {
	 
	 Properties p = null;
	 
	 try {
		 
		FileReader f = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\Property\\Config.properties");
		 
		 p = new Properties();
		 
		
			p.load(f);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
 
	 String values = p.getProperty(value);

	return values;
	 
 
 }
 
 public static PostRequest postRequestBody(String Desc, String summery)  {
	 
	
	 
	 Issuetype is = new Issuetype();
	 
	 is.setName("Bug");
	 
	 Project project = new Project();
	 
	 project.setKey("SCRUM");
	 
	 Fields f = new Fields();
	 
	 f.setDescription("Product is not listed under the Electronis created with RestAssued");
	 
	 f.setSummary("RestAssured builderClass practice");
	 
	 f.setProject(project);
	 
	 f.setIssuetype(is);
	 
	 PostRequest p = new PostRequest();
	 
	 p.setFields(f);
	 
//	 ObjectMapper om = new ObjectMapper();
//	 
//	
//	 System.out.println( om.writeValueAsString(p));
	 
	return p;
	 
	 
 }
 

	public static Parent putRequestBody() {
		
		Add a = new Add();
		
		a.setBody(getProperty("Putbody"));
		
		Comment c = new Comment();
		
		List<Comment> lis = new ArrayList();
		
		lis.add(c);
		
		c.setAdd(a);
		
		Update u = new Update();
				
				u.setComment(lis);
				
				Parent p = new Parent();
				
				p.setUpdate(u);
				
				return p;
		
		
	}
	
	public static void getJVMReport(String Json) {
		
		File f = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Reports");
		
		Configuration conf = new Configuration(f, "JIRA API");
		
		conf.addClassifications("UserName", System.getProperty("user.name"));
		
		List<String> li = new LinkedList();
		
		li.add(Json);
		
		ReportBuilder rb= new ReportBuilder(li, conf);
		
		rb.generateReports();
		
		
	}

	
	public static void main(String[] args) {
		
	
		
		System.out.println(getProperty("BaseURI"));
		
	}

}
