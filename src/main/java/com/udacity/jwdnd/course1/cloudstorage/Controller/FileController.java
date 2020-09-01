package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/files")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    private final FileService fileService;
    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication authentication, @ModelAttribute File file, Model model) {
        Integer userId = this.userService.getUser(authentication.getName()).getUserId();

        String resultError = null;

        if (!fileService.isFileNameAvailable(fileUpload.getOriginalFilename(), userId)) {
            resultError = "This file name already exists.";
        }

        if (resultError == null) {
            int result = this.fileService.uploadFile(userId, fileUpload);
            logger.info("Result of file mapper's upload file method is: " + result);

            if (result == 0) {
                resultError = "There was an error uploading your file.";
            }
        }

        if (resultError == null) {
            model.addAttribute("resultSuccess", true);
            model.addAttribute("files", this.fileService.getFilesByUserId(userId));
        } else {
            model.addAttribute("resultError", resultError);
        }

        return "result";
    }

    //with help from https://www.devglan.com/spring-boot/spring-boot-file-upload-download
    @GetMapping("/view/{fileId}")
    public ResponseEntity<byte[]> viewAndDownloadFile(@PathVariable Integer fileId, Authentication authentication) {
        Integer userId = this.userService.getUser(authentication.getName()).getUserId();
        File file = this.fileService.getFileByFileId(fileId, userId);
        if (file != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(file.getFileData());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Authentication authentication, Model model) {
        Integer userId = this.userService.getUser(authentication.getName()).getUserId();
        int result = this.fileService.deleteFile(fileId, userId);
        if (result > 0) {
            model.addAttribute("resultSuccess", true);
        } else {
            model.addAttribute("resultError", "File ID does not exist.");
        }
        return "result";
    }
}