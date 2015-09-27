package logic;

/*
 * Created by Jonah on 7/23/2015.
 */

import frames.MainFrame;
import local.Strings;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class ReadingSaveFile extends MainFrame
{
    public static void readingSavedFile(String SelectedSaveFile)
    {
        Reader mReader;

        try
        {
            mReader = new FileReader(new File(Strings.pathToSaves + SelectedSaveFile));
            mTextArea.read(mReader, "What? Lol.");
            mReader.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}