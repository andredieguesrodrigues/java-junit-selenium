package base;

import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import utils.GeneralConstants;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

public class BaseTest {

    protected String browserName;
    protected Platform platformName;
    protected WebDriver driver;
    protected PageGenerator page;

    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<String[]>();

        env.add(new String[]{"chrome"});
        //env.add(new String[]{"firefox"});
        //env.add(new String[]{"MicrosoftEdge"});

        return env;
    }

    public BaseTest(String browserName) throws IOException {
        this.browserName = browserName;
    }

    public void setPlatform (Platform platform) {
        platformName = platform;
    }

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browserName.equals("firefox")) {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(false);
            profile.setPreference("network.proxy.type", 0);
            capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        }

        capabilities.setPlatform(platformName);
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("build", "JUnit-Parallel");

        driver = new RemoteWebDriver(new URL("http://"+GeneralConstants.BASE_GRID+":4444/wd/hub"), capabilities);
        page = new PageGenerator(driver);

    }

    public void takeScreenShot(WebDriver driver, String testName) throws IOException {

        if(testName.contains("[0]"))
            testName = testName.replace("[0]","_chrome");

        if(testName.contains("[1]"))
            testName = testName.replace("[1]","_firefox");

        if(testName.contains("[2]"))
            testName = testName.replace("[2]","_edge");

        Screenshot screenshot=new AShot().takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir") + "/src/test/resources/screenshots/img_"+testName+".png"));

    }
}
