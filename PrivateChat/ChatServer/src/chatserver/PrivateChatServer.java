package chatserver;

import chatserver.frames.MainFrame;
import chatserver.frames.ServerManager;
import chatserver.frames.SettingFrame;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.*;

import static chatserver.helpers.ChatConstans.*;

/**
 * Created by Bockser on 30.11.2016.
 */
public class PrivateChatServer implements ServerManager, Runnable{

    private MainFrame mainFrame;
    private SettingFrame settingFrame;

    private int serverPort = DEFAULT_PORT;
    private boolean doRun = false;

    int connectionCount;
    private ServerSocket server = null;
    private List<Connection> connections = Collections.synchronizedList(new ArrayList<Connection>());

    public static void main(String[] args) {
        new PrivateChatServer();
    }

    private PrivateChatServer() {
        MainFrame.initInstance(this);
        mainFrame = MainFrame.getInstance();

        SettingFrame.initInstance(this);
        settingFrame = SettingFrame.getInstance();

        showFrame(MAIN_FRAME_ID);
    }

    @Override
    public void run() {
        connectionCount = 0;
        doRun = true;
        try {
            server = new ServerSocket(serverPort);
            server.setSoTimeout(3000);
            mainFrame.addLog("server start");
            while (doRun == true) {
                try {
                    Connection connection = new Connection(connectionCount, server.accept());
                    connections.add(connection);
                    connection.start();
                    connectionCount++;
                } catch (SocketTimeoutException e) {}
            }
            mainFrame.addLog("Server stopped");
        } catch (IOException e) {
            mainFrame.addLog("Error: ServerSocket was not open");
        } finally { closeAll(); }
    }

    private void closeAll() {
        try {
            server.close();
            synchronized (connections) {
                Iterator<Connection> iterator = connections.iterator();
                while (iterator.hasNext())
                    iterator.next().close();
            }
        } catch (IOException e) {
            mainFrame.addLog("Error: Connection was not closed");        }
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
    public void exit() {
        if (doRun) {
            mainFrame.addLog("Error: Server is already running!");
        } else {
            settingFrame.dispose();
            mainFrame.dispose();
        }
    }

    @Override
    public void startServer() {
        if (doRun == true) mainFrame.addLog("Error: Server is already running");
        else {
            mainFrame.addLog("Trying to start the server");
            new Thread(this).start();
        }
    }

    @Override
    public void stopServer() {
        if (!doRun)
            mainFrame.addLog("Error: Server is not running");
        else {
            mainFrame.addLog("Stopping server");
            doRun = false;
        }
    }


    class Connection extends Thread{

        private final int sessionID;
        private Socket connection;
        private BufferedReader in;
        private PrintWriter out;
        private String name = "";
        private boolean exit = false;

        public Connection(final int sessionID, Socket connection) {
            this.sessionID = sessionID;
            this.connection = connection;
            try {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                out = new PrintWriter(connection.getOutputStream(), true);
            } catch (IOException e) { e.printStackTrace(); }
            setDaemon(true);
        }

        @Override
        public void run() {
            try {
                String command = in.readLine();
                switch (command) {
                    case AUTH_COMMAND:
                        mainFrame.addLog("Authentication new connection from:" + connection.getInetAddress().toString());
                        if (authentication(in.readLine()))
                            chatHandler();
                        break;
                    case REG_COMMAND:
                        mainFrame.addLog("Registration for new connection from: "  + connection.getInetAddress().toString());
                        break;
                    default:
                        mainFrame.addLog("Error command from: "  + connection.getInetAddress().toString());
                        break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                mainFrame.addLog("Connection id" + sessionID + " is closing");
                mainFrame.removeConnectionInfo(sessionID);
                close();
            }
        }

        private boolean authentication(String name) throws IOException{
            this.name = name;
            String[] info = {Integer.toString(sessionID), name, connection.getInetAddress().toString(), "0"};
            System.out.println("I am here");
            out.println(COMMAND_SUCCESS);
            ObjectOutputStream objOut= new ObjectOutputStream(connection.getOutputStream());
            String[] users = new String[connections.size()];
            int i = 0;
            synchronized (connections) {
                Iterator<Connection> iterator = connections.iterator();
                while (iterator.hasNext()) {
                    users[i] = iterator.next().name;
                }
                System.out.print(users);
                objOut.writeObject(users);
            }
            mainFrame.addConnectionInfo(info);
            return true;
        }


        private void chatHandler() {

            try {
                synchronized (connection) {
                    Iterator<Connection> iter = connections.iterator();
                    while (iter.hasNext()) {
                        iter.next().sendPublicMessage("Chat server", name + " enter to chat");
                    }
                }
                while (!exit) {
                    String command = in.readLine();
                    switch (command) {
                        case PUBLIC_MESSAGE:
                            String senderPub = in.readLine();
                            String messagePub = in.readLine();
                            synchronized (connections) {
                                Iterator<Connection>  iter = connections.iterator();
                                while (iter.hasNext()) {
                                    iter.next().sendPublicMessage(senderPub, messagePub);
                                }
                            }
                            break;
                        case PRIVATE_MESSAGE:

                            String addressePri = in.readLine();
                            String messagePri = in.readLine();
                            synchronized (connections) {
                                Iterator<Connection>  iter = connections.iterator();
                                while (iter.hasNext()) {
                                    Connection tempConnection = iter.next();
                                    if (tempConnection.name.equals(addressePri)) {
                                        tempConnection.sendPrivateMessage(name, messagePri);
                                    }

                                }
                            }
                            break;
                        case EXIT_COMMAND:
                            exit = true;
                            break;
                    }
                }
            } catch (IOException ex) {}
        }

        private void sendPrivateMessage(String sender, String message) {
            out.println(PRIVATE_MESSAGE);
            out.println(sender);
            out.println(message);
        }

        private void sendPublicMessage(String sender, String message) {
            out.println(PUBLIC_MESSAGE);
            out.println(sender);
            out.println(message);
        }

        public void close() {
            try {
                exit = true;
                connection.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
