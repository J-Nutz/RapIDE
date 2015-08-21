package handlers.menuItems;

/*
 * Created by Jonah on 7/23/2015.
 */

import frames.FileCreatorFrame;
import frames.MainFrame;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class FileNameMIH extends MainFrame
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
            SwingUtilities.invokeLater(FileCreatorFrame::new);
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