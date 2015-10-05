package frames;

import handlers.keyBindings.HookKB;
import handlers.keyBindings.SavingKB;
import handlers.menus.*;
import handlers.menus.menuItems.settings.FontMIH;
import panes.FileCreatorPane;
import panes.FileDeleterPane;
import panes.FileLoaderPane;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static logic.FolderCreator.createFolders;
import static logic.Saving.save;
import static panes.FileDeleterPane.savesComboBox;

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
    public static JMenu mCreateFile;
    public static JMenu mSoundsLike;
    public static JMenu mFileDeleter;
    public static JMenu mSave;
    public static JMenu mOpenFile;
    public static JMenu mRenameFile;
    public static JMenu mSettings;
    public static JMenuItem sFont;

    private JButton rlClearBtn;

    public static DefaultListModel<String> rhymeList;

    public JScrollPane slScrollPane;

    public static Color mpColor = new Color(88, 88, 88);

    public static String[] defaultList = {"Press", "The", "Sounds", "Like", "Button", "To", "Search", "For", "List",
                                          "Of", "Words", "That", "Sound", "Similar", "To", "Each", "Other"};
    public MainFrame()
    {
        //New Stuff
        mPanel = new JPanel();

        mTextArea = new JTextArea();

        mMenuBar = new JMenuBar();

        mCreateFile = new JMenu("Create New File");
        mSoundsLike = new JMenu("Sounds Like");
        mFileDeleter = new JMenu("Delete Files");
        mSave = new JMenu("Save");
        mOpenFile = new JMenu("Open File");
        mRenameFile = new JMenu("Rename File");
        mSettings = new JMenu("Settings");
        sFont = new JMenuItem("Fonts");

        rhymeList = new DefaultListModel<>();
        rhymeListDisplay = new JList<>(rhymeList);

        slScrollPane = new JScrollPane(rhymeListDisplay);

        rlClearBtn = new JButton("Clear Rhyming Words");

        //long startTime = System.nanoTime();

        //Pre Init
        createFolders();

        //Init
        createView();

        FileCreatorMH.FCMH();
        SoundsLikeMH.SLMH();
        FileDeleterMH.FDMH();
        SavingMH.SMH();
        FileOpenerMH.FOMH();
        FileRenamerMH.FRMH();

        FontMIH.FMIH();

        HookKB.hookBinding();
        SavingKB.savingBinding();

        //Post Init
        setInitialRhymeList();
        lookAndFeel();

        FileDeleterPane.setSavesComboBox();
        if(savesComboBox.getItemCount() > 0)
        {
            SwingUtilities.invokeLater(FileLoaderPane::new);
        }
        else
        {
            SwingUtilities.invokeLater(FileCreatorPane::new);
        }

        beforeExit();

        //long endTime = System.nanoTime();
        //long totalTime = (endTime - startTime);

        //System.out.println("Loaded In " + totalTime / 1000000 + " Milliseconds");

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

        mPanel.add(rlClearBtn, BorderLayout.PAGE_END);

        mMenuBar.add(mSave);
        mMenuBar.add(mOpenFile);
        mMenuBar.add(mCreateFile);
        mMenuBar.add(mRenameFile);
        mMenuBar.add(mFileDeleter);
        mMenuBar.add(mSoundsLike);
        mMenuBar.add(mSettings);
        mSettings.add(sFont);

        //MenuBar Shenanigans
        mMenuBar.setBackground(Color.white);

        //Setting Shenanigans


        //Menu Item Shenanigans
        //ImageIcon saveIcon = new ImageIcon("C:\\Users\\Jonah\\Desktop\\saveLogo.png");
        //mSave.setIcon(saveIcon);

        //TextArea Shenanigans
        mTextArea.setLineWrap(true);
        mTextArea.setWrapStyleWord(true);
        mTextArea.setBackground(mpColor);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        mTextArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        mTextArea.setFont(new Font("Courier New", Font.PLAIN, 16));

        //RhymeList Shenanigans
        rhymeListDisplay.setFixedCellWidth(125);
        rhymeListDisplay.setFixedCellHeight(30);
        rhymeListDisplay.setFont(new Font("Courier New", Font.PLAIN, 15));
        rhymeListDisplay.setBackground(Color.lightGray);

        //slScrollPane Shenanigans
        slScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        slScrollPane.setBorder(null);

        //rlClearBtn Shenanigans
        rlClearBtn.setPreferredSize(new Dimension(100, 25));

        rlClearBtn.addActionListener(e -> {
            rhymeList.removeAllElements();
            setInitialRhymeList();
        });

        pack();
        setVisible(true);
    }

    public static void setInitialRhymeList()
    {
        for(String r : defaultList)
        {
            rhymeList.addElement(r);
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

    public void beforeExit()
    {
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent windowEvent)
            {
                try
                {
                    save();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}