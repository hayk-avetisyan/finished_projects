import Server.*;

import java.io.*;
import java.net.ServerSocket;
import java.util.Set;

public class Server {

    static ChatServer server;


    public static void main(String[] args) {
        try {
            server = new ChatServer(5423);

            server.setOnStart(Server::onStart);
            server.setOnMessage(Server::onMessage);
            server.setOnConnect(Server::onConnect);
            server.setOnDisconnect(Server::onDisconnect);
            server.setOnAcceptFail(Server::onAcceptFailed);
            server.start();

        } catch (IOException e) {
            System.out.println("Port already in use!");
        }
    }

    static void onStart(ServerSocket server) {
        System.out.println("Server started [" + server.getInetAddress().getHostAddress() + ":" + server.getLocalPort() + "]");
    }

    static void onMessage(User user, String message) {
        server.send("1" + user.data().get("name") + ":" + message);
    }

    static void onConnect(User user) {
        System.out.println(user.data().get("name") + " connected");
        server.sendToOthers("2" + user.data().get("name"), user);
        Set<User> users = server.getUsers().keySet();
        StringBuilder message = new StringBuilder();
        for(User client : users) {
            message.append(client.data().get("name")).append(';');
        }
        server.send("4" + message.toString(), user);
    }

    static void onDisconnect(User user, IOException e) {
        System.out.println(user.data().get("name") + " disconnected");
        server.send("3" + user.data().get("name"));
    }

    static void onAcceptFailed(IOException e) {
        System.out.println("Failed to accept");
    }
}
