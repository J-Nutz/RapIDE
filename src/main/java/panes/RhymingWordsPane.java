package panes;

/*
 * Created by Jonah on 9/15/2015.
 */

import logic.APIhitter;

import javax.swing.*;

public class RhymingWordsPane extends JOptionPane
{
    public static String wordToParse;

    public RhymingWordsPane()
    {
        wordToParse = JOptionPane.showInputDialog("Enter Word To Get Rhyming Words");

        if(wordToParse == null)
        {
            System.out.println("Da Faq?");
        } else
        {
            APIhitter.hitAPI(wordToParse);
        }
    }
}