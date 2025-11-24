package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Objects.Composer;
import com.example.demo.Services.ComposerServices;

import jakarta.validation.Valid;

@Controller
public class ComposerController {

    @Autowired
    private ComposerServices csv;

    /**
     * Show a list of composers stored in CSV.
     */
    @GetMapping("/composer")
    public String composerView(Model model) {
        model.addAttribute("lista", csv.read());
        return "composerView";
    }

    /**
     * The post method, takes in the submit, the user input un the HTML form,
     * with @RequestParam, take the input
     * 
     * @param texto Name or Nacionality of Composer
     * @param model What we will pass to the HTML Thymeleaf Object to show on WEB
     * @return Web page.
     */
    @PostMapping("/composer/submit")
    public String composerViewSearch(@RequestParam String texto, Model model) {
        model.addAttribute("lista",
                csv.read().stream()
                        .filter(p -> p.getNameComposer().toLowerCase().contains(texto.toLowerCase())
                                || p.getNacionality().toLowerCase().contains(texto.toLowerCase()))
                        .toList());
        return "composerView";
    }

    /**
     * Take us the the AddComposer form
     * 
     * @param model Pass the HTML an object, who will be the new composer, the HTML
     *              will fill the attributs.
     * @return
     */
    @GetMapping("/addComposer")
    public String addComposerForm(Model model) {
        model.addAttribute("newComposer", new Composer());
        return "addComposer";
    }

    /**
     * This controller will contain the new composer object information. Then,
     * create the object in the CSV.
     * 
     * @param comp          The object from the previous Web Form
     * @param bindingResult To check if the @Valid format for exameple, for date, is
     *                      working or not.
     * @return
     */
    @PostMapping("/addComposer/submit")
    public String addComposerFormPost(@Valid @ModelAttribute Composer comp, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("ERROR");
        }
        csv.create(comp);
        ;
        return "index";
    }

    /**
     * To take us to a drop dow list of composers to edit
     * 
     * @param model The HTML, must have the list of available composers in the CSV
     *              to edit.
     * @return
     */
    @GetMapping("/editComposer")
    public String selectComposerForm(Model model) {
        model.addAttribute("lista", csv.read());
        return "selectComposer";
    }

    /**
     * This, take the composer to edit, then pass it as object to the new form with
     * the preloaded data to edit.
     * the mode "model" makes a custom uri, with new name from the edited composer
     * 
     * @param name  Name of composer to edit
     * @param model To pass the object to the HTML form, then, show the previous
     *              information, to edit it
     * @return
     */
    @PostMapping("/editComposer/submit")
    public String editForm(@RequestParam String name, Model model) { // el parametro que recibe el metodo, TIENE QUE SE
                                                                     // SER EL MISMO NOMBRE QUE EL NAME DEL SELECT de
                                                                     // selectComposer.
        Composer composer = csv.read().stream().filter(c -> c.getNameComposer().equals(name)).findFirst()
                .orElse(null);
        model.addAttribute("composerSelected", composer);
        model.addAttribute("mode", "/edit/" + name);
        return "editComposer";
    }

    /**
     * Takes the information of the new composer, and the name of the previous one,
     * The name in the PathVariable, and @ModelAttribute, from the object from HTML
     * Thymeleaf file.
     * 
     * @param oldcomp          Name of older composer, create and object and delete
     * @param composerSelected This is, the object from the thymeleaf file
     * @return
     */
    @PostMapping("/composer/submit/edit/{oldcomp}") // acordarse que PathVariable va con {} y RequestParam con ?=
    public String postMethodName(@PathVariable String oldcomp, @ModelAttribute Composer composerSelected) {
        Composer old = csv.read().stream().filter(c -> c.getNameComposer().equals(oldcomp)).findFirst()
                .orElse(null);
        if (old != null) {
            csv.update(oldcomp, composerSelected);
        }
        return "index";
    }

}
