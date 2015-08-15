package frames;

/*
 * Created by Jonah on 7/24/2015.
 */

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static logic.Saving.saveFile;

public class FileNameFrame extends JFrame
{

    public JTextField fileNameTF;
    private JButton accept;
    private JButton cancel;
    private JPanel mFNFPanel;

    public static String FileName;

    public FileNameFrame()
    {

        accept = new JButton("Name File");
        cancel = new JButton("Cancel");

        fileNameTF = new JTextField(" Enter File Name ");

        mFNFPanel = new JPanel();

        FNFCreateView();
        acceptActionListener();
        cancelActionListener();

    }

    public void FNFCreateView()
    {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("File Namer");
        setSize(200, 100);
        setResizable(false);
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
            fileNameTF.setText(FileName);

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
