/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoidgame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcin
 */
public class ReadFromFile {
    
    private String pathToFile = "stats.txt";
    int[] scoresIntArray = new int[3];
    private int licznik = 0;
    File file;
    
    
    public void Read()
    {
        licznik =0;
        file = new File(pathToFile);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            try {
                if(file.exists())
                {
                    while((line = bufferedReader.readLine())!= null)
                    {
                        scoresIntArray[licznik] = Integer.parseInt(line);
                        licznik++;
                    }
                }
                fileReader.close();
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadFromFile.class.getName()).log(Level.WARNING, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadFromFile.class.getName()).log(Level.WARNING, null, ex);
        }
    }
    
}
