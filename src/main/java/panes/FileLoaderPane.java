package panes;

/*
 * Created by Jonah on 8/28/2015.
 */

import local.Strings;

import javax.swing.*;
import java.io.File;

import static logic.ReadingSaveFile.readingSavedFile;

public class FileLoaderPane
{
    public static JComboBox<Object> savesComboBox = new JComboBox<>();
    public static JDialog loaderDialog;

    public FileLoaderPane()
    {
        Object[] jopContent = {"Choose File To Load", savesComboBox};

        JOptionPane loaderPane = new JOptionPane();
        loaderPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        loaderPane.setMessage(jopContent);

        setSavesComboBox();

        loaderDialog = loaderPane.createDialog(null, "File Loader");
        loaderDialog.setVisible(true);

        closeIfNoSaves();

        String selectedSave = savesComboBox.getSelectedItem().toString();

        if(loaderPane.getValue().equals(0))
        {
            readingSavedFile(selectedSave);
            Strings.MainFileName = selectedSave;
            System.out.println(selectedSave + " File Loaded");
        }
        else
        {
            System.out.println("Canceled Loading");
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
                    System.out.println("No Files");
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
            loaderDialog.dispose();
        }

        return empty;
    }
}