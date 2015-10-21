package panes.settingsPanes;

/*
 * Created by Jonah on 10/4/2015.
 */

import frames.MainFrame;
import local.Strings;
import logic.setters.ColorSetter;

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
    public static JComboBox<String> fontStyleList;

    public static Properties fontProps = new Properties();
    public static OutputStream fontPropsOutput = null;

    public SetFontsPane()
    {
        fontsList = new JComboBox<>();
        fontSizesList = new JComboBox<>();
        fontColorList = new JComboBox<>();
        fontStyleList = new JComboBox<>();

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

        String fontStyles[] = {"Plain", "Bold", "Italic"};
        for(String aFontStyles : fontStyles)
        {
            fontStyleList.addItem(aFontStyles);
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

        Object[] fontContents = {"Choose Font", fontsList, "Choose Font Style", fontStyleList,
                "Choose Font Size", fontSizesList, "Choose Font Color", fontColorList,};

        setFontPane.setMessage(fontContents);
        setFontPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        setFontDialog = setFontPane.createDialog(null, "Font Settings");
        setFontDialog.setVisible(true);

        if(setFontPane.getValue().equals(0))
        {
            int selectedFontSize = (Integer) fontSizesList.getSelectedItem();
            int selectedFontStyle = getStyle();
            String selectedFont = fontsList.getSelectedItem().toString();
            //noinspection MagicConstant
            Font newFont = new Font(selectedFont, selectedFontStyle, selectedFontSize);

            MainFrame.mTextArea.setFont(newFont);

            //getColor();
            int fontIndex = fontColorList.getSelectedIndex();
            ColorSetter setFont = new ColorSetter();
            setFont.getColor(fontIndex, MainFrame.mTextArea, "Font Color", fontProps, 2);


            String selectedFontSizeS = Integer.toString(selectedFontSize);
            saveFontProps(selectedFont, selectedFontSizeS);
        }
    }

    public static int getStyle()
    {
        String selectedStyle = fontStyleList.getSelectedItem().toString();
        int selectedStyleInt = 0;

        switch(selectedStyle)
        {
            case "Plain":
                selectedStyleInt = 0;
                break;
            case "Bold":
                selectedStyleInt = 1;
                break;
            case "Italic":
                selectedStyleInt = 2;
                break;
            default:
                break;
        }

        String fontStyle = Integer.toString(selectedStyleInt);
        fontProps.put("Font Style", fontStyle);
        return selectedStyleInt;
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