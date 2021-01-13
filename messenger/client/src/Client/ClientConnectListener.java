package Client;

import java.util.EventListener;

public interface ClientConnectListener extends EventListener {
    void onConnect();
}
