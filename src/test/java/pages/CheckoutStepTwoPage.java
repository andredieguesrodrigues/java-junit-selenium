package pages;

import base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CheckoutStepTwoPage extends BasePage {
    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.ID, using = "finish")
    public WebElement btnFirstName;

    @FindBy(how = How.CLASS_NAME, using = "inventory_item_name")
    public WebElement lblProductName;

    @FindBy(how = How.CLASS_NAME, using = "inventory_item_price")
    public WebElement lblProductPrice;

    public void finishCheckout(){
        click(btnFirstName);
    }

    public void verifyProductName(String expectedText) {
        Assert.assertEquals(expectedText, readText(lblProductName).trim());
    }

}
