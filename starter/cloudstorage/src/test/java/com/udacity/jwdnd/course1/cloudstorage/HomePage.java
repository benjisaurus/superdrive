package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement noteSubmit;

    @FindBy(id = "note-add-btn")
    private WebElement noteAddBtn;

    @FindBy(id = "note-save-btn")
    private WebElement noteSaveBtn;

    @FindBy(id = "credential-id")
    private WebElement credentialId;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "credentialSubmit")
    private WebElement credentialSubmit;

    @FindBy(id = "credential-save-btn")
    private WebElement credentialSaveBtn;

    @FindBy(id = "credential-add-btn")
    private WebElement credentialAddBtn;

    @FindBy(className = "note-row")
    private WebElement noteRow;

    @FindBy(className = "credential-row")
    private WebElement credentialRow;

    public void clickNotesTab() { this.navNotesTab.click(); }
    public void clickCredentialsTab() { this.navCredentialsTab.click(); }
    public void clickNoteSubmit() { this.noteSubmit.click(); }
    public void clickNoteAdd() { this.noteAddBtn.click(); }
    public void clickNoteSave() { this.noteSaveBtn.click(); }
    public void clickCredentialSubmit() { this.credentialSubmit.click(); }
    public void clickCredentialSave() { this.credentialSaveBtn.click(); }
    public void clickCredentialAdd() { this.credentialAddBtn.click(); }
    public void setNoteTitle(String title) {
        this.noteTitle.clear();
        this.noteTitle.sendKeys(title);
    }
    public void setNoteDescription(String desc) {
        this.noteDescription.clear();
        this.noteDescription.sendKeys(desc);
    }
    public void setCredentialUrl(String url) {
        this.credentialUrl.clear();
        this.credentialUrl.sendKeys(url);
    }
    public void setCredentialUsername(String username) {
        this.credentialUsername.clear();
        this.credentialUsername.sendKeys(username);
    }
    public void setCredentialPassword(String pass) {
        this.credentialPassword.clear();
        this.credentialPassword.sendKeys(pass);
    }
}
