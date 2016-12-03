package chatserver.frames;

import javax.swing.*;

/**
 * Created by Администратор on 01.12.2016.
 */

public abstract class AbstractFrame extends JFrame{

    private final int frameID;
    protected final ServerManager manager;

    public AbstractFrame(final ServerManager manager, final int frameID) {
        super();
        this.manager = manager;
        this.frameID = frameID;
    }

    public int getFrameID() {
        return frameID;
    }
}
