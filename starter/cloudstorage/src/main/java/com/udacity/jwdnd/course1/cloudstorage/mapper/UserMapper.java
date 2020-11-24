package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from USERS where username = #{userName}")
    User getUser(String userName);

    @Insert("insert into USERS (username, salt, password, firstname, lastname) values(#{userName}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int createUser(User user);

    @Delete("delete from USERS where userid = #{userId}")
    void deleteUser(Integer userId);
}
