package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTests {
    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private SignupPage signup;
    private LoginPage login;
    private HomePage home;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @Test
    public void credentialTests() throws InterruptedException {
        //have to sign up and log in first
        driver.get("http://localhost:" + port + "/signup");
        signup = new SignupPage(driver);
        signup.setInputFirstName("Testy");
        signup.setInputLastName("Testerton");
        signup.setInputUserName("ttesterton3");
        signup.setInputPassword("testing123");
        signup.clickSubmit();
        assertTrue(signup.getSuccessMsg().startsWith("You successfully signed up!"));
        driver.get("http://localhost:" + port + "/login");
        login = new LoginPage(driver);
        login.setInputUsername("ttesterton3");
        login.setInputPassword("testing123");
        login.clickSubmit();
        Thread.sleep(1000);
        assertEquals( driver.getCurrentUrl(), "http://localhost:" + port + "/home");
        home = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-credentials-tab")));
        home.clickCredentialsTab();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-add-btn")));
        assertEquals(driver.findElements(By.className("credential-row")).size(), 0);
        home.clickCredentialAdd();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
        home.setCredentialUrl("http://CredUrl.dom");
        home.setCredentialUsername("bob");
        home.setCredentialPassword("pass");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-save-btn")));
        home.clickCredentialSave();

        Thread.sleep(1000);
        home.clickCredentialsTab();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class='btn btn-success']")));
        driver.findElement(By.cssSelector("button[class='btn btn-success']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
        home.setCredentialUrl("http://CredUrlEdit.dom");
        home.setCredentialUsername("bobedit");
        home.setCredentialPassword("passedit");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-save-btn")));
        home.clickCredentialSave();

        Thread.sleep(1000);
        home.clickCredentialsTab();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("th[class='credential-url-cls']")));
        assertEquals(driver.findElement(By.cssSelector("th[class='credential-url-cls']")).getText(), "http://CredUrlEdit.dom");
        assertEquals(driver.findElement(By.cssSelector("td[class='credential-username-cls']")).getText(), "bobedit");

        assertEquals(driver.findElements(By.className("credential-row")).size(), 1);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Delete")));
        driver.findElement(By.linkText("Delete")).click();
        Thread.sleep(1000);
        home.clickCredentialsTab();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-add-btn")));
        assertEquals(driver.findElements(By.className("credential-row")).size(), 0);
    }
}
