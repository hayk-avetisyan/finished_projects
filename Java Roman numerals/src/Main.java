import Controllers.Decimal;
import Controllers.Roman;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Main extends Application {

    @Override
    public void start(Stage win) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

        new Decimal(root);
        new Roman(root);

        Scene page = new Scene(root, 428, 200);

        win.getIcons().add(new Image(Paths.get("files/logo.png").toUri().toString()));
        win.setScene(page);
        win.setTitle("Roman Numerals");
        win.setResizable(false);
        win.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
