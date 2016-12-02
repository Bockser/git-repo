package chatclient;

import chatclient.frames.FrameManager;
import chatclient.frames.MainFrame;
import sun.applet.Main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.Socket;

/**
 * Created by Администратор on 02.12.2016.
 */
public class PrivateChat implements FrameManager{


    private MainFrame mainFrame;
    public PrivateChat () {
        mainFrame = new MainFrame(this);

        Socket s = null;
        try {
            s = new Socket("localhost", 12321);
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            out.println("my message");
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
    public static void main(String[] args) {
        new PrivateChat();
    }
}
