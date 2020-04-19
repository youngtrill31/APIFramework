package cucumber.options;

import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(strict=true, features="src/test/java/features", glue = {"stepDefinitions"})
public class TestRunner {
	
}
