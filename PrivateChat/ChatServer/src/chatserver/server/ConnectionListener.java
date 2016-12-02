package chatserver.server;

/**
 * Created by Администратор on 02.12.2016.
 */
public class ConnectionListener extends Thread{
    private static ConnectionListener instance;

    public static ConnectionListener getInstance() {
        if (instance == null) instance = new ConnectionListener();
        return instance;
    }

    private ConnectionListener() {
        setDaemon(true);
    }
}
