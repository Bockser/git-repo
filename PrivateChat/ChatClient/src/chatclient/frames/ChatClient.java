package chatclient.frames;

import java.net.InetAddress;

/**
 * Created by Администратор on 02.12.2016.
 */
public interface ChatClient {

    void connect(InetAddress host, String Username);

    void disconnect();

    String[] getUserList();

    String getMyName();

    void sendPublicMessage(String message);

    void sendPrivateMessage(String addressee, String message);

    void sendCommand(String command, String args[]);

}
