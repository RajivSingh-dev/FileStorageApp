package com.udacity.jwdnd.course1.cloudstorage.services;

import com.google.common.io.ByteStreams;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Service
public class    FileService {

    private Logger logger = LoggerFactory.getLogger(FileService.class);

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<File> getFilesByUserId(Integer userId) {
        return fileMapper.getAllUserFiles(userId);
    }

    public boolean isFileNameAvailable(String fileName, Integer userId) {
        return fileMapper.getFileByName(fileName, userId) == null;
    }

    public int uploadFile(Integer userId, MultipartFile fileUpload) {
        InputStream inputStream;
        byte[] data = new byte[0];
        try {
            inputStream = fileUpload.getInputStream();
            data = ByteStreams.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("byte[] data length: " + data.length);

        if (data.length > 0) {
            File file = new File(null, fileUpload.getOriginalFilename(), fileUpload.getContentType(), String.valueOf(fileUpload.getSize()), userId, data);
            return this.fileMapper.uploadFile(file);
        } else {
            return 0;
        }
    }

    public File getFileByFileId(Integer fileId, Integer userId) {
        return fileMapper.getFile(fileId, userId);
    }

    public int deleteFile(Integer fileId, Integer userId) {
        return fileMapper.deleteFileById(fileId, userId);
    }
}
