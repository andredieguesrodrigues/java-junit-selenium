package utils;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.function.Function;

public class GridParallelTestBase {
    //Declare DesiredCapabilities configuration variables
    protected String browserName;
    protected Platform platformName;
    protected WebDriver driver;
    //Hold all Configuration values in a LinkedList
    //Extra Usage Information: http://www.swtestacademy.com/junit-parametrized-tests/
    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<String[]>();
        //env.add(new String[]{"firefox"});
        env.add(new String[]{"chrome"});
        //env.add(new String[]{"MicrosoftEdge"});
        //add more browsers here
        return env;
    }
    //Constructor
    public GridParallelTestBase(String browserName) {
        this.browserName = browserName;
    }
    public void setPlatform (Platform platform) {
        platformName = platform;
    }
    @Before
    public void setUp() throws Exception {
        //Set DesiredCapabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //Firefox Profile Settings
        if (browserName.equals("firefox")) {
            FirefoxProfile profile = new FirefoxProfile();
            //Accept Untrusted Certificates
            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(false);
            //Use No Proxy Settings
            profile.setPreference("network.proxy.type", 0);
            //Set Firefox profile to capabilities
            capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        }

        //Set Platform
        capabilities.setPlatform(platformName);
        //Set BrowserName
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("build", "JUnit-Parallel");

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    }

    //TakeScreenShot
    public void takeScreenShot(WebDriver driver, String testName) throws IOException {
        Screenshot screenshot=new AShot().takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir") + "\\src\\main\\resources\\screenshots\\evidencia_"+testName+".png"));

    }

    public void waitForPageLoad() {

        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                System.out.println("Current Window State       : "
                        + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
                return String
                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
    }
}
