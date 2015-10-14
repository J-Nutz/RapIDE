package logic;

/*
 * Created by Jonah on 7/28/2015.
 */

import local.Strings;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import static logic.JSONparser.parseJSON;

public class APIhitter
{
    public static String inputLine;
    public static int api;
    public static String queries = "50";

    public static URL slAPI = null;
    public static URLConnection APIcnctr;

    public static BufferedReader APIreader;

    public static Properties rwProps = new Properties();

    public static void loadAPIProps()
    {
        try
        {
            rwProps.load(new FileInputStream(Strings.pathToProps + "rwProps.properties"));

            api = Integer.parseInt(rwProps.getProperty("Selected API", "0"));
            queries = rwProps.getProperty("Num Of Queries", "50");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void hitAPI(String SLinput)
    {
        loadAPIProps();

        try
        {
            if(api == 1)
            {
                slAPI = new URL("http://api.datamuse.com/words?sl=" + SLinput + "&max=" + queries);  //API #1
            }
            else if(api == 2)
            {
                slAPI = new URL("http://rhymebrain.com/talk?function=getRhymes&word=" + SLinput + "&maxResults=" + queries); //API #2
            }
            else
            {
                System.out.println("No API Chosen");
            }
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
            if(api == 1)
            {
                while((inputLine = APIreader.readLine()) != null)
                {
                    parseJSON(inputLine);
                }
            }
            else if(api == 2)
            {
                while((inputLine = APIreader.readLine()) != null)
                {
                    toInput = toInput + inputLine;
                }
            }
            else
            {
                System.out.println("No API Chosen");
            }
            try
            {
                if(api == 2)
                {
                    parseJSON(toInput);
                }
                APIreader.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}