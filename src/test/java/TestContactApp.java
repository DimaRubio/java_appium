import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class TestContactApp {
    AndroidDriver driver;
    @BeforeClass
    public void setUp() throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android emulator");
        caps.setCapability("avd","Nexus_5_API_22");// Mention the created AVD name
        caps.setCapability("appPackage", "com.android.contacts");
        caps.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");
        driver = new AndroidDriver (new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    }
    @Test
    public void scrollToParticularContact(){
        driver.scrollTo("User2").click();
        WebElement number = driver.findElementById("com.android.contacts:id/sub_header");
        assert	number.getText().contains("987654321");

        //click on edit profile item
        driver.findElementByAccessibilityId("Edit").click();
        //change avatar
        driver.findElementById("com.android.contacts:id/change_button").click();
        //select one of variants to take avatar
        List <WebElement> variant = driver.findElementsById("android:id/text1");
        variant.get(2).click();
        TouchAction tAction = new TouchAction(driver);
        tAction.press(300,300).waitAction(200).release().perform();

        //perform zoom via multi touch action
        MultiTouchAction multiTouch = new MultiTouchAction(driver);
        TouchAction tAction0 = new TouchAction(driver);
        TouchAction tAction1 = new TouchAction(driver);
        tAction0.press(500,500).waitAction(1000).moveTo(100,100).release();
        tAction1.press(500,550).waitAction(1000).moveTo(500,600).release();
        multiTouch.add(tAction0).add(tAction1);
        multiTouch.perform();
        back(3);


    }

    private void back(int n) {
        for (int i =1;i<=n;i++ ){
            driver.pressKeyCode(AndroidKeyCode.BACK);
        }
    }

    @Test
    public void swipeLabel(){
        WebElement element = driver.findElementById("com.android.contacts:id/toolbar");
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(element));

        // swipe to right
        driver.swipe(10,900,800,900,500);

        // second way, swipe to left
        TouchAction tAction = new TouchAction(driver);
        tAction.press(800, 900).waitAction(500).moveTo(10, 900).release();
        tAction.perform();
    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
