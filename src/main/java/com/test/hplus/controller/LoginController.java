package com.test.hplus.controller;

import com.test.hplus.beans.Login;
import com.test.hplus.beans.User;
import com.test.hplus.exceptions.ApplicationException;
import com.test.hplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@ModelAttribute Login login)
    {
        System.out.println("In Login Controller");
        User user = userRepository.getUserByName(login.getUsername());
        if (user==null)
        {
            throw new ApplicationException("User Not Found");
        }
//        return "search";
        return "forward:/userProfile";
    }
//
//    @ExceptionHandler(ApplicationException.class)
//    public String handleException()
//    {
//        System.out.println("In exception handler of Login Controller");
//        return "error";
//    }
}
