package tests;

import helpers.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class BaseTest {

    static ApplicationManager app= new ApplicationManager();

    @BeforeSuite
    public void beforeSuiteTest() throws ClassNotFoundException, SQLException {
        app.init();
    }

    @AfterSuite
    public void afterSuiteTest() throws ClassNotFoundException, SQLException {
        app.tearDown();
    }

    @DataProvider(name = "credentialsProvider")
    public static Object[][] credentialsProvider() throws SQLException {


        return new Object[][]{

               {app.getDb().getData().get(0).login(),app.getDb().getData().get(0).password()},
                {app.getDb().getData().get(1).login(),app.getDb().getData().get(1).password()}

       };
    }

    @Test(dataProvider = "credentialsProvider")
    public void loginToHH(String login, String password) throws SQLException {
       app.getBp().sendData(login, password);
        app.getBp().loginToHh();
       app.getBp().logoutFromHh();
        Assert.assertTrue(app.getBp().isLoginButtonDisplayed());

        System.out.println();
    }

}
