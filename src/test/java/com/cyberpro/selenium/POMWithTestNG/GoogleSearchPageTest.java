package com.cyberpro.selenium.POMWithTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class GoogleSearchPageTest {

    WebDriver driver;

    GoogleSearchPage objectrepo;

    @BeforeTest
    public void beforeTest(){

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        //It applies to all elements instead of specific elements like Explicit waits
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.google.co.uk/");



   }

   @Test(priority = 0)
   public void testGoogleSearch() throws InterruptedException {

        Thread.sleep(5000);
       GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);
       googleSearchPage.searchGoogle("facebook");

   }

   @Test(priority = 1)
   public void verifyFacebookLink(){
        objectrepo = new GoogleSearchPage(driver);
        objectrepo.clickFacebook();
   }

   @AfterTest
    public void afterTest(){
        driver.quit();
   }

}