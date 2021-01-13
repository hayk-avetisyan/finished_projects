import Client.Client;

import java.io.IOException;

public class ChatClient extends Client {

    protected ChatClient(String host, int port) {
        super(host, port);
    }

    @Override
    protected void sending(String message) throws IOException {
        byte[] data = message.getBytes();
        out.writeInt(data.length);
        out.write(data);
        out.flush();
    }

    @Override
    protected String receive() throws IOException {
        int length = in.readInt();
        byte[] data = new byte[length];
        in.read(data);
        return new String(data);
    }

    @Override
    protected boolean handshake() {
        send("0:" + data().get("name"));
        try {
            return receive().equals("OK");
        } catch (IOException e) {
            return false;
        }
    }
}
