package panes;

/*
 * Created by Jonah on 9/15/2015.
 */

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static logic.XmlApiHitter.hitXmlAPI;

public class RhymingWordsPane extends JOptionPane
{
    public static JTextField rhymingWordTF = new JTextField("Enter Word");
    public static JDialog rhymingDialog;
    public static String wordToParse;

    public RhymingWordsPane()
    {
        Object[] jopContent = {"Enter Word To Rhyme With", rhymingWordTF};

        JOptionPane creatorPane = new JOptionPane();
        creatorPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        creatorPane.setMessage(jopContent);
        rhymingDialog = creatorPane.createDialog(null, "Rhyme Retriever");

        rhymingWordTF.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                rhymingWordTF.setText("");
            }
        });

        rhymingDialog.setVisible(true);

        wordToParse = rhymingWordTF.getText();

        if(creatorPane.getValue().equals(0))
        {
            rhymingWordTF.setText("Enter Word");
            rhymingDialog.dispose();
            //hitAPI(wordToParse);
            hitXmlAPI(wordToParse);
        }
        else
        {
            System.out.println("Not Retrieving Rhyming Word");
        }
    }
}