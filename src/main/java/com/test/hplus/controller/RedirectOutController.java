package com.test.hplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectOutController {

    @GetMapping("/redirectToLinkedIn")
    public String redirectOut()
    {
        System.out.println("In Redirect Controller");
        return"redirect:http://www.linkedin.com";
    }
}
