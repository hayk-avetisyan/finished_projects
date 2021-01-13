package Client;

import java.util.EventListener;

public interface ClientMessageListener extends EventListener {
    void onMessage(String message);
}
