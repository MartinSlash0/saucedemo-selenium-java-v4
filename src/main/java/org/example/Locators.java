package org.example;

import org.openqa.selenium.By;


public class Locators {
    //-Login page locators
    //=Check
    static final By LoginLogoLocator = By.cssSelector("[class='login_logo']");
    static final By ErrorMessageLocator = By.cssSelector("[data-test='error']");
    //=Fields
    static final By UsernameFieldLocator = By.id("user-name");
    static final By PasswordFieldLocator = By.id("password");
    //=Buttons
    static final By LoginButtonLocator = By.id("login-button");

    //-Store page locators
    //=Check
    static final By AppLogoLocator = By.cssSelector("[class='app_logo']");
    static final By CartTitleLocator = By.cssSelector("[class='title']");
    static final By OrderInfoErrorLocator = By.cssSelector("[data-test='error']");
    static final By CompleteOrderHeaderLocator = By.cssSelector("[class='complete-header']");
    //=Fields
    static final By FirstNameFieldLocator = By.id("first-name");
    static final By LastNameFieldLocator = By.id("last-name");
    static final By PostalcodeFieldLocator = By.id("postal-code");
    //=Buttons
    static final By SideBarButtonLocator = By.id("react-burger-menu-btn");
    static final By LogoutButtonLocator = By.id("logout_sidebar_link");
    static final By BackPackBuyButtonLocator = By.id("add-to-cart-sauce-labs-backpack");
    static final By CartButtonLocator = By.cssSelector("[class='shopping_cart_link']");
    static final By CheckoutButtonLocator = By.id("checkout");
    static final By ContinueButtonLocator = By.id("continue");
    static final By FinishOrderButtonLocator = By.id("finish");
    //=Items
    static final By ItemNameLocator = By.cssSelector("[class='inventory_item_name']");

}
