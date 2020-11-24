package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface NoteMapper {

    @Select("select * from NOTES where noteid = #{noteId}")
    Note getNote(Integer noteId);

    @Select("select * from NOTES where userid = #{userId}")
    ArrayList<Note> getUserNotes(Integer userId);

    @Insert("insert into NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int createNote(Note note);

    @Delete("delete from NOTES where noteid = #{noteId}")
    int deleteNote(Integer noteId);

    @Update("update NOTES set notetitle=#{noteTitle}, notedescription=#{noteDescription} where noteid=#{noteId}")
    int updateNote(Note note);
}
