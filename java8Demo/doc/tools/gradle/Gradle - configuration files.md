# Gradle configuration files - setting.gradle & build.gradle & gradle.properties

## build.gradle
```
file structure of a basic gradle project:
build.gradle
gradle    
    wrapper
        gradle-wrapper.jar
        gradle-wrapper.properties
gradlew
gradlew.bat
settings.gradle
src
    main
        java  
            App.java
    test      
        java
            AppTest.java
```
The build.gradle file as the heart or the brain of the project. The resulting file looks like this:

```groovy
plugins {
    id 'java'
    id 'application'
}

mainClassName = 'App'

dependencies {
    compile 'com.google.guava:guava:23.0'

    testCompile 'junit:junit:4.12'
}

repositories {
    jcenter()
}
```
In case of a multi-project build, we’d probably have multiple different build.gradle files, one for each project.

The build.gradle file is executed against a Project instance, with one Project instance created per subproject. 

## settings.gradle

In contrast to the build.gradle file, only one settings.gradle file is executed per Gradle build. We can use it to define the projects of a multi-project build.

Besides, we can also possible to register code as part of different life cycle hooks of a build.

## gradle.properties
Gradle doesn’t create a gradle.properties file by default. It can reside in different locations, for example in the project root directory, inside of GRADLE_USER_HOME or in the location specified by the -Dgradle.user.home command line flag.

This file consists of key-value pairs. We can use it to configure the behavior of the framework itself and it’s an alternative to using command line flags for the configuration.

Examples of possible keys are:
* org.gradle.caching=(true,false)
* org.gradle.daemon=(true,false)
* org.gradle.parallel=(true,false)
* org.gradle.logging.level=(quiet,warn,lifecycle,info,debug)
Also, you can use this file to add properties directly to the Project object, e.g., the property with its namespace: org.gradle.project.property_to_set

Another use case is specifying JVM parameters like this:

org.gradle.jvmargs=-Xmx2g -XX:MaxPermSize=256m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8
Copy
Please note that it needs to launch a JVM process to parse the gradle.properties file. This means these JVM parameters only effect separately launched JVM processes.

## The Build in a Nutshell

1. It launches as a new JVM process
2. It parses the gradle.properties file and configures Gradle accordingly
3. Next, it creates a Settings instance for the build
4. Then, it evaluates the settings.gradle file against the Settings object
5. It creates a hierarchy of Projects, based on the configured Settings object
6. Finally, it executes each build.gradle file against its project