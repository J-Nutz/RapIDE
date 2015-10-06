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

    public SetFontsPane()
    {
        fontsList = new JComboBox<>();
        fontSizesList = new JComboBox<>();
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

        Object[] fontContents = {"Choose Font", SetFontsPane.fontsList, "Choose Font Size", fontSizesList};

        setFontPane.setMessage(fontContents);
        setFontDialog = setFontPane.createDialog(null, "Font Settings");
        setFontDialog.setVisible(true);

        if(setFontPane.getValue().equals(0))
        {
            int selectedFontSize = (Integer) fontSizesList.getSelectedItem();
            String selectedFont = fontsList.getSelectedItem().toString();
            Font newFont = new Font(selectedFont, Font.PLAIN, selectedFontSize);

            MainFrame.mTextArea.setFont(newFont);
        }

    }
}