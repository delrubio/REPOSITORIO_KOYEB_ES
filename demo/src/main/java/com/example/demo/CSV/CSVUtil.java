package com.example.demo.CSV;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Read the CSV file who store the information, the composer either the musical piece
 * @param
 */
public class CSVUtil {
    public static List<String []> readCSV(String path){
        List<String []> list = null; //we need to initialize it to null because the Scanner may fail
        try(Scanner fileScanner = new Scanner(new File(path))){
            list = new ArrayList<>();
            while(fileScanner.hasNextLine())
                list.add(fileScanner.nextLine().split(";")); //the ";" is the separator of the csv
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }
}
