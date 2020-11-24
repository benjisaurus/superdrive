package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    public SignupPage(WebDriver driver) {
        PageFactory.initElements( driver, this);
    }

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUserName")
    private WebElement inputUserName;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    @FindBy(id = "success-msg")
    private WebElement successMsg;

    public void setInputFirstName(String inputFirstName) {
        this.inputFirstName.sendKeys(inputFirstName);
    }

    public void setInputLastName(String inputLastName) {
        this.inputLastName.sendKeys(inputLastName);
    }

    public void setInputPassword(String inputPassword) {
        this.inputPassword.sendKeys(inputPassword);
    }

    public void setInputUserName(String inputUserName) {
        this.inputUserName.sendKeys(inputUserName);
    }

    public void clickSubmit() { this.submitButton.click(); }

    public String getSuccessMsg() {
        return this.successMsg.getText();
    }
}
