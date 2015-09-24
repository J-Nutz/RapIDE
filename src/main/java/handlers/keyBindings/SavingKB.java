package handlers.keyBindings;

/*
 * Created by Jonah on 7/23/2015.
 */

import logic.Saving;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class SavingKB extends Saving
{
    public static void savingBinding()
    {
        mTextArea.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt)
            {

            }

            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_S)
                {
                    try
                    {
                        save();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt)
            {

            }
        });
    }
}