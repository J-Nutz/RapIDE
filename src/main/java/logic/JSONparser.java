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

    public static void parseJSON(String JSONinput)
    {
        final JSONArray JSONrtrn = new JSONArray(JSONinput);

        rhymeList.removeAllElements();

        for (int i = 0; i < JSONrtrn.length(); ++i)
        {
            JSONObject parsedObj = (JSONObject) JSONrtrn.get(i);
            SLrtrn = parsedObj.get("word").toString();

            rhymeList.addElement(SLrtrn);
        }
    }
}