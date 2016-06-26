package frames;

import handlers.keyBindings.HookKB;
import handlers.keyBindings.SavingKB;
import handlers.menus.*;
import handlers.menus.menuItems.settings.AppearanceMIH;
import handlers.menus.menuItems.settings.FontMIH;
import handlers.menus.menuItems.settings.RhymingWordsMIH;
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
import static logic.setters.SettingsSetter.setSettings;
import static panes.FileDeleterPane.savesComboBox;

/*
 * Created by Jonah on 7/2/2015.
 */

public class MainFrame extends JFrame
{
    //Declarations
    public static JPanel mPanel;

    public static JList<String> rhymeListDisplay;

    public static JTextArea mTextArea;

    public static JSeparator separator;

    public static JMenuBar mMenuBar;
    public static JMenu mCreateFile;
    public static JMenu mSoundsLike;
    public static JMenu mFileDeleter;
    public static JMenu mSave;
    public static JMenu mOpenFile;
    public static JMenu mRenameFile;
    public static JMenu mSettings;
    public static JMenuItem sFont;
    public static JMenuItem sRhymingWords;
    public static JMenuItem sColors;

    private JPanel bottomPanel;
    private JButton rlClearBtn;

    public static DefaultListModel<String> rhymeList;

    public JScrollPane slScrollPane;

    public static String[] defaultList = {"Press", "The", "Sounds", "Like", "Button", "To", "Search", "For", "List",
                                          "Of", "Words", "That", "Sound", "Similar", "To", "Each", "Other"};
    public MainFrame()
    {
        //New Stuff
        mPanel = new JPanel();
        bottomPanel = new JPanel();

        mTextArea = new JTextArea();

        separator = new JSeparator(SwingConstants.VERTICAL);

        mMenuBar = new JMenuBar();

        mCreateFile = new JMenu("Create New File");
        mSoundsLike = new JMenu("Sounds Like");
        mFileDeleter = new JMenu("Delete Files");
        mSave = new JMenu("Save");
        mOpenFile = new JMenu("Open File");
        mRenameFile = new JMenu("Rename File");
        mSettings = new JMenu("Settings");
        sFont = new JMenuItem("Fonts");
        sRhymingWords = new JMenuItem("Rhyming Words");
        sColors = new JMenuItem("Appearance");

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
        RhymingWordsMIH.RWMIH();
        AppearanceMIH.AMIH();

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

        setSettings();

        beforeExit();

        //long endTime = System.nanoTime();
        //long totalTime = (endTime - startTime);

        //System.out.println("Loaded In " + totalTime / 1000000 + " Milliseconds");
    }

    private void createView()
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Image mIcon = toolkit.getImage("C:\\Users\\Jonah\\Desktop\\RapIDELogo.png"); //Get Better Image

        //Main Frame
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setTitle("Rap IDE");
        setIconImage(mIcon);

        //Main Panel
        mPanel.setBackground(Color.darkGray);
        mPanel.setLayout(new BorderLayout(5, 5));

        //Add Everything
        getContentPane().add(mPanel);
        mPanel.add(mMenuBar, BorderLayout.PAGE_START);
        mPanel.add(Box.createHorizontalStrut(1), BorderLayout.LINE_START);
        mPanel.add(mTextArea, BorderLayout.CENTER);
        mPanel.add(slScrollPane, BorderLayout.LINE_END);

        //Bottom Panel
        //bottomPanel.setPreferredSize(new Dimension(screenSize.width, 125));
        //bottomPanel.add(rlClearBtn);

        //mPanel.add(bottomPanel, BorderLayout.SOUTH);

        //Menu Stuff
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border border2 = BorderFactory.createMatteBorder(2, 1, 2, 1, Color.BLACK);
        separator.setPreferredSize(new Dimension(2, 2));

        mMenuBar.add(mSave);
        mSave.setBorder(border2);
        mSave.add(new JSeparator(SwingConstants.VERTICAL));
        mMenuBar.add(mOpenFile);
        mOpenFile.setBorder(border2);
        mMenuBar.add(mCreateFile);
        mCreateFile.setBorder(border2);
        mMenuBar.add(mRenameFile);
        mRenameFile.setBorder(border2);
        mMenuBar.add(mFileDeleter);
        mFileDeleter.setBorder(border2);
        mMenuBar.add(mSoundsLike);
        mSoundsLike.setBorder(border2);
        mMenuBar.add(mSettings);
        mSettings.setBorder(border2);
        mSettings.add(sFont);
        mSettings.addSeparator();
        mSettings.add(sRhymingWords);
        mSettings.addSeparator();
        mSettings.add(sColors);

        //MenuBar Shenanigans
        mMenuBar.setBackground(Color.white);
        mMenuBar.setBorderPainted(false);

        //Menu Item Shenanigans
        //ImageIcon saveIcon = new ImageIcon("C:\\Users\\Jonah\\Desktop\\saveLogo.png");
        //mSave.setIcon(saveIcon);

        //TextArea Shenanigans
        mTextArea.setLineWrap(true);
        mTextArea.setWrapStyleWord(true);
        mTextArea.setBackground(Color.darkGray);
        mTextArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        mTextArea.setFont(new Font("Courier New", Font.PLAIN, 16));
        mTextArea.setForeground(Color.black);

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
        for(String rhyme : defaultList)
        {
            rhymeList.addElement(rhyme);
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

    private void beforeExit()
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