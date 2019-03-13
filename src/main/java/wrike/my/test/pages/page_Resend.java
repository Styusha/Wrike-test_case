package wrike.my.test.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;


public class page_Resend {

    @FindBy(css ="[data-question='How would you describe your interest in using a solution like Wrike?']")
    private List<WebElement>   interestINsolution_rButton;
    @FindBy(css="div[data-code = 'team_members']>label>button")
    private List<WebElement>  ideallyTeamMembers_rButton;
    @FindBy(css="div[data-code = 'primary_business']>label>button")
    private List<WebElement>   teamManageWork_rButton;
    @FindBy (css="[placeholder='Your comment']")
    private WebElement commentToOther;
    @FindBy (css =".survey-form>button")
    private WebElement submit_button;
    @FindBy (css =".survey-success")
    private WebElement survey_success;
    @FindBy (css = ".wg-cell--order-1>p>button")
    private WebElement resendEmail_button;
    @FindBy (css = "li[class = 'wg-footer__social-item']:nth-child(1)>a")
    private WebElement twitter_element;

    @Step ("Fill in the Q&A section (like random generated answers)+check")
    public void FillQAsection(WebDriver driver){
        Random random = new Random();
        interestINsolution_rButton.get(random.nextInt(interestINsolution_rButton.size())).click();
        ideallyTeamMembers_rButton.get(random.nextInt(ideallyTeamMembers_rButton.size())).click();
        WebElement teamManageWork = teamManageWork_rButton.get(random.nextInt(teamManageWork_rButton.size()));
        if(teamManageWork.getAttribute("innerHTML").contains("Other"))
        {   teamManageWork.click();
            commentToOther.sendKeys("Some answer");
        }
        else{
            teamManageWork.click();
        }
        submit_button.click();
        WebDriverWait wait_for_submit = new WebDriverWait(driver,10);
        wait_for_submit.until(visibilityOf(survey_success));

        assertTrue("Answers aren't submitted", survey_success.isDisplayed());
    }

    @Step("Click on _Resend email_ + check")
    public void resendEmail_click(WebDriver driver){
        resendEmail_button.click();
        WebDriverWait wait_for_resend = new WebDriverWait(driver,5);
        wait_for_resend.until(ExpectedConditions.invisibilityOf(resendEmail_button));
        assertFalse("Email wasn't resend",resendEmail_button.isDisplayed());
    }

    @Step("Check that section _Follow us_ contains the Twitter_ button that leads to the correct urlexport MAVEN_OPTS=\"-Xmx4G\"")
    public void CheckingTwitter(){
        assertEquals("URL isn't correct","https://twitter.com/wrike",twitter_element.getAttribute("href"));
    }


}