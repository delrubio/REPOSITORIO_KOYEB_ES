package com.example.demo.Objects;
import com.example.demo.Enums.InstrumentsEnum;
import lombok.Data;

@Data
public class MusicalPiece {
    private String title;
    private String year;
    private String instrumentation;
    public String composerPiece;
    

    public MusicalPiece() { }

    public MusicalPiece(String [] list) {
        this.title = list[0];
        this.year = list[1];
        setInstrument(list[2]);
        this.composerPiece = list[3];
    }

    /**
     * Method to check de input from the user, and choose the available instruments in the ENUM
     * @param inst
     */
    public void setInstrument(String inst){
        if(inst.equals(InstrumentsEnum.PIANO.toString())){
            instrumentation = InstrumentsEnum.PIANO.toString();
        }else if(inst.equals(InstrumentsEnum.SOLO.toString())){
            instrumentation = InstrumentsEnum.SOLO.toString();
        }else if(inst.equals(InstrumentsEnum.CHAMBER.toString())){
            instrumentation = InstrumentsEnum.CHAMBER.toString();
        }else if(inst.equals(InstrumentsEnum.ORCHESTRA.toString())){
            instrumentation = InstrumentsEnum.ORCHESTRA.toString();
        }else if(inst.equals(InstrumentsEnum.VOCAL.toString())){
            instrumentation = InstrumentsEnum.VOCAL.toString();
        }else if(inst.equals(InstrumentsEnum.STAGE.toString())){
            instrumentation = InstrumentsEnum.STAGE.toString();
        }else{
            instrumentation = "OTHER";
        }
    }
    
    @Override
    public String toString(){
        return title  + ";" + year + ";" + instrumentation + ";" + composerPiece;
    }
}