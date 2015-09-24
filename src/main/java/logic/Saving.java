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
    public static void save() throws IOException
    {
        if(Strings.MainFileName != null)
        {
            String saveFileName = removeBannedChars(Strings.MainFileName);
            System.out.println("Saving: " + saveFileName);

            FileWriter mFileWriter = new FileWriter(new File(Strings.pathToSaves, saveFileName));
            mFileWriter.write(mTextArea.getText());
            mFileWriter.close();
        }
    }
}