package pages;

import base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

import static utils.Support.checkRepeatedValue;

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

    @FindBys(@FindBy(how = How.CLASS_NAME, using = "inventory_item_price"))
    public List<WebElement> lblPriceItems;

    @FindBys(@FindBy(how = How.CLASS_NAME, using = "inventory_item_name"))
    public List<WebElement> lblNamesItems;

    @FindBys(@FindBy(how = How.CLASS_NAME, using = "inventory_item_desc"))
    public List<WebElement> lblDescriptionsItems;

    @FindBys(@FindBy(how = How.CSS, using = "img.inventory_item_img"))
    public List<WebElement> lblImageItems;

    public void addProductToCart() {
        click(btnAddToCartProduct01);
    }

    public void accessTheCart() {
        click(iconCart);
    }

    public String getProductName() {
        return readText(lblProductName);
    }

    public ArrayList getAllItems(String property) {
        String attribute = "";
        List<WebElement> lblPropertyItems = null;
        switch(property) {
            case "image":
                lblPropertyItems = lblImageItems;
                attribute = "src";
                break;
            case "name":
                lblPropertyItems = lblNamesItems;
                attribute = "text";
                break;
            case "price":
                lblPropertyItems = lblPriceItems;
                attribute = "text";
                break;
            case "description":
                lblPropertyItems = lblDescriptionsItems;
                attribute = "text";
                break;
            default:
            break;
        }

        return iterateElements(lblPropertyItems, attribute);
    }

    public void verifyIfContainsSpecificText(ArrayList<String> arrayItems, String character) {
        boolean itemWithText = true;

        for (int count = 0; count < arrayItems.size(); count++) {
            if (!arrayItems.get(count).contains(character)) {
                itemWithText = false;
            }
        }

        Assert.assertTrue(itemWithText);
    }

    public void verifyRepeatedValue(ArrayList<String> arrayItems) {
        Assert.assertTrue(checkRepeatedValue(arrayItems));
    }

}
