package utils;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.OrderedTest;

@RunWith(Categories.class)
@Categories.IncludeCategory({RegressionTests.class})
@Suite.SuiteClasses({

        OrderedTest.class

})

public class RunTestSuite {

}
