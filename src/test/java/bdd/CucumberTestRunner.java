package bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber"},
        glue = {"bdd.stepdefinitions", "bdd.config"},
        features = "src/test/resources/features"
)
public class CucumberTestRunner {
}