package logic;

import frames.MainFrame;
import local.Strings;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
 * Created by Jonah on 10/9/2015.
 */

public class SetSettings
{
    public static Properties fontProps = new Properties();
    public static InputStream fontInput = null;

    public static void setSettings()
    {
        setFontSettings();
    }

    public static void setFontSettings()
    {
        try
        {
            fontInput = new FileInputStream(Strings.pathToProps + "fontProps.properties");

            String savedFontType = fontProps.getProperty("Font Type");
            String savedFontColor = fontProps.getProperty("Font Color");
            String toParseInt = fontProps.getProperty("Font Size");
            int savedFontSize = Integer.parseInt(toParseInt);

            System.out.println(savedFontColor);

            Font savedFont = new Font(savedFontType, Font.PLAIN, savedFontSize);
            MainFrame.mTextArea.setFont(savedFont);
            //MainFrame.mTextArea.setForeground();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(fontInput != null)
            {
                try
                {
                    fontInput.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}