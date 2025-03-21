package com.cyberpro.selenium.POMWithTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    WebDriver driver;

    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator for dashboard header
//    private By dashboardHeader = By.xpath("//h6[contains(text(),'Dashboard')]");
    private By dashboardHeader = By.xpath("//span[@class='oxd-topbar-header-breadcrumb']");

//    WebElement checkbox = driver.findElement(By.xpath("//span[@class='oxd-topbar-header-breadcrumb']"));

    // Method to verify dashboard is displayed
    public boolean isDashboardDisplayed() {
        return driver.findElement(dashboardHeader).isDisplayed();
    }
}


