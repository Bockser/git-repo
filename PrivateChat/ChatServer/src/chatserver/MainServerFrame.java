package chatserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by Bockser on 30.11.2016.
 */
public class MainServerFrame extends JFrame{

    //Константы окна
    private final String MAIN_FRAME_TITLE = "Server of PrivateChat";
    private final int MAIN_FRAME_WIDTH = 400;
    private final int MAIN_FRAME_HEIGHT = 300;
    private final int MAIN_LOC_FRAME_X = 400;
    private final int MAIN_LOC_FRAME_Y = 300;

    JTextArea messageBox;
    Server server;

    public static void main(String[] args) {

        new MainServerFrame().antistatic();

    }

    public void antistatic() {
        initGUI();
        server = new Server();
        server.start();
    }

    private void initGUI() {
        setTitle(MAIN_FRAME_TITLE);
        setLocation(MAIN_LOC_FRAME_X, MAIN_LOC_FRAME_Y);
        setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        messageBox = new JTextArea(MAIN_FRAME_HEIGHT/30, 50);
        JScrollPane spMessaageBox = new JScrollPane(messageBox);
        spMessaageBox.setLocation(0,0);
        messageBox.setLineWrap(true);

        JButton btnFinish = new JButton();
        btnFinish.setText("Finish!");
        btnFinish.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                server.finish();
            }
        });
        add(BorderLayout.NORTH, messageBox);
        add(BorderLayout.SOUTH, btnFinish);
        setVisible(true);
    }


    public class Server extends Thread {

        private boolean bFinish = false;

        @Override
        public void run() {
            System.out.println("Thread run!");

            ServerSocket serverSocket = null;
            //Socket socket = new Socket();
            try {
                serverSocket = new ServerSocket(12321);
                System.out.println("Socket open");

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (!bFinish) {
                Calendar cal = Calendar.getInstance();
                Formatter formatter = new Formatter(new Locale("ru", "RU"));
                messageBox.append(formatter.format("%tT", cal).toString() + ": " + "abstract messa!\n");
                messageBox.setCaretPosition(messageBox.getText().length());
            }
            System.out.println("I am stoped");
        }

        public void finish() {
            bFinish = true;
        }
    }
}
