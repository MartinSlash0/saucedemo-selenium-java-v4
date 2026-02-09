package org.example;

import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    //Filling username and password fields with credentials and clicking login
    public void login(String username, String password) {
        fillField(Locators.UsernameFieldLocator, username);
        fillField(Locators.PasswordFieldLocator, password);
        clickElement(Locators.LoginButtonLocator);
    }
    //Open sidebar locates logout button and clicks it
    public void logout() {
        clickElement(Locators.SideBarButtonLocator);
        clickElement(Locators.LogoutButtonLocator);
    }
}