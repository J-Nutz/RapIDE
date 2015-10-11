package panes.settingsPanes;

/*
 * Created by Jonah on 10/4/2015.
 */

import frames.MainFrame;
import local.Strings;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class SetFontsPane
{
    public static JComboBox<String> fontsList;
    public static JComboBox<Integer> fontSizesList;
    public static JComboBox<String> fontColorList;

    public static Properties fontProps = new Properties();
    public static OutputStream fontPropsOutput = null;

    public SetFontsPane()
    {
        fontsList = new JComboBox<>();
        fontSizesList = new JComboBox<>();
        fontColorList = new JComboBox<>();

        try
        {
            fontPropsOutput = new FileOutputStream(Strings.pathToProps + "fontProps.properties");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

        JOptionPane setFontPane = new JOptionPane(JOptionPane.OK_CANCEL_OPTION);
        JDialog setFontDialog;

        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for(String aFontsList : fonts)
        {
            fontsList.addItem(aFontsList);
        }

        int fontSizes[] = {10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 36, 42, 48};
        for(int aFontSizes : fontSizes)
        {
            fontSizesList.addItem(aFontSizes);
        }

        String colors[] = {"Black", "Blue", "Cyan", "Dark Gray", "Gray", "Green", "Light Gray", "Magenta",
                "Orange", "Pink", "Red", "White", "Yellow"};
        for(String aColors : colors)
        {
            fontColorList.addItem(aColors);
        }

        Object[] fontContents = {"Choose Font", SetFontsPane.fontsList, "Choose Font Size", fontSizesList,
                "Choose Font Color", fontColorList};

        setFontPane.setMessage(fontContents);
        setFontPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        setFontDialog = setFontPane.createDialog(null, "Font Settings");
        setFontDialog.setVisible(true);

        if(setFontPane.getValue().equals(0))
        {
            int selectedFontSize = (Integer) fontSizesList.getSelectedItem();
            String selectedFont = fontsList.getSelectedItem().toString();
            Font newFont = new Font(selectedFont, Font.PLAIN, selectedFontSize);

            MainFrame.mTextArea.setFont(newFont);

            getColor();

            String selectedFontSizeS = Integer.toString(selectedFontSize);
            saveFontProps(selectedFont, selectedFontSizeS);
        }
    }

    public static void getColor()
    {
        int selectedColorIndex = fontColorList.getSelectedIndex();

        switch(selectedColorIndex)
        {
            case -1:
                System.out.println("No Items In Font Color List");
                break;
            case 0:
                setColor(Color.black, "black");
                break;
            case 1:
                setColor(Color.blue, "blue");
                break;
            case 2:
                setColor(Color.cyan, "cyan");
                break;
            case 3:
                setColor(Color.darkGray, "darkGray");
                break;
            case 4:
                setColor(Color.gray, "gray");
                break;
            case 5:
                setColor(Color.green, "green");
                break;
            case 6:
                setColor(Color.lightGray, "lightGray");
                break;
            case 7:
                setColor(Color.magenta, "magenta");
                break;
            case 8:
                setColor(Color.orange, "orange");
                break;
            case 9:
                setColor(Color.pink, "pink");
                break;
            case 10:
                setColor(Color.red, "red");
                break;
            case 11:
                setColor(Color.white, "white");
                break;
            case 12:
                setColor(Color.yellow, "yellow");
                break;
            default:
                System.out.println("No Value");
                break;
        }
    }

    public static void setColor(Color color, String colorAsString)
    {
        MainFrame.mTextArea.setForeground(color);
        fontProps.put("Font Color", colorAsString);
    }

    public void saveFontProps(String fontType, String fontSize)
    {
        fontProps.put("Font Type", fontType);
        fontProps.put("Font Size", fontSize);

        try
        {
            fontProps.store(fontPropsOutput, Strings.pathToProps + "fontProps.properties");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}