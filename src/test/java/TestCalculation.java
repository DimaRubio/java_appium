
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class TestCalculation {
    AndroidDriver driver;
    @BeforeClass
    public void setUp() throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android emulator");
        caps.setCapability("avd","Nexus_4_API_19");// Mention the created AVD name
        caps.setCapability("appPackage", "com.android.calculator2");
        caps.setCapability("appActivity", "com.android.calculator2.Calculator");
        driver = new AndroidDriver (new URL("http://127.0.0.1:4723/wd/hub"), caps);
        int r= 10;
//        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    }
    @Test
    public void testExample(){
//        WebElement five=driver.findElement(By.name("5"));
//        five.click();
//        WebElement plus=driver.findElement(By.name("+"));
//        plus.click();
//        WebElement four=driver.findElement(By.name("4"));
//        four.click();
//        WebElement equalTo=driver.findElementByAccessibilityId("equals");
//        equalTo.click();

//        driver.findElement(By.id("com.android.calculator2:id/digit_4")).click();
//        driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
//        driver.findElement(By.id("com.android.calculator2:id/digit_8")).click();
//        driver.findElement(By.id("com.android.calculator2:id/eq")).click();
//        String result = driver.findElement(By.className("android.widget.EditText")).getText();
//        System.out.println("Result of Addition is : " + result);

        //clean display
        driver.findElementByAccessibilityId("delete").click();

        //execute operation
        driver.findElementById("digit1").click();
        driver.findElementByAccessibilityId("plus").click();
        driver.findElementById("digit3").click();
        driver.findElementByAccessibilityId("multiply").click();
        driver.findElementById("digit2").click();

        driver.findElementByAccessibilityId("plus").click();
        driver.findElement(By.id("com.android.calculator2:id/digit_4")).click();
        driver.findElementByAccessibilityId("equals").click();

        //verify result
        String result = driver.findElementByClassName("android.widget.EditText").getText();
        assert result.equals("7");
    }
    @AfterClass
    public void tearDown(){
        driver.closeApp();
        driver.quit();
    }
}
