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

public class TestNativeElement {
    AndroidDriver driver;
    @BeforeClass
    public void setUp() throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android emulator");
        caps.setCapability("avd","Nexus_5_API_22");// Mention the created AVD name
        caps.setCapability("appPackage", "com.android.androidui");
        caps.setCapability("appActivity", "com.android.androidui.MainActivity");
        driver = new AndroidDriver (new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    }

    @Test
    public void testAlert(){
        WebElement showAlert = driver.findElementById("com.android.androidui:id/buttonAlert");
        showAlert.click(); //it will open the Alert box
        WebElement but_no = driver.findElementById("android:id/button2");
        but_no.click();// Click on No button
    }

    @Test
    public void testSpinner(){
        WebElement spinner = driver.findElement(By.id("android:id/text1"));
        spinner.click();
        driver.scrollToExact("India");
        WebElement optionIndia = driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='India']"));
        optionIndia.click();
    }

    @Test
    public void testSwitchBtn(){
        WebElement switchBtn=driver.findElementById("com.android.androidui:id/mySwitch");
        switchBtn.click();
        String b = switchBtn.getAttribute("checked");
        assert switchBtn.getAttribute("checked").equals("true");
    }

    @Test
    public void testSlideBar(){
        WebElement slider = driver.findElementById("com.android.androidui:id/seekBar1");
        int xAxisStartPoint = slider.getLocation().getX();
        int xAxisEndPoint = xAxisStartPoint + slider.getSize().getWidth();
        int yAxis = slider.getLocation().getY();
        TouchAction act=new	TouchAction(driver);
        act.press(xAxisStartPoint,yAxis).moveTo(xAxisEndPoint-1,yAxis).release().perform();
    }

    @AfterClass
    public	void	tearDown(){
        driver.closeApp();
    }
}
