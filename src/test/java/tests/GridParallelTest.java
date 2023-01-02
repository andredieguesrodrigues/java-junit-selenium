package tests;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;
import utils.GridParallelTestBase;

@RunWith(Parallelized.class)
public class GridParallelTest extends GridParallelTestBase {

    @Rule
    public TestName testName = new TestName();

    //Constructor
    public GridParallelTest(String browserName) {
        super(browserName);
    }

    @Test
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
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
