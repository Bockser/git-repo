package chatserver.frames;

import javax.swing.*;

import static chatserver.helpers.ChatConstans.*;

/**
 * Created by Администратор on 01.12.2016.
 */
public class SettingFrame extends JDialog{

    protected final ServerManager manager;
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
        super(MainFrame.getInstance(), FRAME_TITLE, true);
        this.manager = manager;
        setSize(400, 400);
        setLocation(getOwner().getX(), getOwner().getY() );
        setVisible(false);
    }
}
