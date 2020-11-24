package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import org.springframework.stereotype.Service;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.service.HashService;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final HashService hashService;

    public CredentialService(CredentialMapper credentialMapper, HashService hashService) {

        this.credentialMapper = credentialMapper;
        this.hashService = hashService;
    }

    public ArrayList<Credential> getCredentials(Integer userId) {
        return this.credentialMapper.getCredentials(userId);
    }

    public Integer deleteCredential(Integer credentialId) { return this.credentialMapper.deleteCredential(credentialId);}

    public void addCredential(CredentialForm credentialForm) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(credentialForm.getCredentialPassword(), encodedSalt);
        Credential credential = new Credential();
        credential.setUserId(Integer.valueOf(credentialForm.getUserId()));
        credential.setCredentialUrl(credentialForm.getCredentialUrl());
        credential.setCredentialUserName(credentialForm.getCredentialUserName());
        credential.setCredentialPassword(hashedPassword);
        credential.setCredentialKey(encodedSalt);
        this.credentialMapper.createCredential(credential);
    }

    public void updateCredential(CredentialForm credentialForm) {
        Credential credential = new Credential();
        credential.setUserId(Integer.valueOf(credentialForm.getUserId()));
        credential.setCredentialUrl(credentialForm.getCredentialUrl());
        credential.setCredentialUserName(credentialForm.getCredentialUserName());
        credential.setCredentialPassword(credentialForm.getCredentialPassword());
        this.credentialMapper.updateCredential(credential);
    }
}
