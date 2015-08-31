package handlers.menuItems;

/*
 * Created by Jonah on 7/23/2015.
 */

import frames.MainFrame;
import panes.FileCreatorPane;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class FileCreatorMIH extends MainFrame
{
    public static void FNMIH()
    {
        mCreateFile.addMenuListener(new SampleMenuListener());
    }

    public static class SampleMenuListener implements MenuListener
    {
        @Override
        public void menuSelected(MenuEvent e)
        {
            SwingUtilities.invokeLater(FileCreatorPane::new);
        }

        @Override
        public void menuDeselected(MenuEvent e)
        {

        }

        @Override
        public void menuCanceled(MenuEvent e)
        {

        }
    }
}