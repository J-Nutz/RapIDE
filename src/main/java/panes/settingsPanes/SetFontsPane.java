package panes.settingsPanes;

/*
 * Created by Jonah on 10/4/2015.
 */

import frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class SetFontsPane
{
    public static JComboBox<String> fontsList;
    public static JComboBox<Integer> fontSizesList;
    public static JComboBox<String> fontColorList;

    public SetFontsPane()
    {
        fontsList = new JComboBox<>();
        fontSizesList = new JComboBox<>();
        fontColorList = new JComboBox<>();

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
                setColor(Color.black);
                break;
            case 1:
                setColor(Color.blue);
                break;
            case 2:
                setColor(Color.cyan);
                break;
            case 3:
                setColor(Color.darkGray);
                break;
            case 4:
                setColor(Color.gray);
                break;
            case 5:
                setColor(Color.green);
                break;
            case 6:
                setColor(Color.lightGray);
                break;
            case 7:
                setColor(Color.magenta);
                break;
            case 8:
                setColor(Color.orange);
                break;
            case 9:
                setColor(Color.pink);
                break;
            case 10:
                setColor(Color.red);
                break;
            case 11:
                setColor(Color.white);
                break;
            case 12:
                setColor(Color.yellow);
                break;
            default:
                System.out.println("Idk");
                break;
        }
    }

    public static void setColor(Color color)
    {
        MainFrame.mTextArea.setForeground(color);
    }

}