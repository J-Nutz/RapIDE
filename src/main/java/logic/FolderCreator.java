package logic;

/*
 * Created by Jonah on 7/31/2015.
 */

import local.Strings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FolderCreator
{
    public static void createFolders()
    {
        File savesFTP = new File(Strings.pathToSaves);
        Path savesPath = savesFTP.toPath();

        File xmlFTP = new File(Strings.pathToXML);
        Path xmlPath = xmlFTP.toPath();

        try
        {
            Files.createDirectories(savesPath);
            Files.createDirectories(xmlPath);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}