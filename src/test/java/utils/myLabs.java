package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class myLabs {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //implicit wait
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //URL launch
        driver.get("https://www.saucedemo.com/");
        // identify div elements with same class
        List<WebElement> l = driver.findElements(By.className("login_wrapper"));
        // list iteration
        System.out.println("Elements are: ");
        for(int j = 0; j< l.size(); j++) {
            //identify separate div
            String v = l.get(j).getText();
            System.out.println("Elements are: ");
            System.out.println(v);
        }
        //browser close
        driver.close();
    }
}
