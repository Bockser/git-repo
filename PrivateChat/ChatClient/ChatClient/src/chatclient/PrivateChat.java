package chatclient;

import chatclient.frames.FrameManager;
import chatclient.frames.MainFrame;
import sun.applet.Main;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Администратор on 02.12.2016.
 */
public class PrivateChat implements FrameManager{

    private MainFrame mainFrame;
    public PrivateChat () {
        mainFrame = new MainFrame(this);
        try {
            Socket s = new Socket("localhost", 12321);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new PrivateChat();
    }
}
