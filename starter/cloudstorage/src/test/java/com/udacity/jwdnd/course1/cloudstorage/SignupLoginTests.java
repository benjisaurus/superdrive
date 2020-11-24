package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignupLoginTests {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private SignupPage signup;
    private LoginPage login;
    //private HomePage home;

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
    public void noUnauthorizedAccess() {
        driver.get("http://localhost:" + port + "/home");
        assertNotEquals( driver.getCurrentUrl(), "http://localhost:" + port + "/home");
    }

    @Test
    public void testSignup() throws InterruptedException {
        driver.get("http://localhost:" + port + "/signup");
        signup = new SignupPage(driver);
        signup.setInputFirstName("Testy");
        signup.setInputLastName("Testerton");
        signup.setInputUserName("ttesterton");
        signup.setInputPassword("testing123");
        signup.clickSubmit();
        assertTrue(signup.getSuccessMsg().startsWith("You successfully signed up!"));
        driver.get("http://localhost:" + port + "/login");
        login = new LoginPage(driver);
        login.setInputUsername("ttesterton");
        login.setInputPassword("testing123");
        login.clickSubmit();
        Thread.sleep(1000);
        assertEquals( driver.getCurrentUrl(), "http://localhost:" + port + "/home");
    }
}
