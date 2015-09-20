package panes;

/*
 * Created by Jonah on 9/20/2015.
 */

import frames.MainFrame;
import local.Strings;

import javax.swing.*;
import java.io.File;

public class FileDeleterPane
{
    public static JComboBox<Object> savesComboBox = new JComboBox<>();
    public static JDialog deleterDialog;

    public FileDeleterPane()
    {
        closeIfNoSaves();
        setSavesComboBox();

        JButton cancelBtn = new JButton("Cancel");
        Object[] jopContent = {"Choose File To Delete", savesComboBox, cancelBtn};

        JOptionPane deleterPane = new JOptionPane();
        deleterPane.setMessage(jopContent);

        deleterDialog = deleterPane.createDialog(null, "File Deleter");
        deleterDialog.setVisible(true);

        cancelBtn.addActionListener(e -> {
            deleterDialog.dispose();
            System.out.println("Canceling");
        });
        //deleteFile(savesComboBox.getSelectedItem());
    }

    public static void setSavesComboBox()
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

    public static void closeIfNoSaves()
    {
        File savesFolder = new File(Strings.pathToSaves);

        if(savesFolder.isDirectory())
        {
            if(savesFolder.list().length > 0)
            {
                System.out.println("Directory is not empty!");
            }
            else
            {
                System.out.println("Directory is empty!");
                deleterDialog.dispose();
                SwingUtilities.invokeLater(FileCreatorPane::new);
            }
        }
        else
        {
            System.out.println("This is not a directory");
        }
    }

    public static void deleteFile(Object toDelete)
    {
        try
        {
            File fileToDelete = new File(Strings.pathToSaves + toDelete.toString());

            if(fileToDelete.delete())
            {
                System.out.println(toDelete + " File Deleted");
                setSavesComboBox();
                closeIfNoSaves();
                MainFrame.mTextArea.setText("");
                SwingUtilities.invokeLater(FileLoaderPane::new);
            }
        }
        catch(Exception de)
        {
            System.out.println("Error: " + de);
        }
    }
}