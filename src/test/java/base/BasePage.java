package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasePage extends PageGenerator{

    public BasePage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(this.driver,20);

    public void click(WebElement wl) {
        wait.until(ExpectedConditions.elementToBeClickable(wl));
        wl.click();
    }

    public void typeText(WebElement wl, String text) {
        wait.until(ExpectedConditions.visibilityOf(wl));
        wl.sendKeys(text);
    }

    public String readText(WebElement wl) {
        wait.until(ExpectedConditions.visibilityOf(wl));
        return wl.getText();
    }

    public ArrayList iterateElements(List<WebElement> lstElements, String attr){
        ArrayList<String> arrayItems = new ArrayList<>();

        if(attr.equalsIgnoreCase("text")) {
            for (int count = 0; count < lstElements.size(); count++) {
                arrayItems.add(lstElements.get(count).getText());
            }
        }

        if(attr.equalsIgnoreCase("src")) {
            for (int count = 0; count < lstElements.size(); count++) {
                arrayItems.add(lstElements.get(count).getAttribute(attr));
            }
        }
        return arrayItems;
    }

}
