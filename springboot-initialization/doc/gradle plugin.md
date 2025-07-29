# Using Plugins

Plugins in gradle are essential for automating common build tasks, integrating with external tools or services, and tailoring the build process or meet specific project needs.

Some Baisc concepts:
1. [What is Maven Bom](https://www.baeldung.com/spring-maven-bom)

   Maven pom is an xml file that contains information about configurations that used by maven to import dependencies and build the project
   BOM stands for Bill Of Materials, A BOM is a special kind of POM that is used to control the versions of a project's dependencies and provide a central place to define and update those versions.
   BOM provides the flexibility to add a dependency to our module without worrying about the version that we should depend on.

## Spring Boot Gradle Plugin
The Spring Boot Gradle plugin provides Spring Boot Support in Gradle, it allows you to package executable jar or war archives, run Spring Boot applications, and use the dependency management provided by spring-boot-dependencies.
When you apply the io.spring.dependency-management plugin, Spring Boot's plugin will automatically import the spring-boot-dependencies bom from the version of Spring Boot that you are using. it allows you omit version numbers when declaring dependencies .

[For more Details, you can go to -> Gradle plugin](https://docs.spring.io/spring-boot/gradle-plugin/index.html)

## Gradle Java plugin

The Java plugin adds support for Java projects in Gradle. It provides tasks for compiling Java source code, running tests, and creating JAR files. The plugin also adds conventions for project structure, such as placing source code in `src/main/java` and test code in `src/test/java`.

[For more Details, you can go to -> Gradle plugin](https://docs.gradle.org/current/userguide/java_plugin.html)

when you apply the java plugin, Gradle automatically adds the following tasks to your project:
- `compileJava`: Compiles the main Java source code.
- `compileTestJava`: Compiles the test Java source code.
- `processResources`: Copies resources from `src/main/resources` to the build directory.
- `processTestResources`: Copies test resources from `src/test/resources` to the build directory.
- `classes`: Assembles the main classes.
- `testClasses`: Assembles the test classes.
- `jar`: Creates a JAR file containing the main classes and resources.
- `test`: Runs the tests in the project.
- `check`: Runs all checks, including tests.
- `build`: Assembles and tests the project.

You can customize the lifecycle tasks:

```xml
task verifyStyle{
    doLast {
        println "Verifying code style..."
        // Add your code style verification logic here
    }
}   
check.dependsOn verifyStyle
```
2. change task execution order
`compileJave.mustRunAfter verifyStyle`
3. replace or disable task