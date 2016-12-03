package chatserver.frames;

/**
 * Created by Администратор on 01.12.2016.
 */
public interface ServerManager {

    void hideFrame(int frameID);

    void showFrame(int frameID);

    void exit();

    void startServer();

    void stopServer();
}