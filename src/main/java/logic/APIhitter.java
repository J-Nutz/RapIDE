package logic;

/*
 * Created by Jonah on 7/28/2015.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static logic.JSONparser.parseJSON;

public class APIhitter
{
    public static String inputLine;

    public static URL slAPI = null;
    public static URLConnection APIcnctr;

    public static BufferedReader APIreader;

    public static void hitAPI(String SLinput)
    {
        try
        {
            //slAPI = new URL("http://api.datamuse.com/words?sl=" + SLinput + "&max=50");  //API #1
            slAPI = new URL("http://rhymebrain.com/talk?function=getRhymes&word=" + SLinput + "&maxResults=50"); //API #2
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        try
        {
            APIcnctr = slAPI.openConnection();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            APIreader = new BufferedReader(new InputStreamReader(
                    APIcnctr.getInputStream()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        String toInput = "";

        try
        {
            while ((inputLine = APIreader.readLine()) != null)
            {
                toInput = toInput + inputLine; //Use if using API #2
                //parseJSON(inputLine);  //Use if using API #1
            }
            try
            {
                parseJSON(toInput); //Use if using API #2
                APIreader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}