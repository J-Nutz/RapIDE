package panes.settingsPanes;

import local.Strings;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/*
 * Created by Jonah on 10/12/2015.
 */

public class SetRhymingWordsPane
{
    public static JTextField numOfRWTF;
    public static JComboBox<String> apiList;

    public static Properties rwProps = new Properties();
    public static OutputStream rwPropsOutput = null;

    public static String numOfRW;

    public SetRhymingWordsPane()
    {
        numOfRWTF = new JTextField();
        apiList = new JComboBox<>();

        try
        {
            rwPropsOutput = new FileOutputStream(Strings.pathToProps + "rwProps.properties");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

        JOptionPane setRWPane = new JOptionPane(JOptionPane.OK_CANCEL_OPTION);
        JDialog setRWDialog;

        Object[] rwContents = {"Choose Which API To Use", apiList, "Choose How Many Results To Get", numOfRWTF};

        String[] apis = {"Stands4", "Datamuse", "Rhymebrain"};
        for(String api : apis)
        {
            apiList.addItem(api);
        }

        setRWPane.setMessage(rwContents);
        setRWPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        setRWDialog = setRWPane.createDialog(null, "Font Settings");
        setRWDialog.setVisible(true);

        if(setRWPane.getValue().equals(0))
        {
            numOfRW = numOfRWTF.getText();
            String chosenAPI = apiList.getSelectedItem().toString();
            String finalAPI;

            switch(chosenAPI)
            {
                case "Stands4":
                    finalAPI = "0";
                    break;
                case "Datamuse":
                    finalAPI = "1";
                    break;
                case "Rhymebrain":
                    finalAPI = "2";
                    break;
                default:
                    finalAPI = "1";
                    break;
            }

            saveRwProps(finalAPI, numOfRW);
        }
    }

    public void saveRwProps(String selectedAPI, String selectedQueries)
    {
        rwProps.put("Selected API", selectedAPI);
        rwProps.put("Num Of Queries", selectedQueries);

        try
        {
            rwProps.store(rwPropsOutput, Strings.pathToProps + "rwProps.properties");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}