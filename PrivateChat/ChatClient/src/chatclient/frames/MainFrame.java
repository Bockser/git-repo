package chatclient.frames;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.util.*;

import static chatclient.helpers.Constans.*;


/**
 * Created by Администратор on 02.12.2016.
 */
public class MainFrame extends JFrame {

    private ChatClient chatClient;

    private JList listOnlineUser;
    private JTabbedPane messageTabbedPane;
    private ArrayList<JPanel> messagePanels;
    private HashMap<String, JTextArea> messageAreas;
    private GridBagConstraints c;
    private JTextField enteredMessage;
    private JButton btnSend;


    public MainFrame(ChatClient chatClient) {
        this.chatClient = chatClient;
        setTitle("123");
        setLocation(200,200);
        setSize(900, 500);
        setVisible(false);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());
        c = new GridBagConstraints();

        initListUser();
        initMessageTabbedPane();

        btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String message = enteredMessage.getText();

                if (!message.matches("(\\s)*")) {
                    chatClient.sendPublicMessage(message);
                    enteredMessage.setText("");

                }
            }
        });
        c.anchor = GridBagConstraints.EAST;
        c.gridwidth = 1;
        c.ipadx = 80;
        c.ipady = 30;
        c.gridx = 1;
        c.gridy = 1;
        add(btnSend,  c);

        enteredMessage = new JTextField();
        enteredMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    sendMessage();
            }
        });
        c.anchor = GridBagConstraints.SOUTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.insets = new Insets(10,10,10,0);
        c.ipady = 30;
        c.ipadx = 560;
        c.gridx = 0;
        c.gridy = 1;
        add(enteredMessage,  c);
    }

    private void sendMessage() {
        String message = enteredMessage.getText();
        JTextArea selectedArea = messageAreas.get(messageTabbedPane.getTitleAt(messageTabbedPane.getSelectedIndex()));
        if (!message.matches("(\\s)*")) {
            String addresse = messageTabbedPane.getTitleAt(messageTabbedPane.getSelectedIndex());

            if (!addresse.equals("Public messages")) {
                Locale locale = new Locale("ru", "RU");
                Calendar date = Calendar.getInstance(locale);
                Formatter f = new Formatter(locale);
                StringBuilder sb = new StringBuilder();
                sb.append(f.format("%tT ", date).toString());
                sb.append(chatClient.getMyName());
                sb.append(": ");
                sb.append(message);
                sb.append("\n");
                selectedArea.append(sb.toString());
                chatClient.sendPrivateMessage(addresse, message);
            } else
                chatClient.sendPublicMessage(message);
            enteredMessage.setText("");
        }
    }

    private void initListUser() {
        listOnlineUser = new JList(new String[]{"user1", "user2", "user3"});
        listOnlineUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    String addressePri = listOnlineUser.getSelectedValue().toString();
                    boolean tabExists = false;
                    int i = 0;
                    for (i = 0; i < messageTabbedPane.getTabCount(); i++) {
                        if (messageTabbedPane.getTitleAt(i).equals(addressePri)) {
                            tabExists = true;
                            break;
                        }
                    }
                    if (!tabExists) {
                        JTextArea tempTextArea = new JTextArea();
                        JScrollPane scrollPane = new JScrollPane(tempTextArea);
                        tempTextArea.setEditable(false);
                        messageAreas.put(addressePri, tempTextArea);
                        messageTabbedPane.add(addressePri, scrollPane);
                        i = messageTabbedPane.getTabCount() - 1;
                    }
                    messageTabbedPane.setSelectedIndex(i);
                }
            }
        });
        c.fill = GridBagConstraints.VERTICAL;
        c.gridwidth = 1;
        c.insets = new Insets(10,10,0,10);
        c.ipadx = 100;
        c.gridx = 2;
        c.gridy = 0;
        add(listOnlineUser, c);
    }

    private void initMessageTabbedPane() {

        messageAreas = new HashMap<>();
        JTextArea tempTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(tempTextArea);
        tempTextArea.setEditable(false);
        messageAreas.put("Public", tempTextArea);

        messageTabbedPane = new JTabbedPane();
        messageTabbedPane.add("Public messages", scrollPane);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(10,10,0,10);
        c.gridwidth = 2;
        c.ipadx = 600;
        c.ipady = 300;
        c.gridx = 0;
        c.gridy = 0;
        add(messageTabbedPane, c);
    }

    public void displayPublicMessage(String seller, String message) {
        Locale locale = new Locale("ru", "RU");
        Calendar date = Calendar.getInstance(locale);
        Formatter f = new Formatter(locale);
        StringBuilder sb = new StringBuilder();
        sb.append(f.format("%tT ",  date).toString());
        sb.append(seller);
        sb.append(": ");
        sb.append(message);
        sb.append("\n");
        JTextArea tempTextArea = messageAreas.get("Public");
        tempTextArea.append(sb.toString());
    }

    public void displayPrivateMessage(String name, String message) {
        boolean tabExists = false;
        int i = 0;
        for (i = 0; i < messageTabbedPane.getTabCount(); i++) {
            if (messageTabbedPane.getTitleAt(i).equals(name)) {
                tabExists = true;
                break;
            }
        }
        if (!tabExists) {
            JTextArea tempTextArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(tempTextArea);
            tempTextArea.setEditable(false);
            messageAreas.put(name, tempTextArea);
            messageTabbedPane.add(name, scrollPane);
            i = messageTabbedPane.getTabCount() - 1;
        }
        Locale locale = new Locale("ru", "RU");
        Calendar date = Calendar.getInstance(locale);
        Formatter f = new Formatter(locale);
        StringBuilder sb = new StringBuilder();
        sb.append(f.format("%tT ",  date).toString());
        sb.append(name);
        sb.append(": ");
        sb.append(message);
        sb.append("\n");
        JTextArea tempTextArea = messageAreas.get(name);

        //messageTabbedPane.setSelectedIndex(i);
        tempTextArea.append(sb.toString());
    }

    public void updateUsersList(String[] users) {
        listOnlineUser.setListData(users);
    }
}
