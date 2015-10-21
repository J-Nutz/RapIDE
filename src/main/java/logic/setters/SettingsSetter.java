package logic.setters;

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

public class SettingsSetter
{
    public static Properties fontProps = new Properties();
    public static Properties colorProps = new Properties();

    public static void setSettings()
    {
        setFontSettings();
    }

    public static void setFontSettings()
    {
        try
        {
            fontProps.load(new FileInputStream(Strings.pathToProps + "fontProps.properties"));

            String savedFontType = fontProps.getProperty("Font Type", "Courier New");
            String savedFontColor = fontProps.getProperty("Font Color", "black");
            int savedFontSize = Integer.parseInt(fontProps.getProperty("Font Size", "16"));
            int savedFontStyle = Integer.parseInt(fontProps.getProperty("Font Style", "0"));

            try
            {
                final Field f = Color.class.getField(savedFontColor);

                Color savedColor = (Color) f.get(null);
                //noinspection MagicConstant
                Font savedFont = new Font(savedFontType, savedFontStyle, savedFontSize);

                MainFrame.mTextArea.setForeground(savedColor);
                MainFrame.mTextArea.setFont(savedFont);
            }
            catch(NoSuchFieldException | IllegalAccessException e)
            {
                e.printStackTrace();
            }

            try
            {
                colorProps.load(new FileInputStream(Strings.pathToProps + "appearanceProps.properties"));

                String savedBackgroundColor = colorProps.getProperty("Background Color", "gray");
                String savedBorderColor = colorProps.getProperty("Border Color", "gray");

                final Field f = Color.class.getField(savedBackgroundColor);
                final Field f1 = Color.class.getField(savedBorderColor);

                Color newBackgroundColor = (Color) f.get(null);
                Color newBorderColor = (Color) f1.get(null);

                MainFrame.mTextArea.setBackground(newBackgroundColor);
                MainFrame.mPanel.setBackground(newBorderColor);
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