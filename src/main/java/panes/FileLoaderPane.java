package panes;

/*
 * Created by Jonah on 8/28/2015.
 */

import javax.swing.*;
import java.io.File;

import static logic.ReadingSaveFile.readingSavedFile;

public class FileLoaderPane extends JOptionPane
{
    String adDir = System.getenv("APPDATA");
    String pathToSaves = adDir + "\\RapIDE\\saves";

    File savesFolder = new File(pathToSaves);
    File[] listOfFiles = savesFolder.listFiles();
    Object[] result = new Object[listOfFiles.length];
    int i = 0;

    public FileLoaderPane()
    {
        if(listOfFiles != null)
        {
            for(File file : listOfFiles)
            {
                if(file.isFile())
                {
                    result[i] = file.getName();
                    i++;
                }
                else if(file.isDirectory())
                {
                    System.out.println("Fucked M8");
                }
            }
        }

        Object selectedValue = JOptionPane.showInputDialog(null,
            "Choose File To Load", "File Loader",
            JOptionPane.PLAIN_MESSAGE, null,
            result, result[0]);

        readingSavedFile(selectedValue.toString());
    }
}