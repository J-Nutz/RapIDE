package handlers.menus.menuItems.settings;

import frames.MainFrame;
import panes.settingsPanes.SetAppearancePane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Created by Jonah on 10/16/2015.
 */

class appearanceAL implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        SwingUtilities.invokeLater(SetAppearancePane::new);
    }
}

public class AppearanceMIH extends MainFrame
{
    public static void AMIH()
    {
        sColors.addActionListener(new appearanceAL());
    }
}