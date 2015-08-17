package frames;

import handlers.menuItems.FileDeleterMIH;
import handlers.menuItems.SavingMIH;
import keyBindings.HookKB;
import keyBindings.SavingKB;
import handlers.menuItems.FileNameMIH;
import handlers.menuItems.SoundsLikeMIH;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static logic.FolderCreator.createFolders;

/*
 * Created by Jonah on 7/2/2015.
 */

public class MainFrame extends JFrame
{
    //Declarations
    private JPanel mPanel;

    private JList<String> rhymeListDisplay;

    public static JTextArea mTextArea;

    private JMenuBar mMenuBar;
    public static JMenu mFileName;
    public static JMenu mSoundsLike;
    public static JMenu mFileDeleter;
    public static JMenu mSave;

    public static DefaultListModel<String> rhymeList;

    public JScrollPane slScrollPane;

    public static Color mpColor = new Color(88, 88, 88);

    public static String[] rhymes = {"Press", "The", "Sounds", "Like", "Button", "To", "Search", "For", "List",
                                     "Of", "Words", "That", "Sound", "Similar", "To", "Each", "Other"};

    public MainFrame()
    {
        //New Stuff
        mPanel = new JPanel();

        mTextArea = new JTextArea();

        mMenuBar = new JMenuBar();

        mFileName = new JMenu("File Name");
        mSoundsLike = new JMenu("Sounds Like");
        mFileDeleter = new JMenu("Delete Files");
        mSave = new JMenu("Save");

        rhymeList = new DefaultListModel<>();
        rhymeListDisplay = new JList<>(rhymeList);

        slScrollPane = new JScrollPane(rhymeListDisplay);

        //Pre Init
        createFolders();

        //Init
        createView();

        FileNameMIH.FNMIH();
        SoundsLikeMIH.SLMIH();
        FileDeleterMIH.FDMIH();
        SavingMIH.SMIH();

        HookKB.hookBinding();
        SavingKB.savingBinding();

        //Post Init
        setInitialRList();
        lookAndFeel();

        SwingUtilities.invokeLater(FileLoaderFrame::new);
    }

    private void createView()
    {
        //Main Frame
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setTitle("Rap IDE");

        //Main Panel
        mPanel.setBackground(mpColor);
        mPanel.setLayout(new BorderLayout(5, 5));

        //Add Everything
        getContentPane().add(mPanel);

        mPanel.add(mMenuBar, BorderLayout.PAGE_START);
        mPanel.add(Box.createHorizontalStrut(1), BorderLayout.LINE_START);
        mPanel.add(mTextArea, BorderLayout.CENTER);
        mPanel.add(slScrollPane, BorderLayout.LINE_END);

        mMenuBar.add(mFileDeleter);
        mMenuBar.add(mFileName);
        mMenuBar.add(mSoundsLike);
        mMenuBar.add(mSave);

        //TextArea Shenanigans
        mTextArea.setLineWrap(true);
        mTextArea.setWrapStyleWord(true);
        mTextArea.setBackground(mpColor);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        mTextArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        //RhymeList Shenanigans
        rhymeListDisplay.setFixedCellWidth(100);
        rhymeListDisplay.setFixedCellHeight(30);
        rhymeListDisplay.setFont(new Font("Courier New", Font.PLAIN, 15));

        //slScrollPane Shenanigans
        slScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        slScrollPane.setBorder(null);

        pack();
        setVisible(true);
    }

    public static void setInitialRList()
    {
        for(String h : rhymes)
        {
            rhymeList.addElement(h);
        }
    }

    private static void lookAndFeel()
    {
        /*SynthLookAndFeel laf = new SynthLookAndFeel();
        try
        {
            laf.load(MainFrame.class.getResourceAsStream("SynthLAF.RapIDE.xml"), MainFrame.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            UIManager.setLookAndFeel(laf);
        }
        catch (UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }*/
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}