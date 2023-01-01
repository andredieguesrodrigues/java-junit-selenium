package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Helper {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    public static String remote_url = "http://localhost:4444";

    public static void setupThread(String browserName) throws MalformedURLException
    {
        if(browserName.equalsIgnoreCase("chrome"))
        {
            System.out.println("Inside Chrome");
            ChromeOptions options = new ChromeOptions();
            driver.set(new RemoteWebDriver(new URL(remote_url), options));
        }
        else if (browserName.equalsIgnoreCase("firefox"))
        {
            System.out.println("Inside Firefox");
            FirefoxOptions options = new FirefoxOptions();
            driver.set(new RemoteWebDriver(new URL(remote_url), options));
        }
        else if (browserName.equalsIgnoreCase("edge"))
        {
            System.out.println("Inside Edge");
            EdgeOptions options = new EdgeOptions();
            driver.set(new RemoteWebDriver(new URL(remote_url), options));
        }
    }

    public void takeScreenShot(WebDriver driver) throws IOException {
        Screenshot screenshot=new AShot().takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir") + "\\src\\main\\resources\\screenshots\\evidencia.png"));

    }

    public static WebDriver getDriver()
    {
        return driver.get();
    }

    public static void tearDownDriver()
    {
        getDriver().quit();
    }

}
