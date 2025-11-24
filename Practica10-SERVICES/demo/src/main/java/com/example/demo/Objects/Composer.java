package com.example.demo.Objects;

import java.util.ArrayList;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Composer {
    private String nameComposer;
    private String biography;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String dob;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String dod;
    private String nacionality;
    private String bPlace;
    private String dPlace;

    public ArrayList<MusicalPiece> picesList = new ArrayList<>();

    public Composer() {
    }

    public Composer(String[] comp) {
        this.nameComposer = comp[0];
        this.biography = comp[1];
        this.dob = comp[2];
        this.dod = comp[3];
        this.nacionality = comp[4];
        this.bPlace = comp[5];
        this.dPlace = comp[6];
    }

    @Override
    public String toString() {
        return nameComposer + ";" + biography + ";" + dob + ";" + ((dod.isEmpty()) ? "*Undetermined*" : dod) + ";"
                + nacionality + ";" + bPlace + ";" + ((dPlace.isEmpty()) ? "*Undetermined*" : dPlace);
    }

}
