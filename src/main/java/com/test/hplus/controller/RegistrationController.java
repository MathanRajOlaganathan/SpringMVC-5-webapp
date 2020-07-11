package com.test.hplus.controller;

import com.test.hplus.beans.User;
import com.test.hplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
     binder.registerCustomEditor(Date.class, "dateOfBirth",
             new CustomDateEditor(new SimpleDateFormat("yyy-MM-dd"), true));
    }



    @PostMapping("/registerUser")
    public String registerUser(@Valid @ModelAttribute("newUser") User user, BindingResult result, Model model)
    {
        System.out.println("In Registration Controller");
        if(result.hasErrors())
        {
            return "register";
        }
        System.out.println("DOB: "+user.getDateOfBirth());
        userRepository.save(user);
        model.addAttribute("dataSaved","User Registration Completed Successfully");
        return "login";
    }
}
