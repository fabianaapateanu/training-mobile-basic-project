# training-mobile-basic-project
Basic Java Maven project with Appium and JUnit test class for simple Android app 

## Prerequisites
* Please download IntelliJ IDEA Community edition:
   https://www.jetbrains.com/idea/download/#section=windows
* Installation of IntelliJ IDEA will be done during the training session, but for reference all install steps can be found here:
   https://www.jetbrains.com/help/idea/install-and-set-up-intellij-idea.html


## Project structure & resources

Please use the following names for the Maven project, but replace with your name value where specified:

    `<groupId>"yourNameHere"-training-mobile-basics</groupId>
    <artifactId>mobile-maven-basic-project</artifactId>
    <version>1.0-SNAPSHOT</version>`

The test project is a Maven project with the default structure:

   * `training-mobile-basic-project/pom.xml` - the maven project configuration file, where all Java library dependencies should be added
   * `src/main/java/resources` - the app under tests, yamba-debug.apk file
   * `src/test/java` - the Java test class with a simple test method

## Application under test
Yamba is Yet Another Micro Blogging App. It was written to support various Android training classes, most specifically Android Bootcamp, and is an evolution of the project from Learning Android book written by Marko Gargenta. You can find it at http://github.com/thenewcircle/yamba. The version of the app used in this tutorial was downloaded from: https://github.com/mailat/android-testing-2016-02-19.

The debug apk can be found here:
* `src/main/java/resources/yamba-debug.apk`

The test user for this app is:
* username = `student`
* password = `password`

## Test scenario

As a simple test scenario in the Yamba application we will want to:
   1. Start the Yamba application
   2. Click the `More options` button
   
      ![Alt text](screenshots/MoreOptionsBtn.png?raw=true)
   3. Click the `Settings` button
   
       ![Alt text](screenshots/SettingsBtn.png?raw=true)
   4. Verify we landed on the Settings screen, meaning the Back button is displayed
       ![Alt text](screenshots/BackBtn.png?raw=true)
   
## Maven dependencies

1. For Appium Java libraries we have added in the `pom.xml` file:   
    `<dependency>
        <groupId>io.appium</groupId>
        <artifactId>java-client</artifactId>
        <version>5.0.4</version>
    </dependency>`
2. For JUnit java libraries we have added in the `pom.xml` file:    
    `<dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency`
    
## Test class

   1. Start Appium server at localhost 127.0.0.1 and default port
   2. Start the Appium Inspector session for the Yamba application
   3. Identify the mobile elements with Appium Inspector
      * The More Options button - we will click the More Options button
        `accessibility id` = `More options` - accessibility id from Appium inspector
        
      * The Settings button - we will click the Settings button
        `xpath` = `/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.TextView`
        The xpath from Appium inspector
        
      * The back button - this will be used to verify that the Settings screen has been reached
        `accessibility id` = `Navigate up` - accessibility id from Appium inspector
      
   4. Create a new Java class
        Right click on the directory `src/test/java` and select `New` and then `Java class`.
        The class file will be opened.
    
   5. Create a new test method
        In the class add the JUnit annotation for test method and afterwards the test method name:
        `@Test
        public void testSettings() { 
        }`
    The following steps need to be followed in the test method, writing the code inside the `{ }`
   
   6. Create AppiumDriver with necessary capabilites
    These are similar to the ones used in the Appium Session window.
        1. Add the Appium URL of the server, where all requests will be send:
        `URL serverUrl = null;
         try {
                serverUrl = new URL("http://127.0.0.1:4723/wd/hub/");
             } catch (MalformedURLException e) {
                 e.printStackTrace();
             }
        `
        2. Add the desired capabilities:
        ```
         DesiredCapabilities capabilities = new DesiredCapabilities();
         capabilities.setCapability(CapabilityType.PLATFORM, "Android");
         capabilities.setCapability(MobileCapabilityType.UDID, "192.168.56.101:5555");
         capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "EmulatorS7");
         capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
         capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "6000");
         String appPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "yamba-debug.apk";
 
         capabilities.setCapability(MobileCapabilityType.APP, appPath);
 
         capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.android.yamba");
         capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.example.android.yamba.MainActivity");
         ```

         3. Start the Android driver session:
         `AndroidDriver androidDriver = new AndroidDriver(serverUrl, capabilities);`
                 
   7. Find and click the More Options button:
  
       `MobileElement moreOptionsElement = (MobileElement) androidDriver.findElementByAccessibilityId("More options");
        moreOptionsElement.click();`
         
   8. Find and click the Settings button:
        ```
       String settingsButtonXpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.TextView";   
        MobileElement settingsElement = (MobileElement) androidDriver.findElementByXPath(settingsButtonXpath);  
        settingsElement.click();
        ```
        
   9. Find and verify that the back button is displayed. The JUnit `Assert` method is doing the verification:
   
       `MobileElement backButtonElement = (MobileElement) androidDriver.findElementByAccessibilityId("Navigate up");
        Assert.assertTrue("The back button is not displayed", backButtonElement.isDisplayed());`
   
   10. Close the Android driver session:
        `androidDriver.quit();`

## Run the test method

Make sure the Appium server at localhost is running, started through Appium desktop app.

Run in IntelliJ IDEA - IntelliJ IDEA already has an automatic way of running the JUnit tests,
therefore it is enough to:
    * Right click on the test method name and select `Run` option
    ![Alt text](screenshots/testSettings.png?raw=true)
    
The test will start running, and the app will start on the emulator/device
   
## Practice :exclamation: :sweat:

Finish the test scenario which we started in the training.
