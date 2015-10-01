package panes;

/*
 * Created by Jonah on 9/19/2015.
 */

import local.Strings;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static logic.RemoveChars.removeBannedChars;
import static logic.Saving.save;

public class FileRenamerPane
{
    public static JComboBox<Object> savesComboBox = new JComboBox<>();
    public static JTextField newNameTF = new JTextField("Enter New Name");
    public static JDialog renameDialog;
    public static String selectedSave;
    public static String newName;

    public FileRenamerPane()
    {
        setSavesComboBox();
        ifNoSaves();

        Object[] jopContent = {"Choose File To Rename", savesComboBox, "Enter New Name Of File", newNameTF};

        JOptionPane renamerPane = new JOptionPane();
        renamerPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        renamerPane.setMessage(jopContent);

        newNameTF.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                newNameTF.setText("");
            }
        });

        renameDialog = renamerPane.createDialog(null, "File Renamer");
        renameDialog.setVisible(true);

        selectedSave = savesComboBox.getSelectedItem().toString();

        Path mPath = FileSystems.getDefault().getPath(Strings.pathToSaves + selectedSave);

        newName = newNameTF.getText();

        if(renamerPane.getValue().equals(0))
        {
            try
            {
                Files.move(mPath, mPath.resolveSibling(removeBannedChars(newName)));
                setSavesComboBox();
                Strings.MainFileName = newName;
                newNameTF.setText("Enter New Name");
                save();
                ifNoSaves();
            }
            catch(IOException e1)
            {
                e1.printStackTrace();
            }
        }
        else
        {
            System.out.println("Not Renaming Anything");
        }
    }

    public void setSavesComboBox()
    {
        savesComboBox.removeAllItems();

        File savesFolder = new File(Strings.pathToSaves);
        File[] listOfFiles = savesFolder.listFiles();

        if(listOfFiles != null)
        {
            for(File file : listOfFiles)
            {
                if(file.isFile())
                {
                    savesComboBox.addItem(file.getName());
                }
                else if(file.isDirectory())
                {
                    System.out.println("Fucked M8");
                }
            }
        }
    }

    public void ifNoSaves()
    {
        if(savesComboBox.getItemCount() == 0)
        {
            renameDialog.dispose();

            SwingUtilities.invokeLater(FileCreatorPane::new);
        }
    }
}