package pages;

import base.BasePage;
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

    public void loginSwagLabs(String name, String pwd){
        typeText(txtUserName,name);
        typeText(txtPassword,pwd);
        click(btnLogin);
    }
}
