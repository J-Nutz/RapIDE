package frames;

/*
 * Created by Jonah on 7/25/2015.
 */

import logic.ReadingSaveFile;
import panes.FileCreatorPane;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileLoaderFrame extends JFrame
{
    public static JComboBox<Object> savesComboBox;

    public static String selectedSave;

    private JPanel mFLFPanel;

    private JButton accept;
    private JButton createNew;

    public FileLoaderFrame()
    {
        mFLFPanel = new JPanel();

        savesComboBox = new JComboBox<>();

        accept = new JButton("Load File");
        createNew = new JButton("Create New");

        FLFCreateView();

        acceptActionListener();
        createNewActionListener();
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
        getContentPane().add(mFLFPanel);

        mFLFPanel.setLayout(new FlowLayout());
        mFLFPanel.add(savesComboBox);
        mFLFPanel.add(accept);
        mFLFPanel.add(createNew);

        setVisible(true);
    }

    public void acceptActionListener()
    {
        accept.addActionListener(e ->
        {
            Object selectedSaveObj = savesComboBox.getSelectedItem();
            selectedSave = selectedSaveObj.toString();

            ReadingSaveFile.readingSavedFile(selectedSave);

            dispose();
        });
    }

    public void createNewActionListener()
    {
        createNew.addActionListener(e -> {

            dispose();
            SwingUtilities.invokeLater(FileCreatorPane::new);

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
                } else if (file.isDirectory())
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

            SwingUtilities.invokeLater(FileCreatorPane::new);
        }
    }
}