package chatserver;

import javax.swing.*;

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

    public static void main(String[] args) {
        new MainServerFrame().initFrame();
    }

    private void initFrame() {
        setTitle(MAIN_FRAME_TITLE);
        setLocation(MAIN_LOC_FRAME_X, MAIN_LOC_FRAME_Y);
        setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
