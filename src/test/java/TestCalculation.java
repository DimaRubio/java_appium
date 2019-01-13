
import Page.CalculatorPage;
import Utils.ExcelSheetLibrary;
import jxl.read.biff.BiffException;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;

public class TestCalculation {
    AndroidDriver driver;
    CalculatorPage page;
    @BeforeClass
    public void setUp() throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android emulator");
        caps.setCapability("avd","Nexus_5_API_26_1");// Mention the created AVD name
        caps.setCapability("appPackage", "com.android.calculator2");
        caps.setCapability("appActivity", "com.android.calculator2.Calculator");
        driver = new AndroidDriver (new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        page = new CalculatorPage(driver);
    }
    @Test
    public void testExample() throws IOException, BiffException {
        ExcelSheetLibrary excel = new ExcelSheetLibrary(System.getProperty("user.dir") + "/src/test/java/Resources/DDT.xls");
        String res = excel.ReadCell(excel.GetCell("Result"), 1);
        //clean display
        page.btnDelete.click();

        //execute operation
        page.clickOnKey(excel.ReadCell(excel.GetCell("Number1"),1));
        page.btnPlus.click();
        page.clickOnKey(excel.ReadCell(excel.GetCell("Number2"),1));
        page.btnPlus.click();
        page.clickOnKey(excel.ReadCell(excel.GetCell("Number2"),1));
        //verify result

        String result = page.fieldResult.getText();
        assert result.equals(excel.ReadCell(excel.GetCell("Result"), 1));
    }
    @AfterClass
    public void tearDown(){
        driver.closeApp();
        driver.quit();
    }
}
