package Server;

import java.io.IOException;
import java.util.EventListener;

public interface ServerAcceptFailListener extends EventListener {
    void onFail(IOException e);
}
