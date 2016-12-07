package chatclient;

import static chatclient.helpers.Constans.*;

import chatclient.frames.ChatClient;
import chatclient.frames.LogonFrame;
import chatclient.frames.MainFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        //mainFrame.setVisible(true);
        logonFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new PrivateChat();
    }

    @Override
    public void run() {
        Socket s = null;
        try {
            s = new Socket(host, 12321);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);
            out.println(AUTH_COMMAND);
            out.println(name);
            Resender resender = new Resender();
            resender.start();
            while (!exitClient) { }
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

    @Override
    public void disconnect() {

    }

    @Override
    public String[] getUserList() {
        return new String[0];
    }

    @Override
    public String getMyName() {
        return name;
    }

    @Override
    public void sendPublicMessage(String message) {
        out.println(PUBLIC_MESSAGE);
        out.println(name);
        out.println(message);
    }

    @Override
    public void sendPrivateMessage(String addressee, String message) {
        out.println(PRIVATE_MESSAGE);
        out.println(addressee);
        out.println(message);
    }

    @Override
    public void sendCommand(String command, String[] args) {

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
                    waitCommand();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void waitCommand() throws IOException {
            String command = in.readLine();
            String name = in.readLine();
            String message = in.readLine();
            switch (command) {
                case PUBLIC_MESSAGE:
                    mainFrame.displayPublicMessage(name, message);
                    break;
                case PRIVATE_MESSAGE:
                    mainFrame.displayPrivateMessage(name, message);
                default:
                    break;
            }
        }
    }
}
