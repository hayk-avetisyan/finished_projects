package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

@SuppressWarnings("unused")
public abstract class Client {

    private String host;
    private int port;
    private boolean closed;

    protected Socket socket;
    protected Thread thread;
    protected DataInputStream in;
    protected DataOutputStream out;
    protected HashMap<String, Object> data = new HashMap<>();


    private ClientConnectListener connectListener = () -> {};
    private ClientDisconnectListener disconnectListener = (e, closed) -> {};
    private ClientMessageListener messageListener = (message) -> {};
    private ClientHandshakeFailListener handshakeFailListener = () -> {};

    protected Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public final void connect() throws IOException {
        socket = new Socket(host, port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        closed = false;

        if(!handshake()) {
            handshakeFailListener.onFail();
            return;
        }

        connectListener.onConnect();

        thread = new Thread(() -> {
            try {
                while (true) messageListener.onMessage(receive());
            } catch (IOException e) {
                disconnect(e);
            }
        }, "Client connection");
        thread.start();
    }

    public final void send(String message) {
        try {
            sending(message);
        } catch (IOException e) {
            disconnect(e);
        }
    }

    private void disconnect(IOException e) {
        thread.interrupt();
        try {
            socket.close();
        } catch (IOException ignore) {}
        disconnectListener.onDisconnect(e, closed);
    }

    public void disconnect() {
        try {
            closed = true;
            socket.close();
        } catch (IOException ignore) {}
    }
    

    protected abstract void sending(String message) throws IOException;
    
    protected abstract String receive() throws IOException;
    
    protected boolean handshake() {
        return true;
    }

    public final void setOnMessage(ClientMessageListener e) {
        messageListener = e;
    }

    public final void setOnDisconnect(ClientDisconnectListener e) {
        disconnectListener = e;
    }

    public final void setOnConnect(ClientConnectListener e) {
        connectListener = e;
    }

    public final void setOnHandshakeFailed(ClientHandshakeFailListener e) {
        handshakeFailListener = e;
    }
    
    public final Socket getSocket() {
        return socket;
    }

    public final Thread getThread() {
        return thread;
    }

    public final DataInputStream getInputStream() {
        return in;
    }

    public final DataOutputStream getOutputStream() {
        return out;
    }

    public boolean isData(String key, Object value) {
        return value.equals(data.get(key));
    }

    public HashMap<String, Object> data() {
        return data;
    }
}
