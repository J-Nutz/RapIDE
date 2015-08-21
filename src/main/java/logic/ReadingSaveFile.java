package logic;

/*
 * Created by Jonah on 7/23/2015.
 */

import frames.MainFrame;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class ReadingSaveFile extends MainFrame
{

    public static String SelectedSaveFileCopy;

    public static void readingSavedFile(String SelectedSaveFile)
    {
        Reader mReader = null;

        SelectedSaveFileCopy = SelectedSaveFile;

        try
        {
            String adDir = System.getenv("APPDATA");
            mReader = new FileReader(new File(adDir + "\\RapIDE\\saves\\" + SelectedSaveFile));
            mTextArea.read(mReader, "What? Lol.");
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
        finally
        {
            try
            {
                assert mReader != null;
                mReader.close();
            }
            catch (Exception exp)
            {
                //Leave Blank
            }
        }
    }
}