package chatclient.frames;

import javax.swing.*;

/**
 * Created by Администратор on 02.12.2016.
 */
public class MainFrame extends JFrame {

    private FrameManager manager;
    //private static MainFrame instance = new

    public MainFrame(FrameManager manager) {
        this.manager = manager;
        setTitle("123");
        setLocation(200,200);
        setSize(500, 500);
        setVisible(true);
    }
}
