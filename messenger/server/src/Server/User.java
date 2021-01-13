package Server;

import java.net.Socket;
import java.util.HashMap;

public final class User {

    private Socket socket;
    private boolean saved = false;
    private HashMap<String, Object> data = new HashMap<>();

    public User(Socket socket) {
        this.socket = socket;
    }

    public boolean isData(String key, Object value) {
        return value.equals(data.get(key));
    }

    public void setSaved(boolean state) {
        saved = state;
    }

    public HashMap<String, Object> data() {
        return data;
    }

    public Socket getSocket() {
        return socket;
    }

    public boolean isSaved() {
        return saved;
    }
}
