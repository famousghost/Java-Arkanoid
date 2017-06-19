/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoidgame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcin
 */
public class SaveToFile {
    
    private String pathToFile = "stats.txt";
    File file;
    
    public void Save(String[] score)
    {
        file = new File(pathToFile);
            try {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                for(int i=0;i<3;i++)
                {
                    bufferedWriter.write(score[i]);
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(SaveToFile.class.getName()).log(Level.FINEST, null, ex);
            }
        
    } 
}
