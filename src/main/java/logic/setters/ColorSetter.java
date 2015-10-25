package logic.setters;

/*
 * Created by Jonah on 10/21/2015.
 */

import frames.MainFrame;

import java.awt.*;
import java.util.Properties;

public class ColorSetter
{
    public void getColor(int index, Component component, String key, Properties properties, int backOrFore)
    {
        switch(index)
        {
            case -1:
                System.out.println("No Items In Font Color List");
                break;
            case 0:
                setColor(Color.black, "black", key, properties, component, backOrFore);
                break;
            case 1:
                setColor(Color.blue, "blue", key, properties, component, backOrFore);
                break;
            case 2:
                setColor(Color.cyan, "cyan", key, properties, component, backOrFore);
                break;
            case 3:
                setColor(Color.darkGray, "darkGray", key, properties, component, backOrFore);
                break;
            case 4:
                setColor(Color.gray, "gray", key, properties, component, backOrFore);
                break;
            case 5:
                setColor(Color.green, "green", key, properties, component, backOrFore);
                break;
            case 6:
                setColor(Color.lightGray, "lightGray", key, properties, component, backOrFore);
                break;
            case 7:
                setColor(Color.magenta, "magenta", key, properties, component, backOrFore);
                break;
            case 8:
                setColor(Color.orange, "orange", key, properties, component, backOrFore);
                break;
            case 9:
                setColor(Color.pink, "pink", key, properties, component, backOrFore);
                break;
            case 10:
                setColor(Color.red, "red", key, properties, component, backOrFore);
                break;
            case 11:
                setColor(Color.white, "white", key, properties, component, backOrFore);
                break;
            case 12:
                setColor(Color.yellow, "yellow", key, properties, component, backOrFore);
                break;
            default:
                System.out.println("No Value");
                break;
        }
    }

    public void setColor(Color color, String colorString, String key, Properties properties, Component component, int backOrFore)
    {
        if(backOrFore == 1)
        {
            component.setBackground(color);
        }
        else if(backOrFore == 2)
        {
            component.setForeground(color);
            MainFrame.mSettings.setForeground(color);

            Component[] components = {MainFrame.mCreateFile, MainFrame.mSoundsLike, MainFrame.mFileDeleter,
                    MainFrame.mSave, MainFrame.mOpenFile, MainFrame.mRenameFile, MainFrame.mSettings,
                    MainFrame.sColors, MainFrame.sRhymingWords, MainFrame.sFont};

            for(Component aComponent : components)
            {
                aComponent.setForeground(color);
            }
            MainFrame.rhymeListDisplay.setForeground(color);
        }
        properties.put(key, colorString);
    }
}