package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.UserFileVO;

import java.util.List;
import java.util.Map;

public interface UserFileMapper {
    List<UserFileVO> getFileByUsername(String username);
    List<UserFileVO> getFileByUsernameAndFilename(Map<String, Object> parMap);

}
