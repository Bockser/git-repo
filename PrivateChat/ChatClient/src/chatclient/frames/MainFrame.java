package chatclient.frames;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowListener;
import java.util.ArrayList;

/**
 * Created by Администратор on 02.12.2016.
 */
public class MainFrame extends JFrame {

    private ChatClient chatClient;

    private JList listOnlineUser;
    private JTabbedPane messageTabbedPane;
    private ArrayList<JPanel> messagePanels;

    private GridBagConstraints c;


    public MainFrame(ChatClient chatClient) {
        this.chatClient = chatClient;
        setTitle("123");
        setLocation(200,200);
        setSize(700, 500);
        setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        initListUser();
        initMessageTabbedPane();


    }

    private void initListUser() {
        listOnlineUser = new JList(new String[]{"123", "123", "123"});

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 5;
        add(listOnlineUser, c);
    }

    private void initMessageTabbedPane() {
        messagePanels = new ArrayList<JPanel>();
        messageTabbedPane = new JTabbedPane();
    }

    public void displayPublicMessage(String seller, String message) {
    }

    public void displayPrivateMessage(String name, String message) {
    }
}
