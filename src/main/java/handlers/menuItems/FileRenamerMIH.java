package handlers.menuItems;

/*
 * Created by Jonah on 8/20/2015.
 */

import frames.MainFrame;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class FileRenamerMIH extends MainFrame
{
    public static void FRMIH()
    {
        mRenameFile.addMenuListener(new SampleMenuListener());
    }

    public static class SampleMenuListener implements MenuListener
    {
        @Override
        public void menuSelected(MenuEvent e)
        {

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