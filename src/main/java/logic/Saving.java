package logic;

/*
 * Created by Jonah on 7/23/2015.
 */

import frames.FileCreatorFrame;
import frames.FileLoaderFrame;
import frames.MainFrame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saving extends MainFrame
{

    public static File mFile;
    public static String adDir = System.getenv("APPDATA");
    public static File fileDirectory = new File(adDir + "\\RapIDE\\saves\\");

    public static void saveFile() throws IOException
    {
        if(FileCreatorFrame.FileName == null)
        {
            save(FileLoaderFrame.selectedSave);
        }
        else
        {
            save(FileCreatorFrame.FileName);
        }
    }

    public static void save(String fileToSave) throws IOException
    {
        System.out.println("Saving: " + FileCreatorFrame.FileName);

        FileWriter mFileWriter = new FileWriter(mFile = new File(fileDirectory, fileToSave));
        mFileWriter.write(mTextArea.getText());
        mFileWriter.close();
    }

}