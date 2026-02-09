package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeleniumTest {

    WebDriver driver;
    LoginPage loginPage;
    StorePage storePage;

    private static final Logger logger = LoggerFactory.getLogger(SeleniumTest.class);

    @BeforeMethod
    public void beforeMethod() {
        //-Setup driver
        try{
            driver = new FirefoxDriver();
            loginPage = new LoginPage(driver);
            storePage = new StorePage(driver);
            driver.manage().window().maximize();

            logger.info("Setup completed.");
        }catch (Exception e){
            logger.error("Setup Failed.");
            throw e;
        }
    }

    @Test
    public void invalidLoginTest(){
        try{
            logger.info("Starting invalid login test...");
            //-Loading test page
            loginPage.goToPage(Config.URL);
            //-Check page
            //=Checks if login page logo is displayed
            Assert.assertTrue(loginPage.isCorrectTextDisplayed(
                    Locators.LoginLogoLocator,
                    Config.LogoTextTestData
            ), "Login page is not loaded");
            //-Logging in with valid credential
            loginPage.login(Config.InvalidUserName, Config.InvalidPassword);
            //-Check error message
            //=Checks if error message is displayed and is the correct one
            Assert.assertTrue(loginPage.isCorrectTextDisplayed(
                    Locators.ErrorMessageLocator,
                    Config.ErrorTextTestData
            ), "Wrong error message or is not displayed");
        }catch (AssertionError e){
            logger.error("Assertion failed: {}", e.getMessage());
            loginPage.takeScreenshot("invalidLoginTest_error");
            throw e;
        }catch (Exception e){
            logger.error("Unexpected error: {}", e.getMessage());
            loginPage.takeScreenshot("invalidLoginTest_error");
            throw e;
        }finally {
            logger.info("Ending invalid login test...");
        }
    }

    @Test
    public void validLoginTest() {
        try{
            logger.info("Starting valid login test...");
            //-Loading test page
            loginPage.goToPage(Config.URL);
            //-Check page
            //=Checks if login page logo is displayed
            Assert.assertTrue(loginPage.isCorrectTextDisplayed(
                    Locators.LoginLogoLocator,
                    Config.LogoTextTestData
            ), "Login page is not loaded");
            //-Logging in with valid credential
            loginPage.login(Config.UserName, Config.Password);
            //-Check login successful
            //=Checks if store logo is displayed
            Assert.assertTrue(loginPage.isCorrectTextDisplayed(
                    Locators.AppLogoLocator,
                    Config.LogoTextTestData
            ), "Login failed");
        }catch (AssertionError e){
            logger.error("Assertion failed: {}", e.getMessage());
            loginPage.takeScreenshot("loginTest_error");
            throw e;
        }catch (Exception e){
            logger.error("Unexpected error: {}", e.getMessage());
            loginPage.takeScreenshot("loginTest_error");
            throw e;
        }finally {
            logger.info("Ending valid login test...");
        }
    }

    @Test
    public void logoutTest () {
        try{
            logger.info("Starting logout test...");
            //-Loading test page
            loginPage.goToPage(Config.URL);
            //-Check page
            //=Checks if login page logo is displayed
            Assert.assertTrue(loginPage.isCorrectTextDisplayed(
                    Locators.LoginLogoLocator,
                    Config.LogoTextTestData
            ), "Login page is not loaded");
            //Logging in with valid credential
            loginPage.login(Config.UserName, Config.Password);
            //-Check login successful
            //=Checks if store logo is displayed
            Assert.assertTrue(loginPage.isCorrectTextDisplayed(
                    Locators.AppLogoLocator,
                    Config.LogoTextTestData
            ), "Login failed");
            //-Logout
            loginPage.logout();
            //-Check logout successful
            //=Checks if login page logo is displayed
            Assert.assertTrue(loginPage.isCorrectTextDisplayed(
                    Locators.LoginLogoLocator,
                    Config.LogoTextTestData
            ), "Logout failed");
        }catch (AssertionError e){
            logger.error("Assertion failed: {}", e.getMessage());
            loginPage.takeScreenshot("logoutTest_error");
            throw e;
        }catch (Exception e){
            logger.error("Unexpected error: {}", e.getMessage());
            loginPage.takeScreenshot("logoutTest_error");
            throw e;
        }finally {
            logger.info("Ending logout test...");
        }
    }

    @Test
    public void checkoutTest () {
        try{
            logger.info("Starting checkout test...");
            //-Loading test page
            loginPage.goToPage(Config.URL);
            //-Check page
            //=Checks if login page logo is displayed
            Assert.assertTrue(loginPage.isCorrectTextDisplayed(
                    Locators.LoginLogoLocator,
                    Config.LogoTextTestData
            ), "Login page is not loaded");
            //Logging in with valid credential
            loginPage.login(Config.UserName, Config.Password);
            //-Check login successful
            //=Checks if store logo is displayed
            Assert.assertTrue(loginPage.isCorrectTextDisplayed(
                    Locators.AppLogoLocator,
                    Config.LogoTextTestData
            ), "Login failed");
            //-Add item to cart and open cart
            storePage.clickElement(Locators.BackPackBuyButtonLocator);
            storePage.clickElement(Locators.CartButtonLocator);
            //-Check cart
            //=Checks if cart title and item name are displayed
            Assert.assertTrue(storePage.isCorrectTextDisplayed(
                    Locators.CartTitleLocator,
                    Config.CartTitleTestData
            ) && storePage.isCorrectTextDisplayed(
                    Locators.ItemNameLocator,
                    Config.BackPackNameTestData
            ),"Cart is empty");
            //-Checkout
            storePage.clickElement(Locators.CheckoutButtonLocator);
            //-Check info page
            //=Checks if title is displayed and has correct text
            Assert.assertTrue(storePage.isCorrectTextDisplayed(
                    Locators.CartTitleLocator,
                    Config.CheckoutTitleInfoTestData
            ),"Failed to continue to checkout");
            storePage.fillOrderInfo(Config.FirstName, Config.LastName, Config.PostalCode);
            storePage.clickElement(Locators.ContinueButtonLocator);
            //-Check overview page
            //=Checks if title is displayed and has correct text
            Assert.assertTrue(storePage.isCorrectTextDisplayed(
                    Locators.CartTitleLocator,
                    Config.CheckoutTitleOverviewTestData
            ),"Failed to continue to order overview");
            storePage.clickElement(Locators.FinishOrderButtonLocator);
            //-Check finished order
            //=Checks if header is displayed with correct text
            Assert.assertTrue(storePage.isCorrectTextDisplayed(
                    Locators.CompleteOrderHeaderLocator, Config.CompleteOrderTestData
            ),"Order completion failed");
        }catch  (AssertionError e){
            logger.error("Assertion failed: {}", e.getMessage());
            storePage.takeScreenshot("checkoutTest_error");
            throw e;
        }catch (Exception e){
            logger.error("Unexpected error: {}", e.getMessage());
            storePage.takeScreenshot("checkoutTest_error");
            throw e;
        }finally {
            logger.info("Ending checkout test...");
        }
    }

    @Test
    public void checkoutNoInfoTest () {
        try{
            logger.info("Starting order form validation test...");
            //-Loading test page
            loginPage.goToPage(Config.URL);
            //-Check page
            //=Checks if login page logo is displayed
            Assert.assertTrue(loginPage.isCorrectTextDisplayed(
                    Locators.LoginLogoLocator, Config.LogoTextTestData
            ), "Login page is not loaded");
            //Logging in with valid credential
            loginPage.login(Config.UserName, Config.Password);
            //-Check login successful
            //=Checks if store logo is displayed
            Assert.assertTrue(loginPage.isCorrectTextDisplayed(
                    Locators.AppLogoLocator, Config.LogoTextTestData
            ), "Login failed");
            //-Add item to cart and open cart
            storePage.clickElement(Locators.BackPackBuyButtonLocator);
            storePage.clickElement(Locators.CartButtonLocator);
            //-Check cart
            //=Checks if cart title and item name are displayed
            Assert.assertTrue(storePage.isCorrectTextDisplayed(
                    Locators.CartTitleLocator, Config.CartTitleTestData
            ) && storePage.isCorrectTextDisplayed(
                    Locators.ItemNameLocator, Config.BackPackNameTestData
            ),"Cart is empty");
            storePage.clickElement(Locators.CheckoutButtonLocator);
            //-Check info page
            //=Checks if title is displayed and has correct text
            Assert.assertTrue(storePage.isCorrectTextDisplayed(
                    Locators.CartTitleLocator,
                    Config.CheckoutTitleInfoTestData
            ),"Failed to continue to checkout");
            //-Check no info errors
            //=Checks if you can continue without filling info and if the correct errors are displayed
            storePage.noInfoErrorFunctionality();
            //-Check overview page
            //=Checks if title is displayed and has correct text
            Assert.assertTrue(storePage.isCorrectTextDisplayed(
                    Locators.CartTitleLocator,
                    Config.CheckoutTitleOverviewTestData)
            );
        }catch (AssertionError e){
            logger.error("Assertion failed: {}", e.getMessage());
            storePage.takeScreenshot("checkoutNoInfoTest_error");
            throw e;
        }catch (Exception e){
            logger.error("Unexpected error: {}", e.getMessage());
            storePage.takeScreenshot("checkoutNoInfoTest_error");
            throw e;
        }finally {
            logger.info("Ending order form validation test...");
        }
    }

    @AfterMethod
    public void afterMethod() {
        try{
            if (driver != null) {
                driver.quit();
            }
        }catch (Exception e){
            logger.error("Error closing browser: {}", e.getMessage());
        }finally {
            driver = null;
            logger.info("Cleanup complete! Browser closed.");
        }
    }
}
