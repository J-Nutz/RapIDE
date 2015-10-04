package handlers.menus.menuItems.settings;

/*
 * Created by Jonah on 10/3/2015.
 */

import frames.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class fontAL implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("Font Settings Opening");
    }
}

public class FontMIH extends MainFrame
{
    public static void FMIH()
    {
        sFont.addActionListener(new fontAL());
    }
}