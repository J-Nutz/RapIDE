package frames;/*
 * Created by Jonah on 8/3/2015.
 */

import panes.FileCreatorPane;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileDeleterFrame extends JFrame
{
    public static JComboBox<Object> savesComboBox;

    private JPanel mFLFPanel;

    private JButton accept;
    private JButton cancel;

    public static String adDir = System.getenv("APPDATA");
    public static String pathToSaves = adDir + "\\RapIDE\\saves\\";

    public FileDeleterFrame()
    {
        mFLFPanel = new JPanel();

        savesComboBox = new JComboBox<>();

        accept = new JButton("Delete");
        cancel = new JButton("Cancel");

        FLFCreateView();
        setSavesComboBox();
        closeIfNoSaves();
        acceptActionListener();
        cancelActionListener();
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
        mFLFPanel.add(cancel);

        setVisible(true);
    }

    public void acceptActionListener()
    {
        accept.addActionListener(e -> {

            Object selectedSaveObj = savesComboBox.getSelectedItem();
            String selectedSave = selectedSaveObj.toString();

            try
            {
                File fileToDelete = new File(pathToSaves + selectedSave);

                if(fileToDelete.delete())
                {
                    System.out.println(selectedSave + " File Deleted");
                    setSavesComboBox();
                    closeIfNoSaves();
                    MainFrame.mTextArea.setText("");
                }
            }
            catch(Exception de)
            {
                System.out.println("Error: " + de);
            }
        });
    }

    public void cancelActionListener()
    {
        cancel.addActionListener(e -> dispose());
    }

    public void setSavesComboBox()
    {
        savesComboBox.removeAllItems();

        File savesFolder = new File(pathToSaves);
        File[] listOfFiles = savesFolder.listFiles();

        if (listOfFiles != null)
        {
            for (File file : listOfFiles)
            {
                if (file.isFile())
                {
                    savesComboBox.addItem(file.getName());
                }
                else if (file.isDirectory())
                {
                    System.out.println("Fucked M8");
                }
            }
        }
    }

    public void closeIfNoSaves()
    {
        File savesFolder = new File(pathToSaves);

        if(savesFolder.isDirectory()){

            if(savesFolder.list().length > 0){

                System.out.println("Directory is not empty!");

            }else{

                System.out.println("Directory is empty!");
                dispose();
                SwingUtilities.invokeLater(FileCreatorPane::new);

            }

        }else{

            System.out.println("This is not a directory");

        }

        /*if()
        {
            System.out.println("Does Have Saves");
        }
        else
        {
            System.out.println("Has Saves");
            dispose();
            SwingUtilities.invokeLater(FileCreatorPane::new);
        }*/
    }
}