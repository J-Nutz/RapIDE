package frames;/*
 * Created by Jonah on 8/3/2015.
 */

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileDeleterFrame extends JFrame
{
    public static JComboBox<Object> savesComboBox;

    private JPanel mFLFPanel;

    private JButton accept;
    private JButton cancel;

    public FileDeleterFrame()
    {
        mFLFPanel = new JPanel();

        savesComboBox = new JComboBox<>();

        accept = new JButton("Delete");
        cancel = new JButton("Cancel");

        FLFCreateView();
        acceptActionListener();
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

            String adDir = System.getenv("APPDATA");
            String pathToSaves = adDir + "\\RapIDE\\saves\\";

            try
            {
                File fileToDelete = new File(pathToSaves + selectedSave);

                if(fileToDelete.delete())
                {
                    System.out.println(selectedSave + " File Deleted");

                    SwingUtilities.invokeLater(FileLoaderFrame::new);
                }
            }
            catch(Exception de)
            {
                System.out.println("Error: " + de);
            }

            MainFrame.mTextArea.setText("");

            dispose();

        });

    }

    public void cancelActionListener()
    {
        cancel.addActionListener(e -> dispose());
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
        }
    }
}