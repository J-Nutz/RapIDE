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
    public static JOptionPane deleterPane = new JOptionPane();

    public FileDeleterPane()
    {
        setSavesComboBox();
        closeIfNoSaves();

        Object[] jopContent = {"Choose File To Delete", savesComboBox};

        deleterPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        deleterPane.setMessage(jopContent);

        JDialog deleterDialog = deleterPane.createDialog(null, "File Deleter");
        deleterDialog.setVisible(true);

        if(deleterPane.getValue().equals(0))
        {
            deleteFile(savesComboBox.getSelectedItem());

            if(savesComboBox.getItemCount() > 0)
            {
                SwingUtilities.invokeLater(FileLoaderPane::new);
            }
            else
            {
                SwingUtilities.invokeLater(FileCreatorPane::new);
            }
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
        boolean empty;

        setSavesComboBox();

        if(savesComboBox.getItemCount() == 0)
        {
            empty = true;
            deleterDialog.dispose();
            SwingUtilities.invokeLater(FileCreatorPane::new);
        }
        else
        {
            System.out.println("Not Closing, Still Saves");
            empty = false;
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
            }
        }
        catch(Exception de)
        {
            System.out.println("DeleterPane Error: " + de);
        }
    }
}