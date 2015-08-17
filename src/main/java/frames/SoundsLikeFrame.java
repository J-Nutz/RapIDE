package frames;

/*
 * Created by Jonah on 7/28/2015.
 */

import logic.APIhitter;

import javax.swing.*;
import java.awt.*;

public class SoundsLikeFrame extends JFrame
{
    public JTextField soundsLikeTF;
    private JButton accept;
    private JButton cancel;
    private JPanel mSLFPanel;

    public static String wordToParse;

    public SoundsLikeFrame()
    {
        accept = new JButton("Get SL Words");
        cancel = new JButton("Cancel");

        soundsLikeTF = new JTextField("Search Sl Words");

        mSLFPanel = new JPanel();

        FNFCreateView();
        acceptActionListener();
        cancelActionListener();
    }

    public void FNFCreateView()
    {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(210, 100);
        setResizable(false);
        setAlwaysOnTop(true);
        getContentPane().add(mSLFPanel);

        mSLFPanel.setLayout(new FlowLayout());

        mSLFPanel.add(soundsLikeTF);
        mSLFPanel.add(accept);
        mSLFPanel.add(cancel);

        accept.setSize(25, 15);
        cancel.setSize(25, 15);

        setVisible(true);
    }

    public void acceptActionListener()
    {
        accept.addActionListener(e -> {

            wordToParse = soundsLikeTF.getText();

            dispose();

            APIhitter.hitAPI(wordToParse);

        });
    }

    public void cancelActionListener()
    {
        cancel.addActionListener(e -> SoundsLikeFrame.this.dispose());
    }
}