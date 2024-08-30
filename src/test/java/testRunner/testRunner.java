package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/features/pizzahut.feature",
		glue = "stepsDef",
		plugin= {"pretty","html:cucumber-reports.html"},
		dryRun=false,
		monochrome=true,
		tags="@Smoke"
		)
public class testRunner {

}
