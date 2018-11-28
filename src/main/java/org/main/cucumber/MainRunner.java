package org.main.cucumber;

//import cucumber.api.CucumberOptions;
//import cucumber.api.SnippetType;

//import runner.RunTest;

/*@CucumberOptions(features =  "/gherkin_new.feature" , 
glue = { " " }, 
monochrome = true,
dryRun = true,
tags = {"@tamilnadu", "not @kerala"}, 
snippets = SnippetType.CAMELCASE)
*/
public class MainRunner {

	public static void main(String[] args) {
		
		String[] argv= {"-g", "steps", System.getProperty("user.dir")+"/gherkin_new.feature"};
		ClassLoader c= Thread.currentThread().getContextClassLoader();
		cucumber.api.cli.Main.run(argv, c);
	}

}
