package com.example.demo.Manager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.CSV.CSVUtil;
import com.example.demo.Enums.InstrumentsEnum;
import com.example.demo.Objects.Composer;
import com.example.demo.Objects.MusicalPiece;

public class Manager {
    private static Manager instance = null;

    private List<MusicalPiece> dataListPiece;
    private List<Composer> dataListComp;

    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    private Manager() {
        setDatalistComposer();
        setDatalistPiece();
    }
    /**
     * Inicializar la lista de compositores, carga desde el CSV todo los compositores que hayan guardados mediando el CSV utils
     */
    public void setDatalistComposer() {
        if (dataListPiece == null)
            dataListPiece = new ArrayList<>(); // si la lista está vacía la inicializo.
        List<String[]> listaString = CSVUtil.readCSV("demo\\src\\main\\resources\\data\\csv\\composerData.csv"); // le paso al contenido de la lista desde el CSV.
        dataListComp = new ArrayList<Composer>(); // inizilaico la lista de Composers de esta clase para utlizarla en el Controller y enviar la lista de objetos a los HTML.
        for (String[] comp : listaString) { // el for para llenar la llista con los compositores que hayan en la lista del CSV.
            dataListComp.add(new Composer(comp));
        }
    }

    /**
     * Devuelve la lista de compositores que está creada con setDatalistComposer
     * @return Lista de compositores
     */
    public List<Composer> getdataListComp() {
        if (dataListComp == null)
            setDatalistComposer();
        return this.dataListComp;
    }

    /** COgemos el CSV y añadimos un compositor nuevo.
     * @param El compositor que vamos a añadir a la lista
     */
    public void addComposer(Composer comp) { // clase para añadir compositores que compositores que vengan del nuevo
                                             // formulario de añadir.
        String path = "demo\\src\\main\\resources\\data\\csv\\composerData.csv";
        if (dataListComp == null)
            setDatalistComposer(); // si la lista de compositores de la clase está vacía, llamo a la clase "set"
                                   // para que la rellene.

        dataListComp.add(comp); // añado el nuevo compositor del formulario a la lista.
        try (PrintWriter printWriter = new PrintWriter(new File(path))) { // para escribir en el CSV
            for (Composer c : dataListComp) { // cojo los compositores de la lista y con toString que en la clase
                                              // Composer hemos configurado escribe el String del compositor en el CSV
                printWriter.println(c); // y de esta manera, tenemos los compositores que ya estaban en el CSV + el
                                        // nuevo que hemos añaido antes sobreescribiendo el CSV.
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Borrar compositores, primero de la lista y luego sobreescribimos el CSV
     * @param comp COmpositor que vamos a borrar
     */
    public void delComposer(Composer comp) {
        String path = "demo\\src\\main\\resources\\data\\csv\\composerData.csv";

        dataListComp.removeIf(p -> p.equals(comp));
        try (PrintWriter printWriter = new PrintWriter(new File(path))) {
            for (Composer c : dataListComp) {
                printWriter.println(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDatalistPiece() {
        if (dataListPiece == null)
            dataListPiece = new ArrayList<>();
        List<String[]> listaString = CSVUtil.readCSV("demo\\src\\main\\resources\\data\\csv\\musicalPieceData.csv");
        dataListPiece = new ArrayList<MusicalPiece>();

        for (String[] piece : listaString) {
            dataListPiece.add(new MusicalPiece(piece));
        }
    }

    public List<MusicalPiece> getdataListPiece() {
        return this.dataListPiece;
    }

    public void addPiece(MusicalPiece piece) {
        String path = "demo\\src\\main\\resources\\data\\csv\\musicalPieceData.csv";
        if (dataListPiece == null)
            setDatalistPiece();

        dataListPiece.add(setInstrument(piece));
        try (PrintWriter printWriter = new PrintWriter(new File(path))) {
            for (MusicalPiece p : dataListPiece) {
                printWriter.println(p);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void delPiece(MusicalPiece piece) {
        String path = "demo\\src\\main\\resources\\data\\csv\\musicalPieceData.csv";

        dataListPiece.removeIf(p -> p.equals(piece));
        try (PrintWriter printWriter = new PrintWriter(new File(path))) {
            for (MusicalPiece p : dataListPiece) {
                printWriter.println(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Este metodo devuelve un objeto de tipo Piece que con el Enum comprueba que el
    // instrumento es correcto
    public MusicalPiece setInstrument(MusicalPiece inst) {

        if (inst.getInstrumentation().equals(InstrumentsEnum.PIANO.toString())) {
            inst.setInstrumentation(InstrumentsEnum.PIANO.toString());
        } else if (inst.getInstrumentation().equals(InstrumentsEnum.SOLO.toString())) {
            inst.setInstrumentation(InstrumentsEnum.SOLO.toString());
        } else if (inst.getInstrumentation().equals(InstrumentsEnum.CHAMBER.toString())) {
            inst.setInstrumentation(InstrumentsEnum.CHAMBER.toString());
        } else if (inst.getInstrumentation().equals(InstrumentsEnum.ORCHESTRA.toString())) {
            inst.setInstrumentation(InstrumentsEnum.ORCHESTRA.toString());
        } else if (inst.getInstrumentation().equals(InstrumentsEnum.VOCAL.toString())) {
            inst.setInstrumentation(InstrumentsEnum.VOCAL.toString());
        } else if (inst.getInstrumentation().equals(InstrumentsEnum.STAGE.toString())) {
            inst.setInstrumentation(InstrumentsEnum.STAGE.toString());
        } else {
            inst.setInstrumentation(InstrumentsEnum.OTHER.toString());
        }

        return inst;
    }
}
