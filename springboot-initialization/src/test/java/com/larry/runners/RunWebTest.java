package com.larry.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features.web"},
        glue = {"com.larry.steps", "com.larry.hooks"},
        plugin = {
                "pretty",
                "html:build/cucumber/cucumber-report-html.html",
                "json:build/cucumber/cucumber-report-json.json",
                "junit:build/cucumber/cucumber-report-junit.xml"
        }
)
public class RunWebTest {
}
