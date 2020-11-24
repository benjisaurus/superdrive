package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {

    @Select("select * from FILES where fileid = #{fileId}")
    File getFile(Integer fileId);

    @Insert("insert into FILES () VALUES()")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int createFile(File file);

    @Delete("delete from FILES where fileid = #{fileId}")
    void deleteFile(Integer fileId);
}
