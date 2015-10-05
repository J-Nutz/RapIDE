package panes.settingsPanes;

/*
 * Created by Jonah on 10/4/2015.
 */

import javax.swing.*;

public class SetFontsPane
{


    public SetFontsPane()
    {
        JComboBox fonts = new JComboBox();
        JOptionPane setFontPane = new JOptionPane(JOptionPane.OK_CANCEL_OPTION);
        JDialog setFontDialog;

        Object[] fontContents = {"Choose Font", fonts};

        setFontPane.setMessage(fontContents);
        setFontDialog = setFontPane.createDialog(null, "Font Settings");
        setFontDialog.setVisible(true);

    }
}
