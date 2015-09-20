package panes;

/*
 * Created by Jonah on 9/19/2015.
 */

import logic.ReadingSaveFile;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static logic.RemoveChars.removeBannedChars;
import static logic.Saving.save;

public class FileRenamerPane
{

    public static JComboBox<Object> savesComboBox;
    public static JTextField newNameTF;
    public static JDialog renameDialog;
    public static String selectedSave;
    public static String newName;

    public FileRenamerPane()
    {
        savesComboBox = new JComboBox<>();
        setSavesComboBox();

        newNameTF = new JTextField("Enter New Name");

        Object[] jopContent = {"Choose File To Rename", savesComboBox, "Enter New Name Of File", newNameTF};

        JOptionPane renamerPane = new JOptionPane();
        renamerPane.setMessage(jopContent);

        renameDialog = renamerPane.createDialog(null, "File Renamer");
        renameDialog.setVisible(true);

        Object selectedSaveObj = savesComboBox.getSelectedItem();

        selectedSave = selectedSaveObj.toString();

        ReadingSaveFile.readingSavedFile(selectedSave);

        String adDir = System.getenv("APPDATA");
        String pathToSaves = adDir + "\\RapIDE\\saves\\" + selectedSave;
        Path mPath = FileSystems.getDefault().getPath(pathToSaves);

        newName = newNameTF.getText();

        try
        {
            Files.move(mPath, mPath.resolveSibling(removeBannedChars(newNameTF.getText())));
            save(newName);
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }

        ifNoSaves();

    }

    public void setSavesComboBox()
    {
        String adDir = System.getenv("APPDATA");
        String pathToSaves = adDir + "\\RapIDE\\saves";

        File savesFolder = new File(pathToSaves);
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

    public void ifNoSaves(){

        if(savesComboBox.getItemCount() == 0)
        {
            renameDialog.dispose();

            SwingUtilities.invokeLater(FileCreatorPane::new);
        }
    }

}
