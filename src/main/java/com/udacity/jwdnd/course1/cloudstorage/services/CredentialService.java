package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final  EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;

    }
public List<Credential> getCredentialsByUserId(Integer userId)
{
    return credentialMapper.getAllUserCredentials(userId);
}

public Credential getCredentialByCredentialId(Integer credentialId,Integer userId)
{
return credentialMapper.getCredential(credentialId,userId);
}

public String getDecryptedPassword(Credential credential)
{
return encryptionService.encryptValue(credential.getPassword(),credential.getKey());
}

public int createCredential(Credential credential)
{
SecureRandom random = new SecureRandom();
byte[] key=new byte[16];
random.nextBytes(key);
String encodedKey= Base64.getEncoder().encodeToString(key);
String encryptedPassword=encryptionService.encryptValue(credential.getPassword(),encodedKey);
return credentialMapper.createCredential(new Credential(null,credential.getUrl(),credential.getUsername(),encodedKey,encryptedPassword,credential.getUserId()));
}

public int updateCredential(Integer credentialId,String url,String username,String password,Integer userId)
{
    SecureRandom random = new SecureRandom();
    byte[] key = new byte[16];
    random.nextBytes(key);
    String encodedKey = Base64.getEncoder().encodeToString(key);
    String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
return credentialMapper.updateCredential(credentialId,url,username,encodedKey,encryptedPassword,userId);
}
public int deleteCredential(Integer credentialId,Integer userId){
        return credentialMapper.deleteCredential(credentialId,userId);
}
}
