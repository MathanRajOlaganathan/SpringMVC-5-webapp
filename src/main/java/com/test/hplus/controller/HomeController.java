package com.test.hplus.controller;

import com.test.hplus.beans.Login;
import com.test.hplus.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String goHome()

    {
        System.out.println("In Home Controller");
        return "index";
    }

    @GetMapping("/goToSearch")
    public String goSearch()
    {
        System.out.println("Go To Search");
        return "search";
    }


    @GetMapping("/goToLogin")
    public String goToLogin()
    {
        System.out.println("Go To Login Page");
        return "login";
    }

    @GetMapping("/goToRegistration")
    public String goToRegistration()
    {
        System.out.println("Go To Registration");
        return "register";
    }

//    @ModelAttribute("newUser")
//    public User getDefaultUser()
//    {
//        return new User();
//    }
//
//    @ModelAttribute("genderItems")
//    public List<String> getGenderItems()
//    {
//    return Arrays.asList(new String[]{"Male","Female","Other"});
//    }
//
//    @ModelAttribute("login")
//    public Login getDefaultLogin()
//    {
//       return  new Login();
//    }
}
