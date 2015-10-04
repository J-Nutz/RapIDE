package handlers.menus;

/*
 * Created by Jonah on 8/20/2015.
 */

import frames.MainFrame;
import panes.FileLoaderPane;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class FileOpenerMH extends MainFrame
{

    public static void FOMH()
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
