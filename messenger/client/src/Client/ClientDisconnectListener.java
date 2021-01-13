package Client;

import java.io.IOException;
import java.util.EventListener;

public interface ClientDisconnectListener extends EventListener {
    void onDisconnect(IOException e, boolean closed);
}
