import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.regex.Pattern;

public class FormStage extends BaseStage {

    private static Pattern namePattern = Pattern.compile("^[a-zA-Z]+$");
    private static Pattern IPPattern = Pattern.compile("^(\\d{1,3}\\.){3}\\d{1,3}$");
    private TextField nameField;
    private TextField IPField;
    private Button status;
    private Label error;
    private boolean login;

    public FormStage(String filepath) throws IOException {
        super(filepath);
        setScene(new Scene(root));
        setResizable(false);
        getIcons().add(new Image("gui/logo.png"));
        setTitle("HayRoom");

        nameField = (TextField) namespace.get("nameField");
        IPField = (TextField) namespace.get("IPField");
        status = (Button) namespace.get("status");
        error = (Label) namespace.get("error");

        ((Button) namespace.get("button")).setOnAction(action -> input());
        nameField.setOnKeyPressed(this::pressed);
        IPField.setOnKeyPressed(this::pressed);
    }

    @Override
    public void start() {
        login = false;
        error.setText("");
        showAndWait();
    }

    private void pressed(KeyEvent key) {
        if(key.getCode() == KeyCode.ENTER) input();
    }

    private void input() {
        if(IPPattern.matcher(IPField.getText()).matches()) {
            if(namePattern.matcher(nameField.getText()).matches()) {
                login = true;
                close();
            }
            error.setText("Invalid name! Only English letters.");
        }
        else error.setText("Invalid IP address!");
    }

    public StringProperty getNameProperty() {
        return nameField.textProperty();
    }

    public StringProperty getIPProperty() {
        return IPField.textProperty();
    }

    public StringProperty getStatusProperty() {
        return status.textProperty();
    }

    public boolean login() {
        return login;
    }
}
