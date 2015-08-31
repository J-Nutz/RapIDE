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
    public static Object FileName;

    public FileCreatorPane()
    {
        FileName = JOptionPane.showInputDialog(null,
                "Enter Name Of New File", "File Loader",
                JOptionPane.QUESTION_MESSAGE);
        mTextArea.setText("");

        try
        {
            save(FileName.toString());
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
    }
}