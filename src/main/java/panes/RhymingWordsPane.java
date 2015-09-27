package panes;

/*
 * Created by Jonah on 9/15/2015.
 */

import javax.swing.*;

import static logic.APIhitter.hitAPI;

public class RhymingWordsPane extends JOptionPane
{
    public static String wordToParse;

    public RhymingWordsPane()
    {
        wordToParse = JOptionPane.showInputDialog("Enter Word To Get Rhyming Words");

        if(wordToParse != null)
        {
            hitAPI(wordToParse);
        }
        else
        {
            System.out.println("No Word Entered");
        }
    }
}