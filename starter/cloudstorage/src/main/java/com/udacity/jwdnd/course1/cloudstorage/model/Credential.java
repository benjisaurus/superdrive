package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {

    private Integer credentialId;
    private String credentialUrl;
    private String credentialUserName;
    private String credentialKey;
    private String credentialPassword;
    private Integer userId;

    public Credential(Integer credentialId, String url, String userName, String key, String password, Integer userId) {
        this.credentialId = credentialId;
        this.credentialUrl = url;
        this.credentialUserName = userName;
        this.credentialKey = key;
        this.credentialPassword = password;
        this.userId = userId;
    }

    public Credential() {}

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
