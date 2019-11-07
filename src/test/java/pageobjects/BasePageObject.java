package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {
    private WebDriver driver;
    private String login;
    private String password;
    private WebDriverWait wait;

    public void sendData(String login, String password){
        this.login=login;
        this.password=password;
    }

    public BasePageObject(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, 10);
    }



    @FindBy(css= ".supernova-navi_dashboard > div:nth-child(5)")
    private WebElement loginButton;
    @FindBy(css= ".HH-AuthForm-Login")
    private WebElement loginBox;
    @FindBy(css= ".HH-AuthForm-Password")
    private WebElement passwordBox;
    @FindBy(css= "input.bloko-button")
    private WebElement blokoButton;
    @FindBy(css = ".supernova-icon_profile")
    private WebElement profileButton;
    @FindBy(css = "input.supernova-dropdown-option")
    private WebElement exitProfileButton;


    public void init(){

        PageFactory.initElements(driver,this);
        driver.get("https://hh.ru");
    }

    public void loginToHh(){
        loginButton.click();

        wait.until(ExpectedConditions.visibilityOf(loginBox));
        loginBox.clear();
        loginBox.sendKeys(login);
        passwordBox.sendKeys(password);
        blokoButton.click();


    }

    public void logoutFromHh(){

        profileButton.click();
        exitProfileButton.click();
        wait.until(ExpectedConditions.visibilityOf(loginButton));


    }

    public boolean isLoginButtonDisplayed(){
        return loginButton.isDisplayed();
    }


}
