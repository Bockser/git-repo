package chatserver;


import chatserver.frames.AbstractFrame;
import chatserver.frames.FrameManager;
import chatserver.frames.MainFrame;
import chatserver.frames.SettingFrame;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static chatserver.helpers.ChatConstans.*;

/**
 * Created by Bockser on 30.11.2016.
 */
public class PrivateChatServer implements FrameManager{



    private MainFrame mainFrame;
    private SettingFrame settingFrame;
    private ConnectionListener connectionListener;
    private int serverPort = DEFAULT_PORT;

    public static void main(String[] args) {
       new PrivateChatServer();
    }

    private PrivateChatServer() {
        mainFrame = new MainFrame(this);
        settingFrame = new SettingFrame(this);
        showFrame(MAIN_FRAME_ID);
    }

    @Override
    public void hideFrame(int frameID) {
        switch (frameID) {
            case MAIN_FRAME_ID:
                mainFrame.setVisible(false);
                break;
            case SETTING_FRAME_ID:
                settingFrame.setVisible(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void showFrame(int frameID) {
        switch (frameID) {
            case MAIN_FRAME_ID:
                mainFrame.setVisible(true);
                break;
            case SETTING_FRAME_ID:
                settingFrame.setVisible(true);
                break;
            default:
                break;
        }
    }

    @Override
    public AbstractFrame getFrame(int frameID) {
        switch (frameID) {
            case MAIN_FRAME_ID:
                return mainFrame;
            case SETTING_FRAME_ID:
                return settingFrame;
            default:
                return null;
        }
    }

    @Override
    public void exit() {
        settingFrame.dispose();
        mainFrame.dispose();
    }

    @Override
    public void startServer() {
        mainFrame.addLog("Trying to start the server");
        connectionListener = new ConnectionListener();
        connectionListener.start();
    }

    @Override
    public void stopServer() {
        mainFrame.addLog("Stopping server");
        connectionListener.stopServer();

    }
    public class ConnectionListener extends Thread{

        private boolean doRun = true;
        private ConnectionListener() {
            setDaemon(true);
        }

        @Override
        public void run() {

            try {
                ServerSocket server = new ServerSocket(serverPort);
                mainFrame.addLog("server start");
            } catch (IOException e) {
                System.out.println("ServerSocket was not open");
            }
            while (doRun == true) {
                System.out.println("server working");
            }
            mainFrame.addLog("Server stopped");
        }

        public void stopServer () {
            doRun = false;
        }

        class ConnectionHandler extends Thread{

            public ConnectionHandler(final int sessionID, Socket connection) {

            }

        }
    }
}
