package Server;

import java.net.ServerSocket;
import java.util.EventListener;

public interface ServerStartListener extends EventListener {
    void onStart(ServerSocket server);
}
