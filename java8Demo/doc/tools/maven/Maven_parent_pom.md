## create maven parent pom project
Maven parent pom is used to structure the project to avoid redundancies or duplicate configuration
child pom file could inherit from the parent pom like below:

```xml
<parent>
    <groupId> com.larry.java8Demo</groupId>
    <artifactId>mavenPomExample</artifactId>
    <version>0.0.0.1</version>
</parent>
```
By default, Maven looks for the parent POM file first at the project'root. then local repository, and lastly from the remote repository

1. Packaging need to be as pom
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.larry</groupId>
  <artifactId>sr-rest4-master</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>pom</packaging>
  <name>sr-rest4-master</name>
</project>
```

2. run mvn clean install
   this command will clean up previous .class and .prpperties then compile, test and package your java project and even copy your jar file into your local Maven repository
   
3. Deploying Liferay Maven Artifacts to a Repository

