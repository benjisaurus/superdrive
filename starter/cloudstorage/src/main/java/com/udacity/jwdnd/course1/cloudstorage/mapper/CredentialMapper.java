package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface CredentialMapper {

    @Select("select * from CREDENTIALS where userid = #{userId}")
    ArrayList<Credential> getCredentials(Integer userId);

    @Insert("insert into CREDENTIALS (url, username, key, password, userid) VALUES(#{credentialUrl}, #{credentialUserName}, #{credentialKey}, #{credentialPassword}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int createCredential(Credential credential);

    @Delete("delete from CREDENTIALS where credentialid = #{credentialId}")
    int deleteCredential(Integer credentialId);

    @Update("update CREDENTIALS set url=#{credentialUrl}, username=#{credentialUserName}, password=#{credentialPassword} where credentialid=#{credentialId}")
    int updateCredential(Credential credential);
}
