package chatclient.frames;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Администратор on 02.12.2016.
 */
public class MainFrame extends JFrame {

    JList listUserl;
    private FrameManager manager;
    //private static MainFrame instance = new

    public MainFrame(FrameManager manager) {
        this.manager = manager;
        setTitle("123");
        setLocation(200,200);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panelMessage = new JPanel(new BorderLayout());
        add(BorderLayout.CENTER, panelMessage);
        JTextArea messageArea = new JTextArea(2, 10);
        messageArea.append("123");

        //JTextField enteredMessage = new JTextField("enteredtext", 10);
        //panelMessage.add(BorderLayout.SOUTH, enteredMessage);

        initListUser();
        String[] elements = {"element 1", "element 2", "element 3"};
        listUserl = new JList(elements);
        JScrollPane scrollListUser = new JScrollPane(listUserl);
        panelMessage.add(BorderLayout.EAST, scrollListUser);
        panelMessage.add(BorderLayout.WEST, messageArea);


    }

    private void initListUser() {


    }
}
