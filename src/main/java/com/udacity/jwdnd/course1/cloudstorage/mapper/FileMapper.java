package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES(docname,contenttype,docsize,userid,filedata) VALUES (#{docname},#{contenttype},#{docsize},#{userid},#{filedata})")
    @Options(useGeneratedKeys = true,keyProperty = "fileid")
    int insert(File file);

    @Select("SELECT * FROM FILES WHERE fileid=#{fileid}")
    File getFileById(Integer fileid);

@Select("SELECT * FROM FILES")
    List<File> getAllFiles();

@Delete("DELETE FROM FILES WHERE fileid=#{fileid}")
    void delete(Integer fileid);

}
