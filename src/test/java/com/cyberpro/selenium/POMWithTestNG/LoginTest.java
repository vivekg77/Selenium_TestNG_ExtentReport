package com.cyberpro.selenium.POMWithTestNG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    private static final Logger log = LogManager.getLogger(LoginTest.class);

    public static ExtentSparkReporter sparkReporter;

    public static ExtentReports extentReports;

    public static ExtentTest test;

    @BeforeTest
    public void setUp() {

        log.info("Starting test setup...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));


        // Initialize Page Objects
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        log.info("Test setup completed.");

        initializeReport();

    }

    @DataProvider(name = "loginData")
    public Object[][] getData() throws IOException{

        String filePath = "src/test/resources/testdata.xlsx";
        return ExcelUtils.getTestData(filePath, "Sheet1");
    }

    @Test(dataProvider = "loginData")
    public void testValidLogin(String username, String password) throws IOException {
        log.info("Opening OrangeHRM login page...");
        //Given
        driver.get("https://opensource-demo.orangehrmlive.com/");

        String methodName = new Exception().getStackTrace()[0].getMethodName();
        test = extentReports.createTest(methodName, "Sign In and verify page title");
        test.log(Status.INFO, "Starting Open login page and verify if the link takes you to Dashboard page");
        test.assignCategory("Smoke Test");

        captureScreenshot(driver);
        //When
        // Perform login
        log.info("Entering login credentials...");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        //Then
        // Validate login success
        log.info("Verifying login success...");
        assertTrue(dashboardPage.isDashboardDisplayed(), "Login successful!");
        //System.out.println("✅ Login successful!");
        log.info("✅ Login successful!");
    }

    public void initializeReport(){

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "/Reports/extentSparkReport.html");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Automation Test Execution Report");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extentReports = new ExtentReports();

        extentReports.attachReporter(sparkReporter);
    }

    public static String captureScreenshot(WebDriver driver) throws IOException {

        String FileSeparator = System.getProperty("file.separator");
        String Extent_report_path = "."+FileSeparator+"Reports";
        String Screenshotpath = Extent_report_path+FileSeparator+"screenshots";
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String screenshotName = "screenshot"+Math.random()+".png";
        String screenshotpath = Screenshotpath+FileSeparator+screenshotName;
        FileUtils.copyFile(src, new File(screenshotpath));
        return "."+FileSeparator+"screenshots"+FileSeparator+screenshotName;
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        extentReports.flush();
    }
}
