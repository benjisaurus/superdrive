package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface CredentialMapper {

    @Select("select * from CREDENTIALS where userid = #{userId}")
    ArrayList<Credential> getCredentials(Integer userId);

    @Select("select * from CREDENTIALS where credentialid = #{credentialId}")
    Credential getCredential(Integer credentialId);

    @Insert("insert into CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int createCredential(Credential credential);

    @Delete("delete from CREDENTIALS where credentialid = #{credentialId}")
    int deleteCredential(Integer credentialId);

    @Update("update CREDENTIALS set url=#{url}, username=#{userName}, password=#{password} where credentialid=#{credentialId}")
    int updateCredential(Credential credential);
}
