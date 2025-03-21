package com.cyberpro.selenium.POMWithTestNG;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSearchPage {

    private static final Logger log = LogManager.getLogger(GoogleSearchPage.class);

    WebDriver driver;

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    By searchBox = By.xpath("//textarea[@name = 'q']");
//    By search_btn = By.xpath("(//input[@name='btnk'])[1]");
    By facebook_link = By.xpath("//a//h3[text()='Facebook - log in or sign up']");

    By search_button = By.xpath("//input[@type='submit' and @name='btnK']");



    public void searchGoogle(String searchInput) {
        try {


            driver.findElement(searchBox).sendKeys(searchInput);
            Thread.sleep(1000);
            driver.findElement(search_button).click();
            Thread.sleep(3000);

        } catch (Exception ex) {
            log.error("Exception caught : {}", ex.getMessage());
        }
    }

    public void clickFacebook(){
        try{
            driver.findElement(facebook_link).click();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("Exception.caught : {}", e.getMessage());
        }
    }
}
