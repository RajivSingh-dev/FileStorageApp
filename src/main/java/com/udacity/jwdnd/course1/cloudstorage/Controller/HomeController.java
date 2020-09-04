package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private UserService userService;
    private FileService fileService;
private NoteService noteService;
private CredentialService credentialService;

    public HomeController(UserService userService, FileService fileService,NoteService noteService,CredentialService credentialService) {
        this.userService = userService;
        this.fileService = fileService;
this.noteService=noteService;
this.credentialService=credentialService;
    }

    @GetMapping
    public String getHomeView(Authentication authentication, Model model) {
        Integer userId = userService.getUser(authentication.getName()).getUserId();

        model.addAttribute("files", this.fileService.getFilesByUserId(userId));
        model.addAttribute("notes",noteService.getNotesByUserId(userId));
        model.addAttribute("credentials",credentialService.getCredentialsByUserId(userId));
        return "home";
    }
}
