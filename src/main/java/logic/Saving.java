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

    public static void saveFile() throws IOException
    {
        String adDir = System.getenv("APPDATA");

        File fileDirectory = new File(adDir + "\\RapIDE\\saves\\");

        if(FileCreatorFrame.FileName == null)
        {
            System.out.println("Saving: " + FileLoaderFrame.selectedSave);

            FileWriter mFileWriter = new FileWriter(mFile = new File(fileDirectory, FileLoaderFrame.selectedSave));
            System.out.println(mFile);
            mFileWriter.write(mTextArea.getText());
            mFileWriter.close();
        }
        else
        {
            System.out.println("Saving: " + FileCreatorFrame.FileName);

            FileWriter mFileWriter = new FileWriter(new File(fileDirectory, FileCreatorFrame.FileName));
            mFileWriter.write(mTextArea.getText());
            mFileWriter.close();
        }
    }
}