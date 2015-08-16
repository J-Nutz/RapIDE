package logic;

/*
 * Created by Jonah on 7/23/2015.
 */

import frames.FileLoaderFrame;
import frames.FileNameFrame;
import frames.MainFrame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saving extends MainFrame
{
    public static void saveFile() throws IOException
    {
        String adDir = System.getenv("APPDATA");

        File fileDirectory = new File(adDir + "\\RapIDE\\saves\\");

        if(FileNameFrame.FileName == null)
        {
            System.out.println("Saving: " + FileLoaderFrame.selectedSave);

            FileWriter mFileWriter = new FileWriter(new File(fileDirectory, FileLoaderFrame.selectedSave));
            mFileWriter.write(mTextArea.getText());
            mFileWriter.close();
        }
        else
        {
            System.out.println("Saving: " + FileNameFrame.FileName);

            FileWriter mFileWriter = new FileWriter(new File(fileDirectory, FileNameFrame.FileName));
            mFileWriter.write(mTextArea.getText());
            mFileWriter.close();
        }
    }
}