package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SumaController{

    @Autowired
    private SumaService sumaService;

    @GetMapping("/suma/{nX}/{nY}")
    public String showSuma(@PathVariable Integer nX, @PathVariable Integer nY, Model model){
        Integer result = sumaService.suma(nX, nY);
        model.addAttribute("resultado", result);
        return "resultSumaView";
    }
}