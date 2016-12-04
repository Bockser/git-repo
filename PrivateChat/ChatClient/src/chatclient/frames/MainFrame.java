package chatclient.frames;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowListener;

/**
 * Created by Администратор on 02.12.2016.
 */
public class MainFrame extends JFrame {

    private ChatClient chatClient;

    public MainFrame(ChatClient chatClient) {
        this.chatClient = chatClient;
        setTitle("123");
        setLocation(200,200);
        setSize(700, 500);
        setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        //panel.setSize(100,100);
        panel.setBorder(new EmptyBorder(20, 20,20, 20));
        //add(BorderLayout.SOUTH, panel);
        JTabbedPane messagePanels = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        //messagePanels.setBorder(new EmptyBorder(20, 20,20, 20));
        JPanel publicMessages = new JPanel();
        JTextArea publicMessagesArea = new JTextArea();
        publicMessages.add(BorderLayout.CENTER, publicMessagesArea);
        messagePanels.add("Public chat", publicMessages);
        add(BorderLayout.CENTER, messagePanels);


        JPanel panelUsers = new JPanel();
        //panelUsers.setBorder(new EmptyBorder(20, 20,20, 20));
        add(BorderLayout.EAST, panelUsers);

        String[] data = {"element 1", "element 2", "element 3"};
        JList listUsers = new JList(data);
        listUsers.setBounds(0, 0, 200 , 200);
        listUsers.setFixedCellWidth(200);
        panelUsers.add(BorderLayout.CENTER, listUsers);



        /*JPanel panel = new JPanel();
        panel.setSize(300,500);

        JList list = new JList(data);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setSize(200,500);

        add(BorderLayout.EAST, list);
        add(BorderLayout.WEST, panel);

        panel.setLayout(new BorderLayout());

        JTextArea ta1 = new JTextArea("123213");
        JTextArea ta2 = new JTextArea("qweqweqwe");
        ta1.setSize(300, 250);
        ta1.setSize(300, 250);

        panel.add(BorderLayout.NORTH, ta1);
        panel.add(BorderLayout.SOUTH, ta2);*/

        //pack();
    }

    private void initListUser() {


    }

    public void showPublicMessage(String seller, String message) {
    }

    public void showPrivateMessage(String seller, String message) {
    }


}
