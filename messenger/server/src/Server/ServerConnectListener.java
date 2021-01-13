package Server;

import java.net.Socket;
import java.util.EventListener;

public interface ServerConnectListener extends EventListener {
    void onConnect(User user);
}
