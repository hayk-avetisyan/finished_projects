import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WinStage extends Stage {

    private static Label message = new Label("Do you want to try again?");
    private static Button yes = new Button("Yes");
    private static Button no = new Button("No");

    private static BorderPane root = new BorderPane();
    private Label winner = new Label();

    static {
        message.setPrefWidth(300);
        message.setAlignment(Pos.CENTER);
        yes.setPrefWidth(60);
        yes.setLayoutX(75);
        yes.setLayoutY(35);

        no.setPrefWidth(60);
        no.setLayoutX(165);
        no.setLayoutY(35);

        root.setPrefWidth(300);
        root.setPrefHeight(130);


        root.setCenter(new Pane(message, yes, no));
    }

    public WinStage() {
        winner.setFont(new Font("System Bold Italic", 16));
        winner.setPadding(new Insets(10));
        root.setTop(new StackPane(winner));

        initModality(Modality.APPLICATION_MODAL);
        getIcons().add(new Image("logo.png"));
        setTitle("Connect Four");
        setScene(new Scene(root));
        setResizable(false);

        setOnCloseRequest(win -> close());
        no.setOnAction(action -> close());
        yes.setOnAction(action -> super.close());
    }

    public void setWinner(boolean winner) {
        this.winner.setText((winner ? "Yellow" : "Blue") + " win!");
    }

    @Override
    public void close() {
        super.close();
        Platform.exit();
    }
}
