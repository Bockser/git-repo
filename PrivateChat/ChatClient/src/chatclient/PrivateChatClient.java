package chatclient;


import chatclient.frames.AbstractFrame;
import chatclient.frames.FrameManager;
import chatclient.frames.MainFrame;
import chatclient.frames.SettingFrame;

import static chatclient.helpers.ChatConstans.*;

/**
 * Created by Bockser on 30.11.2016.
 */
public class PrivateChatClient implements FrameManager{

    private MainFrame mainFrame;
    private SettingFrame settingFrame;

    public static void main(String[] args) {
       new PrivateChatClient();
    }

    private PrivateChatClient() {
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
}
