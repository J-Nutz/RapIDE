package frames;

/*
 * Created by Jonah on 8/27/2015.
 */

import logic.ReadingSaveFile;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static logic.RemoveChars.removeBannedChars;

public class FileRenamerFrame extends JFrame
{
    public static JComboBox<Object> savesComboBox;

    public static String selectedSave;

    private JPanel mFTRPanel;

    private JButton rename;
    private JButton cancel;
    private JTextField newNameTF;

    public FileRenamerFrame()
    {
        mFTRPanel = new JPanel();

        savesComboBox = new JComboBox<>();

        rename = new JButton("Rename File");
        cancel = new JButton("Cancel");

        newNameTF = new JTextField("Enter New Name");

        FLFCreateView();

        selectActionListener();
        cancelActionListener();
        setSavesComboBox();
        ifNoSaves();
    }

    public void FLFCreateView()
    {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(275, 100);
        setResizable(false);
        setAlwaysOnTop(true);
        getContentPane().add(mFTRPanel);

        mFTRPanel.setLayout(new FlowLayout());
        mFTRPanel.add(savesComboBox);
        mFTRPanel.add(newNameTF);
        mFTRPanel.add(rename);
        mFTRPanel.add(cancel);

        setVisible(true);
    }

    public void selectActionListener()
    {
        rename.addActionListener(e -> {

            Object selectedSaveObj = savesComboBox.getSelectedItem();
            selectedSave = selectedSaveObj.toString();

            ReadingSaveFile.readingSavedFile(selectedSave);

            String adDir = System.getenv("APPDATA");
            String pathToSaves = adDir + "\\RapIDE\\saves\\" + selectedSave;
            Path mPath = FileSystems.getDefault().getPath(pathToSaves);

            try
            {
                Files.move(mPath, mPath.resolveSibling(removeBannedChars(newNameTF.getText())));
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }

            dispose();

        });
    }

    public void cancelActionListener()
    {
        cancel.addActionListener(e -> {

            dispose();
            SwingUtilities.invokeLater(FileCreatorFrame::new);

        });
    }

    public void setSavesComboBox()
    {
        String adDir = System.getenv("APPDATA");
        String pathToSaves = adDir + "\\RapIDE\\saves";

        File savesFolder = new File(pathToSaves);
        File[] listOfFiles = savesFolder.listFiles();

        if (listOfFiles != null)
        {
            for (File file : listOfFiles)
            {
                if (file.isFile())
                {
                    savesComboBox.addItem(file.getName());
                    System.out.println("Loading: " + file.getName());
                }
                else if (file.isDirectory())
                {
                    System.out.println("Fucked M8");
                }
            }
        }
    }

    public void ifNoSaves(){

        if(savesComboBox.getItemCount() == 0)
        {
            dispose();

            SwingUtilities.invokeLater(FileCreatorFrame::new);
        }
    }
}