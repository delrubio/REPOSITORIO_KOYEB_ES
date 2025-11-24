package com.example.demo.Services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.example.demo.CSV.CSVUtil;
import com.example.demo.Objects.Composer;

@Service
public class ComposerServices {

    String csvPath = "demo\\src\\main\\resources\\data\\csv\\composerData.csv";

    /**
     * Crear compositores a través de un objeto y la ruta del archivo CSV
     * Cargamos los compositores existentes en el CSV en un array de Strings
     * Si el compositor no es null, lo añadimos, y lo ponemos null para que no lo
     * vuelva a crear
     * Añadimos el resto de compositores de nuevo, ya que sobreescribe.
     * 
     * @param composer
     * @param csvPath
     */
    public void create(Composer composer) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(csvPath, true))) { // true = append
            printWriter.println(composer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Borrar compositor a traves del objeto y la ruta del archivo.
     * Pedimos a la función que encuentre al compositor para que omita la linea, y
     * creamos todos los compositores
     * que hay en el CSV menos el indicado
     * 
     * @param composer
     * @param csvPath
     */
    public void delete(Composer composer) {
        List<Composer> dataListComp = read();
        dataListComp.removeIf(p -> p.equals(composer));
        try (PrintWriter printWriter = new PrintWriter(new File(csvPath))) {
            for (Composer c : dataListComp) {
                printWriter.println(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ver todos los compositores almacenados en el CSV como Lista
     * 
     * @param csvPath
     */
    public List<Composer> read() {
        List<Composer> dataListComp = new ArrayList<>();

        for (String[] comp : CSVUtil.readCSV(csvPath)) {
            dataListComp.add(new Composer(comp));
        }

        return dataListComp;

    }

    /**
     * Actualizar el compositor según el nombre el antiguo compositor, y añadiendo
     * el nuevo como objeto.
     * Comprobamos que en los compositores, el nombre coincida con el oldComp
     * Lo borramos y añadimos el nuevo como objeto.
     * 
     * @param oldComp
     * @param composer
     * @param csvPath
     */
    public void update(String oldComp, Composer composer) {
        for (String[] comp : CSVUtil.readCSV(csvPath)) {
            if (comp[0].toLowerCase().matches(oldComp.toLowerCase())) {
                delete(new Composer(comp));
                create(composer);
            }
        }
    }

    /**
     * Encuentra líneas en el CSV según el objeto que queramos encontrar.
     * 
     * @param composer
     * @param csvPath
     * @return
     */
    public String findLine(Composer composer) {
        String obj = "";
        try (Scanner fileScanner = new Scanner(new File(csvPath))) {
            while (fileScanner.hasNextLine()) {
                if (fileScanner.nextLine().matches(composer.toString())) {
                    obj = fileScanner.nextLine();
                    continue;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }
}