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
import pages.LoginPage;
import utils.Parallelized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static utils.GeneralConstants.SAUCE_DEMO_URL;

@RunWith(Parallelized.class)
public class BuyFlowProductUserWithoutAccessTest extends BaseTest {

    @Rule
    public TestName testName = new TestName();

    Faker faker;

    public BuyFlowProductUserWithoutAccessTest(String browserName, String login, String pwd, String msg) throws IOException {
        super(browserName, login, pwd, msg);
    }

    @Parameterized.Parameters
    public static Collection userDataAccess() {
        return Arrays.asList(new Object[][] {
                { "chrome","locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                { "firefox","locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                { "MicrosoftEdge","locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                { "chrome","locked_out_user", "inexistent_user", "Epic sadface: Username and password do not match any user in this service"},
                { "firefox","locked_out_user", "inexistent_user", "Epic sadface: Username and password do not match any user in this service"},
                { "MicrosoftEdge","locked_out_user", "inexistent_user", "Epic sadface: Username and password do not match any user in this service"}
        });
    }

    @Before
    public void setup(){
        setPlatform(Platform.WIN10);
        faker = new Faker();
    }

    @Test
    public void checkoutProductWithLockedOutUserReturnsError() throws Exception {
        // Arrange
        driver.get(SAUCE_DEMO_URL);

        // Act
        page.GetInstance(LoginPage.class).loginSwagLabs(login,pwd);
        takeScreenShot(driver, testName.getMethodName());

        // Assert
        page.GetInstance(LoginPage.class).verifyErrorMessage(msg);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}