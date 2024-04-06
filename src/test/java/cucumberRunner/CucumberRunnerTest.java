package cucumberRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/resources/features"},
                 glue={"stepDefinitions"},
        plugin={"pretty","html:target/cucumber-reports/Report.html",
        "json:target/cucumber-reports/Report.json",
        "junit:target/cucumber-reports/Report.xml"},
        tags="@e2e"
)
public class CucumberRunnerTest {
}
