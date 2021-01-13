import Client.ClientMessageListener;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageController implements ClientMessageListener {

    private static Pattern messagePattern = Pattern.compile("^1([a-zA-Z]+):(.+)$");
    private static Pattern connectPattern = Pattern.compile("^2([a-zA-Z]+)$");
    private static Pattern disconnectPattern = Pattern.compile("^3([a-zA-Z]+)$");
    private static Pattern usersPattern = Pattern.compile("^4(([a-zA-Z]+;)+)$");

    private VBox scroll;
    private VBox usersList;

    public MessageController(VBox scroll, VBox usersList) {
        this.scroll = scroll;
        this.usersList = usersList;
    }

    public void onMessage(String message) {
        char type = message.charAt(0);
        Matcher match;
        switch (type) {
            case '1': {
                match = messagePattern.matcher(message);
                if (match.matches()) messageData(match.group(1), match.group(2));
                break;
            }
            case '2': {
                match = connectPattern.matcher(message);
                if (match.matches()) connectData(match.group(1));
                break;
            }
            case '3': {
                match = disconnectPattern.matcher(message);
                if (match.matches()) disconnectData(match.group(1));
                break;
            }
            case '4': {
                match = usersPattern.matcher(message);
                if (match.matches()) usersData(match.group(1));
            }
        }
    }

    private void messageData(String name, String message) {
        try {
            Loader loader = new Loader("gui/message.fxml");

            ((Label) loader.get("name")).setText(name);
            ((Label) loader.get("message")).setText(message);
            Platform.runLater(() -> scroll.getChildren().add(loader.getRoot()));
        } catch (IOException ignore) {
        }
    }

    private void connectData(String name) {
        Label user = new Label(name);
        user.setFont(new Font("System Bold Italic", 13));
        user.setId("name:" + name);
        user.setPadding(new Insets(5));
        user.setStyle("-fx-background-color: white; -fx-background-radius: 5;");
        Platform.runLater(() -> usersList.getChildren().add(user));
    }

    private void disconnectData(String name) {
        ObservableList<Node> children = usersList.getChildren();
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getId().equals("name:" + name)) {
                int k = i;
                Platform.runLater(() -> children.remove(k));
                return;
            }
        }
    }

    private void usersData(String string) {
        String[] users = string.split(";");
        for (String user : users) {
            connectData(user);
        }
    }
}
