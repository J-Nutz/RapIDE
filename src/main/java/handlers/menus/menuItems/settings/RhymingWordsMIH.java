package handlers.menus.menuItems.settings;

/*
 * Created by Jonah on 10/12/2015.
 */

import frames.MainFrame;
import panes.settingsPanes.SetRhymingWordsPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class rhymingWordsAL implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        SwingUtilities.invokeLater(SetRhymingWordsPane::new);
    }
}

public class RhymingWordsMIH extends MainFrame
{
    public static void RWMIH()
    {
        sRhymingWords.addActionListener(new rhymingWordsAL());
    }
}