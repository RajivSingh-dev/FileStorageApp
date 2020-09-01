package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface    NoteMapper {

    //create
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{title}, #{description}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int createNote(Note note);

    //read
    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> getAllUserNotes(Integer userId);

    //update
    @Update("UPDATE NOTES SET notetitle=#{title}, notedescription=#{description} WHERE noteid = #{noteId} AND userid = #{userId}")
    int updateNote(String title, String description, Integer noteId, Integer userId);

    //delete
    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId} AND userid = #{userId}")
    int deleteNote(Integer noteId, Integer userId);
}
