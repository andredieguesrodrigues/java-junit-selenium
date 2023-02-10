package pages;

import base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.ID, using = "checkout")
    public WebElement btnCheckout;

    @FindBy(how = How.CLASS_NAME, using = "inventory_item_name")
    public WebElement productName;

    @FindBy(how = How.CLASS_NAME, using = "inventory_item_name")
    public WebElement lblProductName;

    @FindBy(how = How.CLASS_NAME, using = "inventory_item_price")
    public WebElement lblProductPrice;

    public void checkoutProduct(){
        click(btnCheckout);
    }

    public void getProductName(){
        readText(lblProductName);
    }

    public void getPriceName(){
        readText(lblProductPrice);
    }

    public void verifyProductName(String expectedText) {
        Assert.assertEquals(readText(lblProductName).trim(), expectedText);
    }

    public void verifyProductPrice(String expectedText) {
        Assert.assertEquals(readText(lblProductPrice).trim(), expectedText);
    }

}
