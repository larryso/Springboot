# Junit Test Coverage Report Using Gradle and Jacoco\
Jacoco is one the most popular tools for generating coverage reports for Junit tests. It can be used seamlessly integrating with Gradle, Sonarqube and other CI/CD tools like Jenkins

## Use the gradle Jacoco plugin

In order to generate the reports, all you need to di is add the jacoco plugin

```java
plugins {
        id 'java'
        id 'jacoco'
}
```
When you run the test task, it will create the reports but in Jacoco binary format: test.exec

To customize your jacoco test Report, you can add below in your gradle build.gradle file

```java
jacocoTestReport{
//    println "in jacocTestReport"
    executionData tasks.withType(Test).findAll{it.state.executed}
    reports{
        xml.required = true
        csv.required = false
        html.required = true
        csv.destination file("${buildDir}/reports/jacoco/jacocoTestReport.csv")
        html.destination file("${buildDir}/reports/jacoco/jacocoTestReport.html")
        xml.destination file("${buildDir}/reports/jacoco/jacocoTestReport.xml")
    }

}
```

## Generating the test coverage Report

```
task jacocoReportTest(type: Test){
    useJUnitPlatform{
        includeTags "jacocoReport"
    }
}

jacocoTestReport{
//    println "in jacocTestReport"
    executionData tasks.withType(Test).findAll{it.state.executed}
    reports{
        xml.required = true
        csv.required = false
        html.required = true
        csv.destination file("${buildDir}/reports/jacoco/jacocoTestReport.csv")
        html.destination file("${buildDir}/reports/jacoco/jacocoTestReport.html")
        xml.destination file("${buildDir}/reports/jacoco/jacocoTestReport.xml")
    }
    afterEvaluate {
        classDirectories.from = files(classDirectories.files.collect{
            fileTree(dir: it, includes: [
                    'com/larry/test/**'
            ])
        })
    }
    dependsOn jacocoReportTest
}
```
then run gradle task: gradlew clean test

## View the Coverage Report results
![](./imgs/jacoco_report.png)

* Green: Code is covered by a test
* Red: Code is not covered by any test
* Yellow: Code is Partially covered by test
