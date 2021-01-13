package Client;

import java.util.EventListener;

public interface ClientHandshakeFailListener extends EventListener {
    void onFail();
}
