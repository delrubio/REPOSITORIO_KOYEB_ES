package com.example.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppComtroller {

    @GetMapping("/")
    public String indexView() {
        return "index";
    }

}