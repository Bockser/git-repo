package chatclient.frames;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static chatclient.helpers.ChatConstans.MAIN_FRAME_ID;
import static chatclient.helpers.ChatConstans.SETTING_FRAME_ID;

/**
 * Created by Администратор on 01.12.2016.
 */
public class MainFrame extends AbstractFrame {

    private final String FRAME_TITLE = "Private Chat v0.1";

    public MainFrame(FrameManager manager) {
        super(manager, MAIN_FRAME_ID);
        setTitle(FRAME_TITLE);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        setLocation(200, 200);
        Font font = new Font("Verdana", Font.PLAIN, 11);
        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(font);

        JMenu serverMenu = new JMenu("Server");

        JMenuItem startServer = new JMenuItem("start server");
        JMenuItem stopServer = new JMenuItem("stop server");
        JMenuItem settingServer = new JMenuItem("setting");
        JMenuItem exitServer = new JMenuItem("exit");

        serverMenu.add(startServer);
        serverMenu.add(stopServer);
        serverMenu.add(settingServer);
        settingServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.showFrame(SETTING_FRAME_ID);
            }
        });

        exitServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.exit();
            }
        });
        serverMenu.add(exitServer);


        menuBar.add(serverMenu);

        add(BorderLayout.NORTH, menuBar);
        setVisible(false);
    }
}
