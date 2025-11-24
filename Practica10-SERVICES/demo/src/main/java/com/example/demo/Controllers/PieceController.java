package com.example.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Manager.Manager;
import com.example.demo.Objects.MusicalPiece;

@Controller
public class PieceController {

    @GetMapping("/pieces")
    public String musicalPiceView(Model model) {
        Manager instance = Manager.getInstance();
        model.addAttribute("lista", instance.getdataListPiece());
        return "musicalPieceView";
    }

    @GetMapping("/addPiece")
    public String addPieceForm(Model model) {
        model.addAttribute("newPiece", new MusicalPiece()); // le paso a "addPiece" un nuevo objeto Musical Piece
                                                            // llamado newPiece
        model.addAttribute("lista", Manager.getInstance().getdataListComp());
        return "addPiece";
    }

    @PostMapping("/addPiece/submit")
    public String addPieceFormPost(@ModelAttribute MusicalPiece piece) { // cojo el objeto que viene en el submit
                                                                         // llamado piece
        Manager.getInstance().addPiece(piece); // con el Manager creo una nueva pieza con el submit del formulario
        return "index";
    }

    @GetMapping("/editPiece")
    public String getMethodName(Model model) {
        model.addAttribute("lista", Manager.getInstance().getdataListPiece());
        return "selectPiece";
    }

    @PostMapping("/editPiece/submit")
    public String editPieceSelected(@RequestParam String title, Model model) {
        MusicalPiece piece = Manager.getInstance().getdataListPiece().stream().filter(p -> p.getTitle().equals(title))
                .findFirst().orElse(null);
        model.addAttribute("pieceSelected", piece);
        model.addAttribute("lista", Manager.getInstance().getdataListComp());
        model.addAttribute("mode", "/edit/" + title);
        return "editMusicalPiece";
    }

    @PostMapping("/piece/submit/edit/{oldpiece}")
    public String submitEditComposer(@PathVariable String oldpiece, Model model, @ModelAttribute MusicalPiece pieceSelected) {
        MusicalPiece old = Manager.getInstance().getdataListPiece().stream().filter(p -> p.getTitle().equals(oldpiece)).findFirst().orElse(null);
        if (old != null) {
            Manager.getInstance().delPiece(old);
            Manager.getInstance().addPiece(pieceSelected);
        }
        return "index";
    }

}
