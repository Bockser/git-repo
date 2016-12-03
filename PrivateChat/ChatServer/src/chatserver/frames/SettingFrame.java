package chatserver.frames;

import static chatserver.helpers.ChatConstans.*;

/**
 * Created by Администратор on 01.12.2016.
 */
public class SettingFrame extends AbstractFrame{

    private static SettingFrame instance = null;

    private static final String FRAME_TITLE = "Setting";

    public static void initInstance(ServerManager manager) {
        instance = new SettingFrame(manager);
    }

    public static SettingFrame getInstance() {
        try {
            if (instance == null) throw new NullPointerException();
        } catch (NullPointerException ex) {
            ex.toString();
            System.err.println("Instance must be initialize by metod initInstance(ServerManager manager)");
        }
        return instance;
    }

    private SettingFrame(ServerManager manager) {
        super(manager, MAIN_FRAME_ID);
        setTitle(FRAME_TITLE);
        setVisible(false);
    }
}
