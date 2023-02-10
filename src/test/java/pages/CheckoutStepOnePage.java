package pages;

import base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CheckoutStepOnePage extends BasePage {
    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.ID, using = "first-name")
    public WebElement txtFirstName;

    @FindBy(how = How.ID, using = "last-name")
    public WebElement txtLastName;

    @FindBy(how = How.ID, using = "postal-code")
    public WebElement txtPostalCode;

    @FindBy(how = How.ID, using = "continue")
    public WebElement btnContinue;

    public void fillPersonalData(String firstName, String lastName, String postalCode){
        typeText(txtFirstName,firstName);
        typeText(txtLastName,lastName);
        typeText(txtPostalCode,postalCode);

    }

    public void continueCheckout(){
        click(btnContinue);
    }


}
