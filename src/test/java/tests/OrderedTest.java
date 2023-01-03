package tests;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;
import utils.GridParallelTestBase;
import utils.Order;
import utils.OrderedRunner;
import utils.RegressionTests;

import java.util.concurrent.TimeUnit;

@RunWith(OrderedRunner.class)
public class OrderedTest {

    @Test
    @Order(order = 3)
    @Category(RegressionTests.class)
    public void test1() throws Exception {
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    @Order(order = 2)
    @Category(RegressionTests.class)
    public void test2() throws Exception {
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    @Order(order = 1)
    @Category(RegressionTests.class)
    public void test3() throws Exception {
        TimeUnit.SECONDS.sleep(5);
    }

    @After
    public void tearDown() throws Exception {
    }
}
