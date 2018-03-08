import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MavenJunitTestClass {

    @Test
    public void testSettings() {
        URL serverUrl = null;
        try {
            serverUrl = new URL("http://127.0.0.1:4723/wd/hub/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PLATFORM, "Android");
        capabilities.setCapability(MobileCapabilityType.UDID, "192.168.56.101:5555");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "EmulatorS7");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "600");

        //this path is Relative to the project directory path: src/main/resources/yamba-debug.apk
        //in order to use this copy paste the yamba-debug.apk in src/main/resources/
        //otherwise, copy paste here the full path from File Explorer to the yamba-debug.apk, including the name of the file itself yamba-debug.apk
        String appPath = System.getProperty("user.dir") +
                File.separator + "src" + File.separator + "main" +
                File.separator + "resources" + File.separator + "yamba-debug.apk";

        capabilities.setCapability(MobileCapabilityType.APP, appPath);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.android.yamba");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.example.android.yamba.MainActivity");

        AndroidDriver androidDriver = new AndroidDriver(serverUrl, capabilities);

        MobileElement moreOptionsElement = (MobileElement) androidDriver.findElementByAccessibilityId("More options");
        moreOptionsElement.click();

        String settingsButtonXpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.TextView";
        MobileElement settingsElement = (MobileElement) androidDriver.findElementByXPath(settingsButtonXpath);
        settingsElement.click();

        MobileElement backButtonElement = (MobileElement) androidDriver.findElementByAccessibilityId("Navigate up");

        boolean isBackButtonDisplayed = backButtonElement.isDisplayed();
        Assert.assertTrue("The back button is not displayed", isBackButtonDisplayed);

        //Closes the session
        androidDriver.quit();
    }
}
