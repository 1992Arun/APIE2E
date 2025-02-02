package org.test;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.utility.UtilityClass;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources", 

                  glue="org.step",
                  dryRun=false, 
                  monochrome=true,
                  plugin= {
                  		   
                		  "json:src\\test\\resources\\Reports\\JSON\\jsonReport.json",
                		  "rerun:src\\test\\resources\\FailedScenarios\\Failed.txt"
                		  
                		  
                           }
                  
		
		 )



public class TestRunner {
	
	@AfterClass
	public static void after() {
		
		UtilityClass.getJVMReport("src\\test\\resources\\Reports\\JSON\\jsonReport.json");
		
	}

}
