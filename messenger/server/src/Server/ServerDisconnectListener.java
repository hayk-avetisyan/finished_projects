package Server;

import java.io.IOException;
import java.net.Socket;
import java.util.EventListener;

public interface ServerDisconnectListener extends EventListener {
    void onDisconnect(User user, IOException e);
}
