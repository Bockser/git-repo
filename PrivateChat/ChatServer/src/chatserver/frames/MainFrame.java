package chatserver.frames;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;

import static chatserver.helpers.ChatConstans.MAIN_FRAME_ID;
import static chatserver.helpers.ChatConstans.SETTING_FRAME_ID;

/**
 * Created by Администратор on 01.12.2016.
 */
public class MainFrame extends AbstractFrame {

    private final String FRAME_TITLE = "Private Chat v0.1";

    private JTextArea log = new JTextArea();
    private String[] columnNames = {"sessionID", "userName", "userAdress", "sessionTime"};
    private String[][] data= {
            {"1", "I am", "localhost", "1000"},
            {"1", "I am", "localhost", "1000"},
            {"1", "I am", "localhost", "1000"},
            {"1", "I am", "localhost", "1000"}};

    private JTable tableInfo;


    public MainFrame(FrameManager manager) {
        super(manager, MAIN_FRAME_ID);

        setTitle(FRAME_TITLE);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setSize(800, 500);
        setResizable(false);
        setLocation(200, 200);

        initMenuBar();
        initLog();
        initTableInfo();

        setVisible(false);

    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu serverMenu = new JMenu("Server");
        JMenuItem startServer = new JMenuItem("start server");
        JMenuItem stopServer = new JMenuItem("stop server");
        JMenuItem settingServer = new JMenuItem("setting");
        JMenuItem exitServer = new JMenuItem("exit");

        startServer.addActionListener(e -> manager.startServer());
        serverMenu.add(startServer);

        stopServer.addActionListener(e -> manager.stopServer());
        serverMenu.add(stopServer);
        serverMenu.add(settingServer);
        settingServer.addActionListener(e -> manager.showFrame(SETTING_FRAME_ID));

        exitServer.addActionListener(e -> manager.exit());
        serverMenu.add(exitServer);
        menuBar.add(serverMenu);


        add(BorderLayout.NORTH, menuBar);
    }

    private void initTableInfo() {

        tableInfo = new JTable(data, columnNames);
        tableInfo.setShowGrid(false);
        JScrollPane tableScroll = new JScrollPane(tableInfo);
        add(BorderLayout.WEST, tableScroll);
    }

    private void initLog() {
        log.setAutoscrolls(true);
        log.setEditable(false);
        add(BorderLayout.CENTER, log);
        addLog("Application was start\n");
    }

    private void appendDate() {
        Locale locale = new Locale("ru", "RU");
        Calendar date = Calendar.getInstance(locale);
        Formatter f = new Formatter(locale);
        log.append(f.format("%tT: ",  date).toString());
     }
    public void addLog(String s) {
        appendDate();
        log.append(s);
        log.append("\n");
    }

}
