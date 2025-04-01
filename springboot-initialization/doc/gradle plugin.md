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




