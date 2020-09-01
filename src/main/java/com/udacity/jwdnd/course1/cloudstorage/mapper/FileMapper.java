package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    //create (upload)
    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{name}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int uploadFile(File file);

    //read
    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> getAllUserFiles(Integer userId);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId} AND userid = #{userId}")
    File getFile(Integer fileId, Integer userId);

    @Select(("SELECT * FROM FILES WHERE filename=#{fileName} AND userid=#{userId}"))
    File getFileByName(String fileName, Integer userId);

    //delete
    @Delete("DELETE FROM FILES WHERE fileid = #{fileId} AND userid = #{userId}")
    int deleteFileById(Integer fileId, Integer userId);
}
