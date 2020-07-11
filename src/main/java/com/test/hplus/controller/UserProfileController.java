package com.test.hplus.controller;

import com.test.hplus.beans.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class UserProfileController {

    @PostMapping("/userProfile")
    public String getUserProfile(@SessionAttribute("login") Login login, Model model)
    {
        System.out.println("In User Profile Controller");
        System.out.println("Username From Session: "+login.getUsername());
        model.addAttribute("userName",login.getUsername());
        return "profile";
    }
}
