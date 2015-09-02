package panes;

/*
 * Created by Jonah on 8/30/2015.
 */

import javax.swing.*;
import java.io.IOException;

import static frames.MainFrame.mTextArea;
import static logic.Saving.save;

public class FileCreatorPane extends JOptionPane
{
    public static String FileName;

    public FileCreatorPane()
    {
        FileName = JOptionPane.showInputDialog("Enter Name Of New File");

        if(FileName == null)
        {
            System.out.println("Da Faq?");
        }
        else
        {
            mTextArea.setText("");

            try
            {
                save(FileName);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}