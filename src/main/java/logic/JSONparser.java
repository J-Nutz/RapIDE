package logic;

/*
 * Created by Jonah on 7/28/2015.
 */

import frames.MainFrame;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONparser extends MainFrame
{
    public static String SLrtrn;
    public static String StringScore;
    public static int Score;

    public static void parseJSON(String JSONinput)
    {
        JSONArray JSONrtrn = new JSONArray(JSONinput);

        rhymeList.removeAllElements();

        for (int i = 0; i < JSONrtrn.length(); ++i)
        {
            JSONObject parsedObj = (JSONObject) JSONrtrn.get(i);
            SLrtrn = parsedObj.get("word").toString();

            StringScore = parsedObj.get("score").toString();
            //System.out.println(Score);

            Score = Integer.parseInt(StringScore);

            if(Score > 200)
            {
                rhymeList.addElement(SLrtrn);
            }
            else
            {
                rhymeList.addElement("No");
                rhymeList.addElement("Rhyming");
                rhymeList.addElement("Words");

                break;
            }
        }
    }
}