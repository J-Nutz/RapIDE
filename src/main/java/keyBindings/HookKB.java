package keyBindings;

/*
 * Created by Jonah on 7/23/2015.
 */

import frames.MainFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HookKB extends MainFrame
{
    public static void hookBinding()
    {
        String hookIn = "[Hook] \n \n [Hook]";

        mTextArea.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt)
            {

            }

            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_H)
                {
                    int curCarPos = mTextArea.getCaretPosition();

                    mTextArea.insert(hookIn, curCarPos);
                    mTextArea.setCaretPosition(curCarPos + 9);
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt)
            {

            }
        });
    }
}