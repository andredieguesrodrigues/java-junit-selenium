package tests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Helper;

import java.io.IOException;
import java.util.Properties;

import static utils.Support.loadConfig;

public class FirstTest extends Helper  {

    public static String status = "passed";

    @BeforeClass
    public static void setupTests() throws Exception {
        System.out.println("start of tests!");
        Properties prop;

        prop = loadConfig();
        String environment = prop.getProperty("environment");

        if (environment.toString().equalsIgnoreCase("chrome"))
        {
            setupThread("chrome");
        }
        else if (environment.toString().equalsIgnoreCase("firefox"))
        {
            setupThread("firefox");
        }
        else if (environment.toString().equalsIgnoreCase("edge"))
        {
            setupThread("edge");
        }

    }

    @Test
    public void DefaultTests(){
        //screen elements

        //methods

    }

    @Test
    public void testGoogle() throws IOException {
        String search_string =" LambdaTest";
        String exp_title = "Most Powerful Cross Browser Testing Tool Online | LambdaTest";

        WebDriver webdriver = getDriver();
        webdriver.navigate().to("https://www.google.com");
        webdriver.manage().window().maximize();

        takeScreenShot(webdriver);

        System.out.println("Started session");


        try {
            /* Enter the search term in the Google Search Box */
            WebElement search_box = webdriver.findElement(By.xpath("//input[@name='q']"));
            search_box.sendKeys(search_string);

            search_box.submit();
            Thread.sleep(3000);

            /* Click on the first result which will open up the LambdaTest homepage */
            WebElement lt_link = webdriver.findElement(By.xpath("//span[.='LambdaTest: Most Powerful Cross Browser Testing Tool Online']"));
            lt_link.click();
            Thread.sleep(5000);

            String curr_window_title = webdriver.getTitle();
            Assert.assertEquals(curr_window_title, exp_title);
            status = "passed";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (getDriver() != null)
        {
            tearDownDriver();
        }
    }

    @AfterClass
    public static void tearDown(){
        tearDownDriver();
        System.out.println("end of tests!");
    }

}
