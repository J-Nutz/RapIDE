package panes;

/*
 * Created by Jonah on 8/30/2015.
 */

import local.Strings;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static frames.MainFrame.mTextArea;
import static logic.Saving.save;

public class FileCreatorPane
{
    public static JTextField newNameTF = new JTextField("Enter New Name");
    public static JDialog creatorDialog;
    public static String newName;

    public FileCreatorPane()
    {
        Object[] jopContent = {"Enter New Name Of File", newNameTF};

        JOptionPane creatorPane = new JOptionPane();
        creatorPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        creatorPane.setMessage(jopContent);
        creatorDialog = creatorPane.createDialog(null, "File Creator");

        newNameTF.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                newNameTF.setText("");
            }
        });

        creatorDialog.setVisible(true);

        newName = newNameTF.getText();

        if(creatorPane.getValue().equals(0))
        {
            creatorDialog.dispose();
            mTextArea.setText("");
            Strings.MainFileName = newName;
            newNameTF.setText("Enter New Name");

            try
            {
                save();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

        }
        else
        {
            System.out.println("Not Renaming Anything");
        }
    }
}