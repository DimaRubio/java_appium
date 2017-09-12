
import io.appium.java_client.TouchAction;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class TestDialer {
    AndroidDriver driver;
    @BeforeClass
    public void setUp() throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android emulator");
        caps.setCapability("avd","Nexus_5_API_22");// Mention the created AVD name
        caps.setCapability("appPackage", "com.android.dialer");
        caps.setCapability("appActivity", "com.android.dialer.DialtactsActivity");
        driver = new AndroidDriver (new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    }
    @Test
    public void testDialer(){

        WebElement dialPad = driver.findElementByAccessibilityId("dial pad");
        dialPad.click();

        //type number
        driver.findElement(By.id("com.android.dialer:id/one")).click();
        driver.findElement(By.id("com.android.dialer:id/two")).click();
        for (int i = 3; i <= 8; i++){
            driver.findElementByAccessibilityId(""+i+"").click();
        }

        //to make the call
        driver.findElementByAccessibilityId("dial").click();
        turnOfCall();
    }

    @Test
    public void testLongPress(){

        WebElement dialPad = driver.findElementByAccessibilityId("dial pad");
        dialPad.click();


        //perform long press
        TouchAction tAction=new	TouchAction(driver);
        tAction.longPress(driver.findElementByAccessibilityId("0")).perform();
        
        //type number
        for (int i = 1; i<= 6; i++){
            driver.findElementByAccessibilityId(""+i+"").click();
        }
        WebElement	results= driver.findElementByClassName("android.widget.EditText");
        assert	results.getText().contains("+"):"Actual value is :"+results.getText()+" did not match with expected value: +";
    }


    public void turnOfCall(){
        driver.findElementByAccessibilityId("End").click();
    }

    @AfterClass
    public void tearDown(){
        driver.closeApp();
        driver.quit();
    }
}
