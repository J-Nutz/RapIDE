package logic;

/*
 * Created by Jonah on 8/16/2015.
 */

import java.io.File;

public class GetFileList
{
    public static boolean hasSaves = false;

    public static void GetFiles()
    {
        String adDir = System.getenv("APPDATA");
        String pathToSaves = adDir + "\\RapIDE\\saves";

        File savesFolder = new File(pathToSaves);
        File[] listOfFiles = savesFolder.listFiles();

        if (listOfFiles != null)
        {
            hasSaves = true;
            /*for (File file : listOfFiles)
            {
                if (file.isFile())
                {
                    hasSaves = true;
                }
                else if (file.isDirectory())
                {
                    System.out.println("Fucked M8");
                }
            }*/
        }
    }

}
