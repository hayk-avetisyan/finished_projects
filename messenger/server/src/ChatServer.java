import Server.Server;
import Server.User;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatServer extends Server {

    public ChatServer(int port) throws IOException {
        super(port);
    }

    @Override
    protected String receive(User user) throws IOException {
        DataInputStream input = new DataInputStream(user.getSocket().getInputStream());

        int length = input.readInt();
        byte[] data = new byte[length];
        input.read(data);
        return new String(data);

    }

    @Override
    protected void sending(String message, User user) throws IOException {
        DataOutputStream output = new DataOutputStream(user.getSocket().getOutputStream());

        byte[] data = message.getBytes();
        output.writeInt(data.length);
        output.write(data);
        output.flush();
    }

    @Override
    protected boolean handshake(User user) {
        try {
            Matcher matcher = Pattern.compile("^0:([a-zA-Z]+)$").matcher(receive(user));
            if(matcher.matches()) {
                user.data().put("name", matcher.group(1));
                send("OK", user);
                return true;
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
