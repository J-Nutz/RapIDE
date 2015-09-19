package logic;

/*
 * Created by Jonah on 7/23/2015.
 */

import frames.MainFrame;
import panes.FileCreatorPane;
import panes.FileLoaderPane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static logic.RemoveChars.removeBannedChars;

public class Saving extends MainFrame
{
    public static File mFile;
    public static String adDir = System.getenv("APPDATA");
    public static File fileDirectory = new File(adDir + "\\RapIDE\\saves\\");

    public static void saveFile() throws IOException
    {
        if(FileCreatorPane.FileName != null)
        {
            save(FileCreatorPane.FileName);
        }
        else
        {
            save(FileLoaderPane.selectedSave);
        }
    }

    public static void save(String fileToSave) throws IOException
    {
        String saveFile = removeBannedChars(fileToSave);
        System.out.println("Saving: " + saveFile);

        FileWriter mFileWriter = new FileWriter(mFile = new File(fileDirectory, saveFile));
        mFileWriter.write(mTextArea.getText());
        mFileWriter.close();
    }
}