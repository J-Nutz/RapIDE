package handlers.menuItems;

/*
 * Created by Jonah on 8/3/2015.
 */

import frames.MainFrame;
import panes.FileDeleterPane;
import panes.FileLoaderPane;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import static panes.FileDeleterPane.savesComboBox;
import static panes.FileDeleterPane.setSavesComboBox;

public class FileDeleterMIH extends MainFrame
{
    public static void FDMIH()
    {
        mFileDeleter.addMenuListener(new SampleMenuListener());
    }

    public static class SampleMenuListener implements MenuListener
    {
        @Override
        public void menuSelected(MenuEvent e)
        {
            setSavesComboBox();
            if(savesComboBox.getItemCount() > 0)
            {
                SwingUtilities.invokeLater(FileDeleterPane::new);
            } else
            {
                SwingUtilities.invokeLater(FileLoaderPane::new);
                System.out.println("No Files...");
            }
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