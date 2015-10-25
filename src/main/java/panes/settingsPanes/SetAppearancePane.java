package panes.settingsPanes;

import frames.MainFrame;
import local.Strings;
import logic.setters.ColorSetter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/*
 * Created by Jonah on 10/16/2015.
 */

public class SetAppearancePane
{
    public static Properties colorProps = new Properties();
    public static OutputStream colorPropsOutput = null;

    public static JComboBox<String> backgroundColorList;
    public static JComboBox<String> borderColorList;
    public static JComboBox<String> rwBackgroundColorList;
    public static JComboBox<String> menuColorList;

    public SetAppearancePane()
    {
        backgroundColorList = new JComboBox<>();
        borderColorList = new JComboBox<>();
        rwBackgroundColorList = new JComboBox<>();
        menuColorList = new JComboBox<>();

        try
        {
            colorPropsOutput = new FileOutputStream(Strings.pathToProps + "appearanceProps.properties");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

        JOptionPane setFontPane = new JOptionPane(JOptionPane.OK_CANCEL_OPTION);
        JDialog setFontDialog;

        String colors[] = {"Black", "Blue", "Cyan", "Dark Gray", "Gray", "Green", "Light Gray", "Magenta",
                "Orange", "Pink", "Red", "White", "Yellow"};
        for(String aColors : colors)
        {
            backgroundColorList.addItem(aColors);
            borderColorList.addItem(aColors);
            rwBackgroundColorList.addItem(aColors);
            menuColorList.addItem(aColors);
        }

        Object[] fontContents = {"Choose Background Color", backgroundColorList, "Choose Border Color", borderColorList,
                "Choose Rhyming Words Color", rwBackgroundColorList, "Choose Menu Bar Color", menuColorList};

        setFontPane.setMessage(fontContents);
        setFontPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        setFontDialog = setFontPane.createDialog(null, "Appearance Settings");
        setFontDialog.setVisible(true);

        if(setFontPane.getValue().equals(0))
        {
            int backIndex = backgroundColorList.getSelectedIndex();
            ColorSetter setBackground = new ColorSetter();
            setBackground.getColor(backIndex, MainFrame.mTextArea, "Background Color", colorProps, 1);

            int bordIndex = borderColorList.getSelectedIndex();
            ColorSetter setBorder = new ColorSetter();
            setBorder.getColor(bordIndex, MainFrame.mPanel, "Border Color", colorProps, 1);

            int rwIndex = rwBackgroundColorList.getSelectedIndex();
            ColorSetter setRW = new ColorSetter();
            setRW.getColor(rwIndex, MainFrame.rhymeListDisplay, "RW Color", colorProps, 1);

            int menuIndex = menuColorList.getSelectedIndex();
            ColorSetter setMenu = new ColorSetter();
            setMenu.getColor(menuIndex, MainFrame.mMenuBar, "Menu Color", colorProps, 1);

            try
            {
                colorProps.store(colorPropsOutput, Strings.pathToProps + "appearanceProps.properties");
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}