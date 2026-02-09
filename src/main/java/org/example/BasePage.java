package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasePage {

    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    WebDriver driver;
    WebDriverWait waitShort;
    WebDriverWait waitMedium;
    WebDriverWait waitLong;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitShort =  new WebDriverWait(driver, Duration.ofSeconds(5));
        this.waitMedium =  new WebDriverWait(driver, Duration.ofSeconds(15));
        this.waitLong =  new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    protected void goToPage(String url) {
        try{
            driver.get(url);
            logger.info("Loading page: " + url);
        }catch(TimeoutException e){
            logger.error("Page load timeout: {}", url);
            Assert.fail("Failed to load page: " + e.getMessage());
        }catch(WebDriverException e){
            logger.error("WebDriver error while loading: " + url);
            Assert.fail("WebDriver error: " + e.getMessage());
        }catch(Exception e){
            logger.error("Unexpected error while loading page: " + e.getMessage());
            Assert.fail("Loading failed: " + e.getMessage());
        }
    }

    protected void fillField(By locator, String value) {
        try{
            WebElement field = waitShort.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            locator
                    )
            );
            field.clear();
            field.sendKeys(value);
            logger.info("Filling Field: {} Value: {}", locator, value);
        }catch (TimeoutException e){
            logger.error("Timeout waiting for element: {}", locator);
            Assert.fail("Element not found within timeout: " + locator);
        }catch (Exception e){
            logger.error("Unexpected error filling field: {}", e.getMessage());
            Assert.fail("Failed to fill field: "+ locator +" Error: "  + e.getMessage());
        }

    }

    protected void clickElement(By locator) {
        int attempt = 0;
        int maxAttempts = 3;
        while (attempt < maxAttempts) {
            try {
                WebElement element = waitMedium.until(
                        ExpectedConditions.elementToBeClickable(
                                locator
                        )
                );
                element.click();
                logger.info("Clicked element: {}", locator);
                return;
            }catch (StaleElementReferenceException e) {
                attempt++;
                logger.warn("Stale element: {} retry {}/3", locator, attempt);
                if (attempt == maxAttempts) {
                    logger.error("Element stale after {} attempts.", maxAttempts);
                    Assert.fail("Stale element: " + locator);
                }
            }catch (ElementClickInterceptedException e) {
                logger.error("Element click intercepted (covered by another element): {}", locator);
                Assert.fail("Failed to click element: " + locator);
            }catch (TimeoutException e){
                logger.error("Timeout waiting for element to be clickable: {}", locator);
                Assert.fail("Element not clickable within timeout: " + locator);
            }catch (Exception e){
                logger.error("Unexpected error clicking element: {}", e.getMessage());
                Assert.fail("Failed to click: "+ locator +" Error: "  + e.getMessage());
            }
        }
    }

    protected boolean elementIsVisible(By locator) {
       try{
           WebElement element = waitMedium.until(
                   ExpectedConditions.visibilityOfElementLocated(
                           locator
                   )
           );
           logger.info("Element is visible: {}", locator);
           return element.isDisplayed();
       }catch (TimeoutException e){
           logger.error("Element not visible: {}", locator);
           return false;
       }catch (Exception e){
           logger.error("Error checking visibility: {}", e.getMessage());
           return false;
       }
    }

    protected String getTextOfElement(By locator) {
        try{
            WebElement element = waitShort.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            locator
                    )
            );
            String elementText =  element.getText().toLowerCase();
            logger.info("Got element text:{} From element: {}", elementText, locator);
            return elementText;
        }catch (TimeoutException e){
            logger.error("Timeout getting text from: {}", locator);
            return "";
        }catch (Exception e){
            logger.error("Error getting text: {}", e.getMessage());
            return "";
        }
    }

    protected boolean isCorrectTextDisplayed(By locator, String correctText) {
        try{
            if(elementIsVisible(locator)){
                String textOfElement = getTextOfElement(
                        locator
                );
                boolean isTextCorrect = textOfElement.contains(correctText);
                if(isTextCorrect){
                    logger.info("Text is correct - Expected: {}", correctText);
                }else{
                    logger.error("Wrong text - Expected: {}", correctText);
                }
                return isTextCorrect;
            }else{
                logger.error("Element not found: {}", locator);
                return false;
            }
        }catch(Exception e){
            logger.error("Error checking text: {}", e.getMessage());
            return false;
        }
    }

    public void takeScreenshot(String testName){
        try{
            File screenshotDir = new File("screenshots");
            if(!screenshotDir.exists()){
                screenshotDir.mkdir();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";

            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File("screenshots/", fileName);

            FileUtils.copyFile(srcFile, destFile);
            logger.info("Screenshot saved to: {}", destFile.getAbsolutePath());
        }catch(Exception e){
            logger.error("Failed to take screenshot: {}", e.getMessage());
        }
    }

}