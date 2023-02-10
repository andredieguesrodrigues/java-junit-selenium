package pages;

import base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.ID, using = "user-name")
    public WebElement txtUserName;

    @FindBy(how = How.ID, using = "password")
    public WebElement txtPassword;

    @FindBy(how = How.ID, using = "login-button")
    public WebElement btnLogin;

    @FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/h3[1]")
    public WebElement lblErrorMessage;

    public void loginSwagLabs(String name, String pwd){
        typeText(txtUserName,name);
        typeText(txtPassword,pwd);
        click(btnLogin);
    }

    public void verifyErrorMessage(String expectedText) {
        Assert.assertEquals(expectedText, readText(lblErrorMessage).trim());
    }
}
