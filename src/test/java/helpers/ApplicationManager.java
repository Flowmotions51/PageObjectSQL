package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.BasePageObject;

import java.sql.Connection;
import java.sql.SQLException;

public class ApplicationManager {

    private DatabaseHelper db;
    private BasePageObject bp;
    WebDriver webdriver_1;

    public void init() throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://fan-mag.ddns.net:58091/new_database?user=postgres&password=study";
        String driver = "org.postgresql.Driver";
        db=new DatabaseHelper(url,driver);

        System.setProperty("webdriver.gecko.driver","/Users/user/Downloads/geckodriver");
        webdriver_1=new FirefoxDriver();
        bp=new BasePageObject(webdriver_1);
        bp.init();
    }

    public void tearDown() throws SQLException {
        db.disconnect();
    }

    public DatabaseHelper getDb(){
        return db;
    }

    public BasePageObject getBp(){
        return bp;
    }

}
