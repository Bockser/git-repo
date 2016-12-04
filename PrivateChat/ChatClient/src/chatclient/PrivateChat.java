package chatclient;

import static chatclient.helpers.Constans.*;

import chatclient.frames.ChatClient;
import chatclient.frames.LogonFrame;
import chatclient.frames.MainFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Администратор on 02.12.2016.
 */
public class PrivateChat implements ChatClient, Runnable{


    private MainFrame mainFrame;
    private LogonFrame logonFrame;


    private InetAddress host;
    private BufferedReader in;
    private PrintWriter out;
    private String name = "";
    private boolean exitClient = false;





    public PrivateChat () {
        mainFrame = new MainFrame(this);
        logonFrame = new LogonFrame(this);

    }

    public static void main(String[] args) {
        new PrivateChat();
    }

    public void connect() {
        Thread client = new Thread(this);
        client.start();
    }

    @Override
    public void run() {
        Socket s = null;
        try {
            s = new Socket(host, 12321);
            out = new PrintWriter(s.getOutputStream(), true);
            System.out.println(AUTH_COMMAND + "|" + name);
            out.println(AUTH_COMMAND + "|" + name);
            while (!exitClient) {}
        } catch (IOException e) {
            logonFrame.showFrame(true);
            mainFrame.setVisible(false);
        }
        finally {
            try {
                if (s != null) {
                    out.println(EXIT_COMMAND);
                    s.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void connect(InetAddress host, String username) {
        this.host = host;
        this.name = username;
        logonFrame.setVisible(false);
        mainFrame.setVisible(true);
        Thread chat = new Thread(this);
        chat.start();
    }

    private class Resender extends Thread {

        private boolean stop = false;

        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {

            try {
                while (!stop) {
                    String[] command = in.readLine().split("|");
                    switch (command[0]) {
                        case PUBLIC_MESSAGE:
                            mainFrame.showPublicMessage(command[1], command[2]);
                            break;
                        case PRIVATE_MESSAGE:
                            mainFrame.showPrivateMessage(command[1], command[2]);
                            break;
                        default:
                            break;
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
