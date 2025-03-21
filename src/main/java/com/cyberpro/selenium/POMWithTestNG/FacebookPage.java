package com.cyberpro.selenium.POMWithTestNG;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class FacebookPage {

    private static final Logger log = LogManager.getLogger(FacebookPage.class);

    WebDriver driver;

    public FacebookPage(WebDriver driver) {
        this.driver = driver;
    }

    //Create various webElements you want to capture on this screen
    //By username_input = By.id("email");
    //By pw_input = By.id("password");
    //By login_btn = By.name("login");

    //Create different methods for automating various steps a user would perform when working on this webpage.

    //public void enterUserName(){
    //driver.findElement(username_input).send_keys("TestUser");

    //Similarly for password and any other fields
}
