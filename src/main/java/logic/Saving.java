package logic;

/*
 * Created by Jonah on 7/23/2015.
 */

import frames.MainFrame;
import local.Strings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static logic.RemoveChars.removeBannedChars;

public class Saving extends MainFrame
{
    public static File mFile;

    /*public static void saveFile() throws IOException
    {
        if(FileCreatorPane.FileName != null)
        {
            save(FileCreatorPane.FileName);
        }
        else if(FileRenamerPane.newName != null)
        {
            save(FileRenamerPane.newName);
        }
        else
        {
            save(FileLoaderPane.selectedSave);
        }
    }*/

    /*public static void save(String fileToSave) throws IOException
    {
        String saveFile = removeBannedChars(fileToSave);
        System.out.println("Saving: " + saveFile);

        FileWriter mFileWriter = new FileWriter(mFile = new File(Strings.pathToSaves, saveFile));
        mFileWriter.write(mTextArea.getText());
        mFileWriter.close();
    }*/

    public static void saving() throws IOException
    {
        String saveFile = removeBannedChars(Strings.MainFileName);
        System.out.println("Saving: " + saveFile);

        FileWriter mFileWriter = new FileWriter(mFile = new File(Strings.pathToSaves, saveFile));
        mFileWriter.write(mTextArea.getText());
        mFileWriter.close();
    }
}