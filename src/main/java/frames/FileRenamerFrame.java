package frames;

/*
 * Created by Jonah on 8/20/2015.
 */

import logic.ReadingSaveFile;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FileRenamerFrame extends JFrame
{

    public JTextField fileRenameTF;
    private JButton accept;
    private JButton cancel;
    private JPanel mFNFPanel;

    public FileRenamerFrame()
    {
        accept = new JButton("Rename File");
        cancel = new JButton("Cancel");

        fileRenameTF = new JTextField("Enter File Name");

        mFNFPanel = new JPanel();

        FNFCreateView();
        acceptActionListener();
        cancelActionListener();
    }

    public void FNFCreateView()
    {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(185, 100);
        setResizable(false);
        setAlwaysOnTop(true);
        getContentPane().add(mFNFPanel);

        mFNFPanel.setLayout(new FlowLayout());

        mFNFPanel.add(fileRenameTF);
        mFNFPanel.add(accept);
        mFNFPanel.add(cancel);

        accept.setSize(25, 15);
        cancel.setSize(25, 15);

        setVisible(true);
    }

    public void acceptActionListener()
    {
        accept.addActionListener(e -> {

            if(ReadingSaveFile.SelectedSaveFileCopy != null)
            {
                System.out.println(fileRenameTF.getText());

                String adDir = System.getenv("APPDATA");
                String pathToSaves = adDir + "\\RapIDE\\saves";

                File oldFile = new File(pathToSaves + ReadingSaveFile.SelectedSaveFileCopy);
                System.out.println(ReadingSaveFile.SelectedSaveFileCopy);

                File newFile = new File(pathToSaves + fileRenameTF.getText());

                    if(newFile.exists()){
                        try
                        {
                            throw new java.io.IOException("File Already Exists");
                        }
                        catch (IOException e1)
                        {
                            e1.printStackTrace();
                        }
                    }

                boolean renameStatus = oldFile.renameTo(newFile);

                if(!renameStatus)
                {
                    System.out.println("Error");
                }

                //FileLoaderFrame.selectedSave
            }

            dispose();
        });
    }

    public void cancelActionListener()
    {
        cancel.addActionListener(e -> dispose());
    }


}
