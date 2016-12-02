package chatserver.frames;

import static chatserver.helpers.ChatConstans.*;

/**
 * Created by Администратор on 01.12.2016.
 */
public class SettingFrame extends AbstractFrame{

    private static final String FRAME_TITLE = "Setting";
    public SettingFrame(FrameManager manager) {
        super(manager, MAIN_FRAME_ID);
        setTitle(FRAME_TITLE);
        setVisible(false);
    }
}
