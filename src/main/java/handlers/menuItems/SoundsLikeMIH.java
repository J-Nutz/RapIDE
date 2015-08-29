package handlers.menuItems;

/*
 * Created by Jonah on 7/28/2015.
 */

import frames.MainFrame;
import frames.SoundsLikeFrame;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class SoundsLikeMIH
{
    public static void SLMIH()
    {
        MainFrame.mSoundsLike.addMenuListener(new SampleMenuListener());
    }

    public static class SampleMenuListener implements MenuListener
    {
        @Override
        public void menuSelected(MenuEvent e)
        {
            SwingUtilities.invokeLater(SoundsLikeFrame::new);
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