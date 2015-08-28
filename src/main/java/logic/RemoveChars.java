package logic;

/*
 * Created by Jonah on 8/27/2015.
 */

public class RemoveChars
{
    public static String removeBannedChars(String wordToEdit)
    {
        char[] bannedCharArray = {'/', ':', '*', '?', '<', '>', '|', '"'};
        for (char charToRemove : bannedCharArray)
        {
            wordToEdit = wordToEdit.replace("" + charToRemove, "");
        }

        return wordToEdit;
    }
}