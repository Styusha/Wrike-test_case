package wrike.my.test;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import wrike.my.test.pages.page_Home;
import wrike.my.test.pages.page_Resend;


public class myTest {
    private static WebDriver driver;
    private static page_Home page_home;
    private static page_Resend page_resend;

    @BeforeClass
    public static void setDriver() {
      //  System.setProperty("webdriver.chrome.driver", "C:\\web_drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        page_home=PageFactory.initElements(driver, page_Home.class);
        page_resend=PageFactory.initElements(driver,page_Resend.class);

    }

    @AfterClass
    public static void closeDriver() {
        driver.close();
    }


    @Test
    public void wrike_test() {

        page_home.openWrikeHomepage(driver);        //Step ("Open url: wrike.com")

        page_home.getStartedForFreeButton(driver);  //Step ("Click _Get_started_for_free button_ near _Login_ button")

        page_home.email_fillIn();                   //Step ("Fill in the email field with mask <random_text>+wpt@wriketask.qaa")

        page_home.createAcc_move_nextPage(driver);  //Step("Click on _Create_my_Wrike_account_ button + check moving to the next page")

        page_resend.FillQAsection(driver);          //Step("Fill in the Q&A section (like random generated answers)+check")

        page_resend.resendEmail_click(driver);      //@Step("Click on _Resend email_ + check")

        page_resend.CheckingTwitter();              //Step("Check that section _Follow us_ contains the Twitter_ button that leads to the correct url")
    }
}