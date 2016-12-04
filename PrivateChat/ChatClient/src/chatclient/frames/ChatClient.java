package chatclient.frames;

import java.net.InetAddress;

/**
 * Created by Администратор on 02.12.2016.
 */
public interface ChatClient {

    void connect(InetAddress host, String Username);
}
