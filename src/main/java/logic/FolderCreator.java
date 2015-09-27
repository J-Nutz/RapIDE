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
    public static File ftp = new File(Strings.pathToSaves);
    public static Path adDirPath = ftp.toPath();

    public static void createFolders()
    {
        try
        {
            Files.createDirectories(adDirPath);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (!Files.exists(adDirPath))
        {
            System.out.println("FolderCreator: Folder Not Created");
        }
    }
}