package Server;

import java.net.Socket;
import java.util.EventListener;

public interface ServerMessageListener extends EventListener {
    void onMessage(User user, String message);
}
