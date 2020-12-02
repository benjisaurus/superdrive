package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import org.springframework.stereotype.Service;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.service.EncryptionService;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {

        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public ArrayList<Credential> getCredentials(Integer userId) {
        return this.credentialMapper.getCredentials(userId);
    }

    public Credential getCredential(Integer credentialId) { return this.credentialMapper.getCredential(credentialId);}

    public Integer deleteCredential(Integer credentialId) { return this.credentialMapper.deleteCredential(credentialId);}

    public void addCredential(CredentialForm credentialForm) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPass = encryptionService.encryptValue(credentialForm.getPassword(), encodedKey);
        Credential credential = new Credential();
        credential.setUserId(Integer.valueOf(credentialForm.getUserId()));
        credential.setUrl(credentialForm.getUrl());
        credential.setUserName(credentialForm.getUserName());
        credential.setPassword(encryptedPass);
        credential.setKey(encodedKey);
        this.credentialMapper.createCredential(credential);
    }

    public void updateCredential(CredentialForm credentialForm) {
        Credential credential = credentialMapper.getCredential(Integer.valueOf(credentialForm.getCredentialId()));
        credential.setUserId(Integer.valueOf(credentialForm.getUserId()));
        credential.setUrl(credentialForm.getUrl());
        credential.setUserName(credentialForm.getUserName());
        String encryptedPass = encryptionService.encryptValue(credentialForm.getPassword(), credential.getKey());
        credential.setPassword(encryptedPass);
        this.credentialMapper.updateCredential(credential);
    }
}
