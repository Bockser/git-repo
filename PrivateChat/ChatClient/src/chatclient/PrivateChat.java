package chatclient;

import chatclient.frames.ChatClient;
import chatclient.frames.LogonFrame;
import chatclient.frames.MainFrame;

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
    private String name = "unname";

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
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            out.println("AUTH");
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (s != null)
                    s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void connect(InetAddress host, String username) {
        this.host = host;
        this.name = username;
        Thread chat = new Thread(this);
        chat.start();
    }
}
