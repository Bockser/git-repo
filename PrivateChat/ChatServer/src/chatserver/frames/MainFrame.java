package chatserver.frames;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;


import java.awt.*;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;
import java.util.Vector;

import static chatserver.helpers.ChatConstans.MAIN_FRAME_ID;
import static chatserver.helpers.ChatConstans.SETTING_FRAME_ID;

/**
 * Created by Администратор on 01.12.2016.
 */
public class MainFrame extends JFrame {


    private final ServerManager manager;

    private static MainFrame instance = null;
    private final String FRAME_TITLE = "Private Chat v0.1";

    private JTextArea log = new JTextArea();
    private String[] columnNames = {"sessionID", "userName", "userAdress", "sessionTime"};
    private String[][] data= {
            {"1", "I am", "localhost", "1000"}};

    DefaultTableModel myModel;
    private JTable tableInfo;

    public static void initInstance(ServerManager manager) {
        instance = new MainFrame(manager);
    }

    public static MainFrame getInstance() {
        try {
            if (instance == null) throw new NullPointerException();
        } catch (NullPointerException ex) {
            ex.toString();
            System.err.println("Instance must be initialize by metod initInstance(ServerManager manager)");
        }
        return instance;
    }

    private MainFrame(ServerManager manager) {
        this.manager = manager;
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

        Vector<String> headerTable = new Vector<>();

        headerTable.add(columnNames[0]);
        headerTable.add(columnNames[1]);
        headerTable.add(columnNames[2]);
        headerTable.add(columnNames[3]);
        myModel = new DefaultTableModel(headerTable, 0);
        tableInfo = new JTable();
        tableInfo.setModel(myModel);
        tableInfo.setShowGrid(false);
        JScrollPane tableScroll = new JScrollPane(tableInfo);
        add(BorderLayout.WEST, tableScroll);
    }

    private void initLog() {
        JScrollPane scrollLog = new JScrollPane(log);
        log.setAutoscrolls(true);
        log.setEditable(false);
        add(BorderLayout.CENTER, scrollLog);
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
        log.append(s+"\n");
    }

    public void addConnectionInfo(String[] connectionInfo) {
        Vector<String> newRow = new Vector<>();
        newRow.add(connectionInfo[0]);
        newRow.add(connectionInfo[1]);
        newRow.add(connectionInfo[2]);
        newRow.add(connectionInfo[3]);
        myModel.getDataVector().add(newRow);
        myModel.newRowsAdded(new TableModelEvent(myModel));
    }

}
