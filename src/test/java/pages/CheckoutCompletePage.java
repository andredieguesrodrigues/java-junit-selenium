package pages;

import base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CheckoutCompletePage extends BasePage {
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.CLASS_NAME, using = "title")
    public WebElement lblCheckoutComplete;

    @FindBy(how = How.CLASS_NAME, using = "complete-header")
    public WebElement lblThankYou;

    @FindBy(how = How.ID, using = "back-to-products")
    public WebElement btnBackHome;

    public void verifyCheckoutMessage(String expectedText) {
        Assert.assertEquals(readText(lblCheckoutComplete).trim(), expectedText);
    }

    public void verifyThanksMessage(String expectedText) {
        Assert.assertEquals(readText(lblThankYou).trim(), expectedText);
    }

    public void verifyButtonBackHome(String expectedText) {
        Assert.assertEquals(readText(btnBackHome).trim(), expectedText);
    }

}
