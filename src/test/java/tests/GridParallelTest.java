package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.GridParallelTestBase;
import utils.RegressionTests;

@RunWith(Parallelized.class)
public class GridParallelTest extends GridParallelTestBase {

    @Rule
    public TestName testName = new TestName();

    //Constructor
    public GridParallelTest(String browserName) {
        super(browserName);
    }

    @Before
    public void setup(){
        setPlatform(Platform.WIN10);
    }

    /*@Test
    public void parallelGridTest() throws Exception {
        //Set Platform Name
        setPlatform(Platform.WIN10);
        //Go to Amazon.com
        System.out.println("Test is started for: "+ browserName);
        driver.get("http://www.yahoo.com");
        System.out.println("Page title is: " + driver.getTitle());
        System.out.println("Test is finished for: "+ browserName);
        //ScreenShot Section
        takeScreenShot(driver, testName.getMethodName());
    }*/

    @Test
    public void performLoginWithCorrectDataReturnsProductsPage() throws Exception {
        System.out.println("Test is started for: "+ browserName);
        driver.get("https://www.saucedemo.com/");

        WebElement txtUserName = driver.findElement(By.id("user-name"));
        txtUserName.sendKeys("standard_user");

        WebElement txtPassword = driver.findElement(By.id("password"));
        txtPassword.sendKeys("secret_sauce");

        WebElement btnLogin = driver.findElement(By.id("login-button"));
        btnLogin.click();

        //waitForPageLoad();

        WebElement lblProducts = driver.findElement(By.id("react-burger-menu-btn"));

        //System.out.println("lblLogin: "+ lblLogin.toString());

        //ScreenShot Section
        takeScreenShot(driver, testName.getMethodName());
    }

    @After
    public void tearDown() throws Exception {
       driver.quit();
    }
}
