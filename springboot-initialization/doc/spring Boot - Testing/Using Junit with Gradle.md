# Using Junit5 with Gradle

1. Specify the platform
```
test{
    useJUnitPlatform()
}
```
2. Spuly the Junit dependency
In Junit5, The API is seperated from the runtime, meaning two dependencies.
The API is mainfest with junit-jupiter-api, the runtime is junit-jupiter-engine
```
//Junit5 for test
    testImplementation "org.junit.jupiter:junit-jupiter-api:${junitVersion}"
    testRuntimeOnly "org.junit.jupiter:junit-jupiter-engine:${junitVersion}
```
3. Configure Junit5 tests with Gradle

```java
import javax.annotation.processing.SupportedAnnotationTypes;

class JunitTestDemo {
    @Test
    void junitTestCase(){
        assertEquals(1222, Integer.sum(1, 98));
    }
}
```

4. Run Test Cases with Gradle
We can run our tests with gradle by using command: `gradle clean test`
 
NOTE: By default, the test task doesn't show the information to system.out, if you want to show the information, you can make the required changes to configuration of the test task by:
1. using com.adarshr.test-logger plugin
  `id "com.adarshr.test-logger" version "2.0.0"` 
2. overwrite defult logger config, below is default config
```
testlogger {
    // pick a theme - mocha, standard, plain, mocha-parallel, standard-parallel or plain-parallel
    theme 'standard'

    // set to false to disable detailed failure logs
    showExceptions true

    // set to false to hide stack traces
    showStackTraces true

    // set to true to remove any filtering applied to stack traces
    showFullStackTraces false

    // set to false to hide exception causes
    showCauses true

    // set threshold in milliseconds to highlight slow tests
    slowThreshold 2000

    // displays a breakdown of passes, failures and skips along with total duration
    showSummary true

    // set to true to see simple class names
    showSimpleNames false

    // set to false to hide passed tests
    showPassed true

    // set to false to hide skipped tests
    showSkipped true

    // set to false to hide failed tests
    showFailed true

    // enable to see standard out and error streams inline with the test results
    showStandardStreams false

    // set to false to hide passed standard out and error streams
    showPassedStandardStreams true

    // set to false to hide skipped standard out and error streams
    showSkippedStandardStreams true

    // set to false to hide failed standard out and error streams
    showFailedStandardStreams true
}
```
5. using @Tag annotation and Gradle Task to run specofic test
```groovy
task FastTest(type: Test){
    useJUnitPlatform{
        includeTags "Fast"
    }
}
```
Run the task by:
`gradle FastTest`

## Using Cucumber with Junit5
Cucumber is a test automation tool that supports Behavior-Driven Development, it runs specifications written in plain text that describes system behavior.

#### Dependencies
```groovy
 testImplementation "io.cucumber:cucumber-java:${cucumberVersion}"
 testImplementation "io.cucumber:cucumber-junit:${cucumberVersion}"
```
#### Use Junit Cucumber Runner with @RunWith annotation
```java
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/web"},
        glue = {"com.larry.steps", "com.larry.hooks"},
        plugin = {
                "pretty",
                "html:build/cucumber/cucumber-report-html",
                "json:build/cucumber/cucumber-report.json",
                "junit:build/cucumber/cucumber-report-junit.xml"
        }
)
public class RunWebTest {
}
```
@CucumberOptions annotation is provided in Test Runner class to specific different properties:
1. features: Location of feature files
2. glue: location of stepdefinition files and hooks
3. tags: Scenarios specified with tags
4. plugin: Cucumber plugins to generate report or perform additional tasks
5. dryRun: enables a quick check to see if all step definitions have been implemented
6. monochrome: makes the console output more readable
7. strict: Cucumber will treat undefined and pending steps as failures
8. name: provide a name for the Cucumber test suite

#### Feature file
Cucumber BDD (Behavior Driven Development) framework mainly consists of three major parts - Feature files, Step Definitions, and the Test Runner

Feature file that are stored with "*.feature". Key words such as GIVEN, WHEN, and THEN.

```
GIVEN user navigates to login page by opening Firefox
WHEN user enters correct <username> AND <password> values
THEN user is directed to the home page
```

#### Step Definitions
Now that the features are writen in the feature files, the code for the related scenario has to be run.
To Know which batch of code needs to be run for a given scenario, Step definitions come into the picture.
A steps definitions file stores the mapping data between each step of a scenatior defines in the feature file and the code to be executed.

```java
public class Steps{ 
    @Given("^user navigates to the login page by opening Firefox$")
  //code to open open Firefox Browser and launch login page
  @When("^user enters correct username and password value$")
  // take inputs for username and password fields by element selector and put the correct values
  @Then ("^user gets directed to homepage$")
  //direct to homepage
}
```

Then Test Runner will read from features and steps to execute those tests.