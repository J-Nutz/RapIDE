package handlers;

/*
 * Created by Jonah on 7/23/2015.
 */

import frames.FileNameFrame;
import frames.MainFrame;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class FileNameMIH extends MainFrame
{

    public static void FNMIH()
    {

        mFileName.addMenuListener(new SampleMenuListener());

    }

    public static class SampleMenuListener implements MenuListener
    {

        @Override
        public void menuSelected(MenuEvent e)
        {

            SwingUtilities.invokeLater(FileNameFrame::new);

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
