package org.example;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class StorePage extends BasePage {

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public void fillOrderInfo(String firstName, String lastName, String postalCode){
        fillField(Locators.FirstNameFieldLocator, firstName);
        fillField(Locators.LastNameFieldLocator, lastName);
        fillField(Locators.PostalcodeFieldLocator, postalCode);
    }

    public void noInfoErrorFunctionality(){
        try{
            //Testing First name validation
            clickElement(Locators.ContinueButtonLocator);
            if (isCorrectTextDisplayed(Locators.OrderInfoErrorLocator, Config.FirstNameErrorTestData)){
                fillField(Locators.FirstNameFieldLocator,Config.FirstName);
            }else{
                logger.error("First name error message is not displayed or wrong!");
                Assert.fail("First name error message is not displayed or wrong!");
            }
            //Testing Last name validation
            clickElement(Locators.ContinueButtonLocator);
            if (isCorrectTextDisplayed(Locators.OrderInfoErrorLocator, Config.LastNameErrorTestData)){
                fillField(Locators.LastNameFieldLocator,Config.LastName);
            }else{
                logger.error("Last name error message is not displayed or wrong!");
                Assert.fail("Last name error message is not displayed or wrong!");
            }
            //Testing Postal code validation
            clickElement(Locators.ContinueButtonLocator);
            if (isCorrectTextDisplayed(Locators.OrderInfoErrorLocator, Config.PostalCodeErrorTestData)){
                fillField(Locators.PostalcodeFieldLocator,Config.PostalCode);
            }else{
                logger.error("Postal code error message is not displayed or wrong!");
                Assert.fail("Postal code error message is not displayed or wrong!");
            }
            //Success message
            logger.info("Order form validations tests passed!");
            clickElement(Locators.ContinueButtonLocator);
        }catch (Exception e){
            logger.error("Error in order form validations: {}", e.getMessage());
            throw e;
        }
    }
}