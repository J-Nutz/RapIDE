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

        Object[] jopContent = {"Choose File To Delete", savesComboBox};

        JOptionPane deleterPane = new JOptionPane();
        deleterPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        deleterPane.setMessage(jopContent);

        final JDialog deleterDialog = deleterPane.createDialog(null, "File Deleter");
        deleterDialog.setVisible(true);

        if(deleterPane.getValue().equals(0))
        {
            deleteFile(savesComboBox.getSelectedItem());
            closeIfNoSaves();
        }
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
                    System.out.println("Folder In Saves");
                }
            }
        }
    }

    public static boolean closeIfNoSaves()
    {
        boolean empty = false;

        if(savesComboBox.getItemCount() == 0)
        {
            empty = true;
            SwingUtilities.invokeLater(FileCreatorPane::new);
            deleterDialog.dispose();
        }

        return empty;
    }

    public static void deleteFile(Object toDelete)
    {
        try
        {
            File fileToDelete = new File(Strings.pathToSaves + toDelete.toString());

            if(fileToDelete.delete())
            {
                MainFrame.mTextArea.setText("");
                System.out.println(toDelete + " File Deleted");
                setSavesComboBox();
                Strings.MainFileName = null;
                if(closeIfNoSaves())
                {
                    System.out.println("Closing, No Saves");
                }
                else
                {
                    SwingUtilities.invokeLater(FileLoaderPane::new);
                }
            }
        }
        catch(Exception de)
        {
            System.out.println("Error: " + de);
        }
    }
}