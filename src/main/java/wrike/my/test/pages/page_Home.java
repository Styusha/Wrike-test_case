package wrike.my.test.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class page_Home {

    @FindBy (css =".wg-header__sticky-mainmenu .wg-header__free-trial-button")
        private WebElement getStarted_button;
    @FindBy(css =".modal-form-trial__form")
        private WebElement trialForm;
    @FindBy(css = ".modal-form-trial__input")
        private WebElement emailInput;
    @FindBy(css=".modal-form-trial__submit")
        private WebElement createAccount_button;


    @Step ("Open url: wrike.com")
        public void openWrikeHomepage(WebDriver driver){

         driver.navigate().to("https://www.wrike.com/vb/?utm_expid=75732941-113._QwudDuLQTa0farhZW-FBA.2");
    }

    @Step ("Click _Get_started_for_free button_ near _Login_ button")
        public void getStartedForFreeButton(WebDriver driver){
         getStarted_button.click();
         WebDriverWait wait_form_appears = new WebDriverWait(driver,5);
         wait_form_appears.until(ExpectedConditions.visibilityOf(trialForm));
    }

    @Step ("Fill in the email field with mask <random_text>+wpt@wriketask.qaa")
        public void email_fillIn(){
        emailInput.sendKeys("qwerty"+"+wpt@wriketask.qaa");
    }

    @Step("Click on _Create_my_Wrike_account_ button + check moving to the next page")
        public void createAcc_move_nextPage(WebDriver driver) {
        createAccount_button.click();
        WebDriverWait nextPage = new WebDriverWait(driver,5);
        nextPage.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://www.wrike.com/vb/?utm_expid=75732941-113._QwudDuLQTa0farhZW-FBA.2")));
        assertEquals("The current page is wrong","https://www.wrike.com/resend/",driver.getCurrentUrl());

    }
}
