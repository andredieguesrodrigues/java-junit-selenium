package pages;

import base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class InventoryPage extends BasePage {
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.ID, using = "add-to-cart-sauce-labs-backpack")
    public WebElement btnAddToCartProduct01;

    @FindBy(how = How.CLASS_NAME, using = "shopping_cart_link")
    public WebElement iconCart;

    @FindBy(how = How.CLASS_NAME, using = "inventory_item_name")
    public WebElement lblProductName;

    @FindBy(how = How.CLASS_NAME, using = "inventory_item_price")
    public WebElement lblProductPrice;

    public void addProductToCart(){
        click(btnAddToCartProduct01);
    }

    public void accessTheCart(){
        click(iconCart);
    }

    public String getProductName(){
        return readText(lblProductName);
    }

    public String getPriceName(){
        return readText(lblProductPrice);
    }

}
