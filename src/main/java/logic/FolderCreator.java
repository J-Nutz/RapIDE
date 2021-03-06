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

        File propFTP = new File(Strings.pathToProps);
        Path propPath = propFTP.toPath();

        try
        {
            Files.createDirectories(savesPath);
            Files.createDirectories(xmlPath);
            Files.createDirectories(propPath);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}