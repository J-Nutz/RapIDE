package logic;

/*
 * Created by Jonah on 7/28/2015.
 */

import local.Strings;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static logic.XMLparser.parseXML;

public class XmlApiHitter
{
    public static String inputLine;

    public static URL slAPI = null;
    public static URLConnection APIcnctr;

    public static BufferedReader APIreader;

    public static void hitXmlAPI(String SLinput)
    {
        try
        {
            slAPI = new URL("http://www.stands4.com/services/v2/rhymes.php?uid=4396&tokenid=7ok5aFMY8y0i0HP1&term=" + SLinput);
        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
        }
        try
        {
            APIcnctr = slAPI.openConnection();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            APIreader = new BufferedReader(new InputStreamReader(
                    APIcnctr.getInputStream()));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        String toInput = "";

        try
        {
            while((inputLine = APIreader.readLine()) != null)
            {
                toInput = toInput + inputLine;
            }
            try
            {
                FileWriter mFileWriter = new FileWriter(new File(Strings.pathToXML, "xmlSave.xml"));
                mFileWriter.write(toInput);
                mFileWriter.close();
                APIreader.close();
                parseXML();
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