package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credentials")
public class CredentialController {
    private CredentialService credentialService;
private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }
    @PostMapping
    public String createOrUpdateCredential(Authentication authentication, @ModelAttribute Credential credential, Model model)
    {
        Integer userId = userService.getUser(authentication.getName()).getUserId();
if (credential.getCredentialId()!=null)
{
credentialService.updateCredential(credential.getCredentialId(),credential.getUrl(),credential.getUsername(),credential.getPassword(),userId);
}
else
{
credentialService.createCredential(new Credential(null,credential.getUrl(),credential.getUsername(),null,credential.getPassword(),userId));
}
        model.addAttribute("credentials", this.credentialService.getCredentialsByUserId(userId));
        model.addAttribute("resultSuccess", true);
        return "result";    }
    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId, Authentication authentication, Model model) {
        Integer userId = userService.getUser(authentication.getName()).getUserId();

        int result = credentialService.deleteCredential(credentialId, userId);
        if (result > 0) {
            model.addAttribute("resultSuccess", true);
        } else {
            model.addAttribute("resultError", "Credential ID does not exist.");
        }

        return "result";
    }
}
