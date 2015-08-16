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
    public static URLConnection APIcnctr = null;

    public static BufferedReader APIreader = null;

    public static void hitAPI(String SLinput)
    {
        try
        {
            slAPI = new URL("http://api.datamuse.com/words?sl=" + SLinput + "&max=50");
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        try
        {
            assert slAPI != null;
            APIcnctr = slAPI.openConnection();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            assert APIcnctr != null;
            APIreader = new BufferedReader(new InputStreamReader(
                    APIcnctr.getInputStream()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            assert APIreader != null;
            while ((inputLine = APIreader.readLine()) != null)
            {
                parseJSON(inputLine);
            }
            try
            {
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