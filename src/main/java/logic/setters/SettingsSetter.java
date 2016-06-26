package logic.setters;

import frames.MainFrame;
import local.Strings;

import java.awt.*;
import java.io.File;
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
        setAppearanceSettings();
    }

    public static void setFontSettings()
    {
        try
        {
            File fontFile = new File(Strings.pathToProps + "fontProps.properties");
            if(fontFile.exists())
            {
                fontProps.load(new FileInputStream(fontFile));
                String savedFontType = fontProps.getProperty("Font Type", "Courier New");
                String savedFontColor = fontProps.getProperty("Font Color", "black");
                int savedFontSize = Integer.parseInt(fontProps.getProperty("Font Size", "16"));
                int savedFontStyle = Integer.parseInt(fontProps.getProperty("Font Style", "0"));

                final Field f = Color.class.getField(savedFontColor);

                Color newFontColor = (Color) f.get(null);
                //noinspection MagicConstant
                Font savedFont = new Font(savedFontType, savedFontStyle, savedFontSize);

                Component[] components = {MainFrame.mCreateFile, MainFrame.mSoundsLike, MainFrame.mFileDeleter,
                        MainFrame.mSave, MainFrame.mOpenFile, MainFrame.mRenameFile, MainFrame.mSettings,
                        MainFrame.sColors, MainFrame.sRhymingWords, MainFrame.sFont};

                for(Component aComponent : components)
                {
                    aComponent.setForeground(newFontColor);
                }
                MainFrame.rhymeListDisplay.setForeground(newFontColor);

                MainFrame.mTextArea.setForeground(newFontColor);
                MainFrame.mTextArea.setFont(savedFont);
            }
        }
        catch(IOException | IllegalAccessException | NoSuchFieldException e)
        {
            e.printStackTrace();
        }
    }

    public static void setAppearanceSettings()
    {
        try
        {
            File colorFile = new File(Strings.pathToProps + "appearanceProps.properties");

            if(colorFile.exists())
            {
                colorProps.load(new FileInputStream(colorFile));

                String savedBackgroundColor = colorProps.getProperty("Background Color", "gray");
                String savedBorderColor = colorProps.getProperty("Border Color", "gray");
                String savedRWColor = colorProps.getProperty("RW Color", "gray");
                String savedMenuColor = colorProps.getProperty("Menu Color", "white");

                final Field f = Color.class.getField(savedBackgroundColor);
                final Field f1 = Color.class.getField(savedBorderColor);
                final Field f2 = Color.class.getField(savedRWColor);
                final Field f3 = Color.class.getField(savedMenuColor);

                Color newBackgroundColor = (Color) f.get(null);
                Color newBorderColor = (Color) f1.get(null);
                Color newRWColor = (Color) f2.get(null);
                Color newMenuColor = (Color) f3.get(null);

                MainFrame.mTextArea.setBackground(newBackgroundColor);
                MainFrame.mPanel.setBackground(newBorderColor);
                MainFrame.rhymeListDisplay.setBackground(newRWColor);
                MainFrame.mMenuBar.setBackground(newMenuColor);
            }
        }
        catch(NoSuchFieldException | IllegalAccessException | IOException e)
        {
            e.printStackTrace();
        }
    }
}