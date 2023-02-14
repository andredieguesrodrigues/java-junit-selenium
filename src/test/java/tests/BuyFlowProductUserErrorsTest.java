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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static utils.GeneralConstants.SAUCE_DEMO_URL;

@RunWith(Parallelized.class)
public class BuyFlowProductUserErrorsTest extends BaseTest {

    @Rule
    public TestName testName = new TestName();

    Faker faker;

    public BuyFlowProductUserErrorsTest(String browserName, String login, String pwd) throws IOException {
        super(browserName, login, pwd);
    }

    @Parameterized.Parameters
    public static Collection userDataAccess() {
        return Arrays.asList(new Object[][] {
                { "chrome","problem_user", "secret_sauce"},
                { "firefox","problem_user", "secret_sauce"},
                { "MicrosoftEdge","problem_user", "secret_sauce"},
                { "chrome","performance_glitch_user", "secret_sauce"},
                { "firefox","performance_glitch_user", "secret_sauce"},
                { "MicrosoftEdge","performance_glitch_user", "secret_sauce"}
        });
    }

    @Before
    public void setup(){
        setPlatform(Platform.WIN10);
        faker = new Faker();
    }

    @Test
    public void checkoutProductWithInternalErrors() throws Exception {
        // Arrange
        driver.get(SAUCE_DEMO_URL);

        // Act
        page.GetInstance(LoginPage.class).loginSwagLabs(login,pwd);

        ArrayList<String> arrayImages = page.GetInstance(InventoryPage.class).getAllItems("image");
        ArrayList<String> arrayPrices = page.GetInstance(InventoryPage.class).getAllItems("price");
        ArrayList<String> arrayNames = page.GetInstance(InventoryPage.class).getAllItems("name");
        ArrayList<String> arrayDescriptions = page.GetInstance(InventoryPage.class).getAllItems("description");

        takeScreenShot(driver, testName.getMethodName());

        // Assert
        page.GetInstance(InventoryPage.class).verifyNotRepeatedValue(arrayNames);
        page.GetInstance(InventoryPage.class).verifyNotRepeatedValue(arrayDescriptions);
        page.GetInstance(InventoryPage.class).verifyNotRepeatedValue(arrayImages); //it fails
        page.GetInstance(InventoryPage.class).verifyIfContainsSpecificText(arrayPrices,"$");
        page.GetInstance(InventoryPage.class).verifyIfNotContainsSpecificText(arrayNames,"()"); //it fails
        page.GetInstance(InventoryPage.class).verifyIfNotContainsSpecificText(arrayDescriptions,"()"); //it fails

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}