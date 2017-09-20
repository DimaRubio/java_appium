package Page;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;

public class PageAndroidUI {

    public PageAndroidUI(AndroidDriver driver){
        PageFactory.initElements( new AppiumFieldDecorator(driver),this);
//        PageFactory.initElements( driver,this);
//        PageFactory.initElements( driver,this);

    }

    @AndroidFindBy(id = "com.android.androidui:id/buttonAlert")
    public WebElement btnAlert;

    @AndroidFindBy(id = "android:id/text1")
    public WebElement selSpinner;

    @AndroidFindBy(id = "com.android.androidui:id/mySwitch")
    public WebElement btnSwitch;

}
