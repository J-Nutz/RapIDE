/*
 * Created by Jonah on 8/29/2015.
 */

import java.util.Scanner;

public class ChangelogHelper
{
    public static void main(String[] args)
    {
        System.out.println("Change Log Help Online");

        //Single JFrame
        //   ___________________________
        //  |                           |
        //  | |ChangeLog| |Date| |Vers| |
        //  |                           |
        //  |         |Submit|          |
        //  |___________________________|

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("Your Input Is: " + input);
    }
}