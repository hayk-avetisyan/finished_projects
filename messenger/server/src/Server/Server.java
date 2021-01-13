package Server;

import java.io.*;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Set;

/**
 * Այս դասը նախատեսված է սերվեր ստեղծելու և կատարվող միացումները կառավարելու համար։
 *
 * @author Հայկ Ավետիսյան
 * @version 1.0
 */
@SuppressWarnings("unused")
public abstract class Server {

    protected ServerSocket server;
    protected final HashMap<User, Thread> users = new HashMap<>();

    private ServerStartListener startListener = server -> {};
    private ServerConnectListener connectListener = user -> {};
    private ServerDisconnectListener disconnectListener = (connection, e) -> {};
    private ServerMessageListener messageListener = (connection, message) -> {};
    private ServerAcceptFailListener acceptFailListener = e -> {};

    protected Server(int port) throws IOException {
        server = new ServerSocket(port);
    }

    public void start() {
         new Thread(() -> {

            int count = 0;
            startListener.onStart(server);

            while (true) {
                try {
                    User user = new User(server.accept());
                    new Thread(new Monitoring(user), "Socket listening " + ++count).start();

                } catch (IOException e) {
                    acceptFailListener.onFail(e);
                }
            }
        }, "Server listening").start();
    }

    public final void sendToOthers(String message, User user) {
        Set<User> keys = users.keySet();
        for(User key : keys) {
            if(!key.equals(user)) send0(message, key);
        }
    }

    public final void send(String message) {
        Set<User> keys = users.keySet();
        for(User user : keys) send0(message, user);
    }

    public final void send(String message, User user) {
        send0(message, user);
    }

    private void send0(String message, User user) {
        try {
            sending(message, user);
        } catch (IOException e) {
            delete(user, e);
        }
    }

    private void delete(User user, IOException e) {
        if (users.containsKey(user) && user.isSaved()) {
            user.setSaved(false);
            disconnectListener.onDisconnect(user, e);
            users.get(user).interrupt();
            users.remove(user);
        }
    }


    private class Monitoring implements Runnable {
        private User user;

        Monitoring(User user) {
            this.user = user;
        }

        @Override
        @SuppressWarnings("InfiniteLoopStatement")
        public void run() {
            if(handshake(user)) {
                users.put(user, Thread.currentThread());
                user.setSaved(true);

                connectListener.onConnect(user);
                try {
                    while (true) messageListener.onMessage(user, receive(user));

                } catch (IOException e) {
                    delete(user, e);
                }
            }

            try {
                user.getSocket().close();
            } catch (IOException ignore) {}
        }
    }

    public final void setOnStart(ServerStartListener e) {
        startListener = e;
    }

    public final void setOnConnect(ServerConnectListener e) {
        connectListener = e;
    }

    public final void setOnDisconnect(ServerDisconnectListener e) {
        disconnectListener = e;
    }

    public final void setOnMessage(ServerMessageListener e) {
        messageListener = e;
    }

    public final void setOnAcceptFail(ServerAcceptFailListener e) {
        acceptFailListener = e;
    }

    public final HashMap<User, Thread> getUsers() {
        return users;
    }

    public final ServerSocket getServerSocket() {
        return server;
    }

    protected abstract String receive(User socket) throws IOException;

    protected abstract void sending(String message, User connection) throws IOException;

    protected boolean handshake(User user) {
        return true;
    }

}
