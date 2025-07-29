# JaCoCo and Its Usage in Gradle Projects

JaCoCo (Java Code Coverage) is a free, open-source code coverage library for Java and Kotlin applications. It helps developers measure how much of their code is being exercised by tests.

## How to Use JaCoCo in Gradle Projects

To use JaCoCo in a Gradle project, you need to apply the JaCoCo plugin in your `build.gradle` file. Here’s how you can do it:

```groovy                   
plugins {
    id 'java'
    id 'jacoco'
}   
// customize JaCoCo behavior
jacoco {
    toolVersion = "0.8.10" // Specify the JaCoCo version
    reportsDirectory = layout.buildDirectory.dir('customJacocoReportDir')
}   
jacocoTestReport {
    reports {
        xml.required = true
        html.required = true
    }
}
test {
    finalizedBy jacocoTestReport // Generate report after tests run
}
```

## Generating JaCoCo Reports

After applying the plugin, Gradle adds several tasks:

* jacocoTestReport: Generates code coverage reports

* jacocoTestCoverageVerification: Verifies if coverage meets specified rules

Basic report configuration:

```groovy   
jacocoTestReport {
    reports {
        xml.required = true // Required for SonarQube
        html.required = true // Human-readable HTML report
        csv.required = false
    }
    
    // Optional: Exclude files from coverage
    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.collect {
            fileTree(dir: it, exclude: [
                'com/example/excludedpackage/**',
                '**/*Test.class'
            ])
        }))
    }
}
```
## How jacoco calculates test coverage 
JaCoCo calculates test coverage by analyzing the bytecode of the classes and comparing it with the executed code during tests. It tracks which lines of code were executed and which were not, providing metrics such as:
* **Line Coverage**: Percentage of executed lines of code.
* **Branch Coverage**: Percentage of executed branches in conditional statements.
* **Method Coverage**: Percentage of executed methods.
* **Class Coverage**: Percentage of executed classes.
* **Instruction Coverage**: Percentage of executed bytecode instructions.
* **Complexity Coverage**: Percentage of executed complex code paths.
* **Missed Instructions**: Lines of code that were not executed during tests.
* **Missed Branches**: Branches in conditional statements that were not executed.
* **Missed Methods**: Methods that were not called during tests.
* **Missed Classes**: Classes that were not loaded or executed during tests.
* **Missed Complexity**: Complex code paths that were not executed.
* **Missed Lines**: Lines of code that were not executed during tests.
## Viewing JaCoCo Reports
After running the `jacocoTestReport` task, you can find the generated reports in the specified directory (default is `build/reports/jacoco/test`).
You can open the HTML report in a web browser to view detailed coverage information, including which lines of code were executed and which were not.
