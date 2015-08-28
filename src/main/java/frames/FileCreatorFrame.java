package frames;

/*
 * Created by Jonah on 7/24/2015.
 */

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static frames.MainFrame.mTextArea;
import static logic.Saving.saveFile;

public class FileCreatorFrame extends JFrame
{
    public JTextField fileNameTF;
    private JButton accept;
    private JButton cancel;
    private JPanel mFNFPanel;

    public static String FileName;

    public FileCreatorFrame()
    {
        accept = new JButton("Name File");
        cancel = new JButton("Cancel");

        fileNameTF = new JTextField("Enter File Name");

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

        mFNFPanel.add(fileNameTF);
        mFNFPanel.add(accept);
        mFNFPanel.add(cancel);

        accept.setSize(25, 15);
        cancel.setSize(25, 15);

        setVisible(true);
    }

    public void acceptActionListener()
    {
        accept.addActionListener(e -> {

            FileName = fileNameTF.getText();

            char[] bannedCharArray = {'/', ':', '*', '?', '<', '>', '|', '"'};
            for (char charToRemove : bannedCharArray)
            {
                FileName = FileName.replace("" + charToRemove, "");
            }

            fileNameTF.setText(FileName);
            mTextArea.setText("");

            try
            {
                saveFile();
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
        cancel.addActionListener(e -> dispose());
    }
}