package com.udacity.jwdnd.course1.cloudstorage.model;

public class CredentialForm {

    private String credentialId;
    private String credentialUrl;
    private String credentialUserName;
    private String credentialKey;
    private String credentialPassword;
    private String userId;

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getCredentialUrl() {
        return credentialUrl;
    }

    public void setCredentialUrl(String url) {
        this.credentialUrl = url;
    }

    public String getCredentialUserName() {
        return credentialUserName;
    }

    public void setCredentialUserName(String userName) {
        this.credentialUserName = userName;
    }

    public String getCredentialKey() {
        return credentialKey;
    }

    public void setCredentialKey(String key) {
        this.credentialKey = key;
    }

    public String getCredentialPassword() {
        return credentialPassword;
    }

    public void setCredentialPassword(String password) {
        this.credentialPassword = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
