import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainStage extends BaseStage {

    private TextField messageField;
    private Label nameField;
    private VBox scroll;
    private VBox usersList;
    private ChatClient client;
    private SimpleStringProperty IP = new SimpleStringProperty();
    private SimpleStringProperty status = new SimpleStringProperty();

    private MessageController messageController;

    public MainStage(String filepath) throws IOException {
        super(filepath);

        setScene(new Scene(root));
        setResizable(false);
        getIcons().add(new Image("gui/logo.png"));
        setTitle("HayRoom");

        scroll = (VBox) namespace.get("scroll");
        usersList = (VBox) namespace.get("usersList");
        messageField = (TextField) namespace.get("messageField");
        nameField = (Label) namespace.get("name");

        messageController = new MessageController(scroll, usersList);

        messageField.setOnKeyPressed(key -> { if(key.getCode() == KeyCode.ENTER) send(); });
        ((ScrollPane) namespace.get("scrollPane")).vvalueProperty().bind(scroll.heightProperty());
        ((Button) namespace.get("send")).setOnAction(action -> send());
        ((Button) namespace.get("out")).setOnAction(action -> logOut());
    }

    @Override
    public void start() {
        try {
            status.set("");

            client = new ChatClient(IP.getValue(), 5423);
            client.data().put("name", nameField.getText());
            client.setOnDisconnect(this::onDisconnect);
            client.setOnMessage(messageController);
            client.connect();

            showAndWait();
            client.disconnect();
        } catch (IOException e) {
            status.set("Unable to connect to server!");
            close();
        }
    }

    private void logOut() {
        client.disconnect();

        scroll.getChildren().clear();
        usersList.getChildren().clear();
        messageField.setText("");
        close();
    }

    private void onDisconnect(IOException e, boolean closed) {
        if(!closed) status.set("Connection lost!");
        Platform.runLater(this::logOut);
    }

    private void send() {
        client.send(messageField.getText());
        messageField.setText("");
    }

    public StringProperty getNameProperty() {
        return nameField.textProperty();
    }

    public StringProperty getIPProperty() {
        return IP;
    }

    public StringProperty getStatusProperty() {
        return status;
    }
}
