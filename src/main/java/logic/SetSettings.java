package logic;

import frames.MainFrame;
import local.Strings;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/*
 * Created by Jonah on 10/9/2015.
 */

public class SetSettings
{
    public static Properties fontProps = new Properties();

    public static void setSettings()
    {
        setFontSettings();
    }

    public static void setFontSettings()
    {
        try
        {
            fontProps.load(new FileInputStream(Strings.pathToProps + "fontProps.properties"));

            String savedFontType = fontProps.getProperty("Font Type");
            String savedFontColor = fontProps.getProperty("Font Color");
            int savedFontSize = Integer.parseInt(fontProps.getProperty("Font Size"));

            try
            {
                final Field f = Color.class.getField(savedFontColor);

                Color savedColor = (Color) f.get(null);
                Font savedFont = new Font(savedFontType, Font.PLAIN, savedFontSize);

                MainFrame.mTextArea.setForeground(savedColor);
                MainFrame.mTextArea.setFont(savedFont);
            }
            catch(NoSuchFieldException | IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}