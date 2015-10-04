package handlers.menus;

/*
 * Created by Jonah on 8/3/2015.
 */

import frames.MainFrame;
import panes.FileDeleterPane;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class FileDeleterMH extends MainFrame
{
    public static void FDMH()
    {
        mFileDeleter.addMenuListener(new SampleMenuListener());
    }

    public static class SampleMenuListener implements MenuListener
    {
        @Override
        public void menuSelected(MenuEvent e)
        {
            SwingUtilities.invokeLater(FileDeleterPane::new);
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