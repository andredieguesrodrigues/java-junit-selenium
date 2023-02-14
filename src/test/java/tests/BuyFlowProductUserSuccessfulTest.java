package tests;

import base.BaseTest;
import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Platform;
import pages.*;
import utils.Parallelized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static utils.GeneralConstants.SAUCE_DEMO_URL;

@RunWith(Parallelized.class)
public class BuyFlowProductUserSuccessfulTest extends BaseTest {

    @Rule
    public TestName testName = new TestName();

    Faker faker;

    public BuyFlowProductUserSuccessfulTest(String browserName, String login, String pwd) throws IOException {
        super(browserName, login, pwd);
    }

    @Parameterized.Parameters
    public static Collection userDataAccess() {
        return Arrays.asList(new Object[][] {
                { "chrome","standard_user", "secret_sauce"},
                { "firefox","standard_user", "secret_sauce"},
                { "MicrosoftEdge","standard_user", "secret_sauce"}
        });
    }

    @Before
    public void setup(){
        setPlatform(Platform.WIN10);
        faker = new Faker();
    }

    @Test
    public void checkoutProductWithStandardUserSuccessfully() throws Exception {
        // Arrange
        driver.get(SAUCE_DEMO_URL);

        // Act
        page.GetInstance(LoginPage.class).loginSwagLabs(login,pwd);
        page.GetInstance(InventoryPage.class).addProductToCart();
        page.GetInstance(InventoryPage.class).accessTheCart();

        String productName = page.GetInstance(InventoryPage.class).getProductName();
        String productPrice = page.GetInstance(InventoryPage.class).getProductName();

        page.GetInstance(CartPage.class).verifyProductName(productName);
        page.GetInstance(CartPage.class).verifyProductName(productPrice);

        page.GetInstance(CartPage.class).checkoutProduct();
        page.GetInstance(CheckoutStepOnePage.class).fillPersonalData(faker.name().firstName(), faker.name().lastName(), faker.address().zipCode());

        page.GetInstance(CheckoutStepOnePage.class).continueCheckout();

        page.GetInstance(CheckoutStepTwoPage.class).verifyProductName(productName);
        page.GetInstance(CheckoutStepTwoPage.class).verifyProductName(productPrice);

        page.GetInstance(CheckoutStepTwoPage.class).finishCheckout();

        takeScreenShot(driver, testName.getMethodName());

        // Assert
        page.GetInstance(CheckoutCompletePage.class).verifyCheckoutMessage("CHECKOUT: COMPLETE!");
        page.GetInstance(CheckoutCompletePage.class).verifyThanksMessage("THANK YOU FOR YOUR ORDER");
        page.GetInstance(CheckoutCompletePage.class).verifyButtonBackHome("BACK HOME");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}