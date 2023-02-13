package tests;

import base.BaseTest;
import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;
import pages.*;
import utils.Parallelized;

import java.io.IOException;
import java.util.ArrayList;

import static utils.GeneralConstants.SAUCE_DEMO_URL;

@RunWith(Parallelized.class)
public class BuyFlowProductTest extends BaseTest {

    @Rule
    public TestName testName = new TestName();

    Faker faker;

    public BuyFlowProductTest(String browserName) throws IOException {
        super(browserName);
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
        page.GetInstance(LoginPage.class).loginSwagLabs("standard_user","secret_sauce");
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

    @Test
    public void checkoutProductWithLockedOutUserReturnsError() throws Exception {
        // Arrange
        driver.get(SAUCE_DEMO_URL);

        // Act
        page.GetInstance(LoginPage.class).loginSwagLabs("locked_out_user","secret_sauce");
        takeScreenShot(driver, testName.getMethodName());

        // Assert
        page.GetInstance(LoginPage.class).verifyErrorMessage("Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void checkoutProductWithProblemUserSuccessfully() throws Exception {
        // Arrange
        driver.get(SAUCE_DEMO_URL);

        // Act
        page.GetInstance(LoginPage.class).loginSwagLabs("problem_user","secret_sauce");

        ArrayList<String> arrayImages = page.GetInstance(InventoryPage.class).getAllItems("image");
        ArrayList<String> arrayPrices = page.GetInstance(InventoryPage.class).getAllItems("price");
        ArrayList<String> arrayNames = page.GetInstance(InventoryPage.class).getAllItems("name");
        ArrayList<String> arrayDescriptions = page.GetInstance(InventoryPage.class).getAllItems("description");

        takeScreenShot(driver, testName.getMethodName());

        // Assert
        page.GetInstance(InventoryPage.class).verifyNotRepeatedValue(arrayImages); //it fails
        page.GetInstance(InventoryPage.class).verifyNotRepeatedValue(arrayNames);
        page.GetInstance(InventoryPage.class).verifyNotRepeatedValue(arrayDescriptions);
        page.GetInstance(InventoryPage.class).verifyIfContainsSpecificText(arrayPrices,"$");
        page.GetInstance(InventoryPage.class).verifyIfNotContainsSpecificText(arrayNames,"()"); //it fails
        page.GetInstance(InventoryPage.class).verifyIfNotContainsSpecificText(arrayDescriptions,"()"); //it fails

    }

    @Test
    public void checkoutProductWithPerformanceGlitchUserSuccessfully() throws Exception {

        // Arrange
        driver.get(SAUCE_DEMO_URL);

        // Act
        page.GetInstance(LoginPage.class).loginSwagLabs("performance_glitch_user","secret_sauce");

        ArrayList<String> arrayImages = page.GetInstance(InventoryPage.class).getAllItems("image");
        ArrayList<String> arrayPrices = page.GetInstance(InventoryPage.class).getAllItems("price");
        ArrayList<String> arrayNames = page.GetInstance(InventoryPage.class).getAllItems("name");
        ArrayList<String> arrayDescriptions = page.GetInstance(InventoryPage.class).getAllItems("description");

        takeScreenShot(driver, testName.getMethodName());

        // Assert
        page.GetInstance(InventoryPage.class).verifyNotRepeatedValue(arrayImages);
        page.GetInstance(InventoryPage.class).verifyNotRepeatedValue(arrayNames);
        page.GetInstance(InventoryPage.class).verifyNotRepeatedValue(arrayDescriptions);
        page.GetInstance(InventoryPage.class).verifyIfContainsSpecificText(arrayPrices,"$");
        page.GetInstance(InventoryPage.class).verifyIfNotContainsSpecificText(arrayNames,"()"); //it fails
        page.GetInstance(InventoryPage.class).verifyIfNotContainsSpecificText(arrayDescriptions,"()"); //it fails
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}