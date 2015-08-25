package frames;

/*
 * Created by Jonah on 8/20/2015.
 */

import logic.ReadingSaveFile;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

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
                String adDir = System.getenv("APPDATA");
                String pathToSaves = adDir + "\\RapIDE\\saves\\" + ReadingSaveFile.SelectedSaveFileCopy;
                Path mPath = FileSystems.getDefault().getPath(pathToSaves);

                try
                {
                    Files.move(mPath, mPath.resolveSibling(fileRenameTF.getText()));
                }
                catch(IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            else
            {
                String adDir = System.getenv("APPDATA");
                String pathToSaves = adDir + "\\RapIDE\\saves\\" + FileCreatorFrame.FileName;
                Path mPath = FileSystems.getDefault().getPath(pathToSaves);

                try
                {
                    Files.move(mPath, mPath.resolveSibling(fileRenameTF.getText()));
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            dispose();
        });
    }

    public void cancelActionListener()
    {
        cancel.addActionListener(e -> dispose());
    }
}