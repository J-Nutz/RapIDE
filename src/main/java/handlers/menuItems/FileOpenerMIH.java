package handlers.menuItems;

/*
 * Created by Jonah on 8/20/2015.
 */

import frames.MainFrame;
import panes.FileLoaderPane;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class FileOpenerMIH extends MainFrame
{

    public static void FOMIH()
    {
        mOpenFile.addMenuListener(new SampleMenuListener());
    }

    public static class SampleMenuListener implements MenuListener
    {
        @Override
        public void menuSelected(MenuEvent e)
        {
            SwingUtilities.invokeLater(FileLoaderPane::new);
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
