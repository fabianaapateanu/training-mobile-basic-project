# training-mobile-basic-project
Basic Java Maven project with Appium and JUnit test class for simple Android app 

## Project resources
* src/main/java/resources - Android apk file(s)
* training-mobile-basic-project/pom.xml - the maven project configuration file, where all Java 
library dependencies should be added

## Maven dependencies

1. For Appium Java libraries we have added in the `pom.xml` file: 
   `<dependency>
       <groupId>io.appium</groupId>
       <artifactId>java-client</artifactId>
       <version>5.0.4</version>
   </dependency>`
2. For JUnit java libraries we have added in the `pm.xml` file:
    `<dependency>
       <groupId>junit</groupId>
       <artifactId>junit</artifactId>
       <version>4.12</version>
       <scope>test</scope>
   </dependency`

## Test class

1. Start Appium server
2. Identify a mobile element with Appium Inspector 

3. Create a new class
4. Create a new test method
5. Create AppiumDriver with necessary capabilites
6. Click the mobile element
7. Verify the click result

## Run the test method
Run in IntelliJ IDEA
TBD

## Practice :exclamation: :sweat:
TBD
