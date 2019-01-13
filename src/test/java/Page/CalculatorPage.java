package Page;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class CalculatorPage {
    Map<String, WebElement> keyboard = new HashMap();

    public CalculatorPage(AndroidDriver driver){
        PageFactory.initElements( new AppiumFieldDecorator(driver),this);

        keyboard.put("1", digit1);
        keyboard.put("2", digit2);
        keyboard.put("3", digit3);
    }

    @AndroidFindBy(id = "plus")
    public WebElement btnPlus;

    @AndroidFindBy(id = "times")
    public WebElement btnMultiply;

    @AndroidFindBy(id = "equals")
    public WebElement btnEqual;

    @AndroidFindBy(xpath = "//*[contains(@resource-id,'result')]")
    public WebElement fieldResult;

    @AndroidFindBy(id = "delete")
    public WebElement btnDelete;

    @AndroidFindBy(xpath = "//android.widget.Button")
    public List<WebElement> keyList;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,'digit_1')]")
    public WebElement digit1;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,'digit_2')]")
    public WebElement digit2;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,'digit_3')]")
    public WebElement digit3;


    public void clickOnKey(String key){

        keyboard.get(key).click();
    }
}
