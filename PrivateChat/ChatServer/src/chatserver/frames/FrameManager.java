package chatserver.frames;

/**
 * Created by Администратор on 01.12.2016.
 */
public interface FrameManager {

    void hideFrame(int frameID);

    void showFrame(int frameID);

    AbstractFrame getFrame(int frameID);

    void exit();

    void startServer();

    void stopServer();
}