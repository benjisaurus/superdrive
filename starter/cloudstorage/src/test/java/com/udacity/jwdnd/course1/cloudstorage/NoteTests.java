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
public class NoteTests {
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
    public void noteTests() throws InterruptedException {
        //have to sign up and log in first
        driver.get("http://localhost:" + port + "/signup");
        signup = new SignupPage(driver);
        signup.setInputFirstName("Testy");
        signup.setInputLastName("Testerton");
        signup.setInputUserName("ttesterton2");
        signup.setInputPassword("testing123");
        signup.clickSubmit();
        assertTrue(signup.getSuccessMsg().startsWith("You successfully signed up!"));
        driver.get("http://localhost:" + port + "/login");
        login = new LoginPage(driver);
        login.setInputUsername("ttesterton2");
        login.setInputPassword("testing123");
        login.clickSubmit();
        Thread.sleep(1000);
        assertEquals( driver.getCurrentUrl(), "http://localhost:" + port + "/home");
        home = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab")));
        home.clickNotesTab();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("note-add-btn")));
        assertEquals(driver.findElements(By.className("note-row")).size(), 0);
        home.clickNoteAdd();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
        home.setNoteTitle("NoteTitleOne");
        home.setNoteDescription("NoteDescriptionOne");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("note-save-btn")));
        home.clickNoteSave();

        Thread.sleep(1000);
        home.clickNotesTab();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class='btn btn-success']")));
        driver.findElement(By.cssSelector("button[class='btn btn-success']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
        home.setNoteTitle("NoteTitleOneEdit");
        home.setNoteDescription("NoteDescriptionOneEdit");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("note-save-btn")));
        home.clickNoteSave();

        Thread.sleep(1000);
        home.clickNotesTab();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("th[class='note-title-cls']")));
        assertEquals(driver.findElement(By.cssSelector("th[class='note-title-cls']")).getText(), "NoteTitleOneEdit");
        assertEquals(driver.findElement(By.cssSelector("td[class='note-desc-cls']")).getText(), "NoteDescriptionOneEdit");

        assertEquals(driver.findElements(By.className("note-row")).size(), 1);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Delete")));
        driver.findElement(By.linkText("Delete")).click();
        Thread.sleep(1000);
        home.clickNotesTab();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("note-add-btn")));
        assertEquals(driver.findElements(By.className("note-row")).size(), 0);
    }
}
