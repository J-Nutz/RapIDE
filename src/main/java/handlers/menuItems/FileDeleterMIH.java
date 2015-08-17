package handlers.menuItems;

/*
 * Created by Jonah on 8/3/2015.
 */

import frames.FileDeleterFrame;
import frames.FileNameFrame;
import frames.MainFrame;
import logic.GetFileList;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import static logic.GetFileList.GetFiles;

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

            GetFiles();

            if(GetFileList.hasSaves)
            {
                SwingUtilities.invokeLater(FileDeleterFrame::new);
            }
            else
            {
                SwingUtilities.invokeLater(FileNameFrame::new);
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