package handlers.menuItems;

/*
 * Created by Jonah on 8/3/2015.
 */

import logic.Saving;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.io.IOException;

public class SavingMIH extends Saving
{
    public static void SMIH()
    {
        mSave.addMenuListener(new SampleMenuListener());
    }

    public static class SampleMenuListener implements MenuListener
    {
        @Override
        public void menuSelected(MenuEvent e)
        {
            try
            {
                saveFile();
            }
            catch(IOException e1)
            {
                e1.printStackTrace();
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