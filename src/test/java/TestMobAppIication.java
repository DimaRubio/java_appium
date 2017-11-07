
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

/**
 * Created by dmytro.bolyachin on 07.09.17.
 */
public class TestMobAppIication {
    AndroidDriver driver;
    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Browser");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android emulator");
        caps.setCapability("avd","Nexus_5_API_22");// Mention the created AVD name
        driver = new AndroidDriver (new URL("http://0.0.0.0:4723/wd/hub"), caps);
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void testExample() {
        driver.get("https://www.google.com");
        WebElement searchBox=driver.findElement(By.id("com.android.chrome:id/search_box_text"));
        searchBox.sendKeys("Appium for mobile automation");
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
