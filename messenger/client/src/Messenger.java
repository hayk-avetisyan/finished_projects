import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Messenger extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        MainStage main = new MainStage("gui/main.fxml");
        FormStage form = new FormStage("gui/form.fxml");
        form.getStatusProperty().bind(main.getStatusProperty());
        main.getNameProperty().bind(form.getNameProperty());
        main.getIPProperty().bind(form.getIPProperty());
        while(true) {
            form.start();
            if(form.login()) main.start();
            else break;
        }
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
