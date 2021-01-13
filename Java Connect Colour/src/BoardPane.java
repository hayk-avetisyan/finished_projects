import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class BoardPane extends BorderPane {

    private Circle[][] fields = new Circle[6][7];
    private Label status = new Label();

    public BoardPane() {
        FlowPane board = new FlowPane();
        board.setPrefWidth(360);
        board.setBackground(new Background(new BackgroundFill(Color.web("#D1EFFC"), CornerRadii.EMPTY, Insets.EMPTY)));
        board.setPadding(new Insets(10));
        board.setHgap(10);
        board.setVgap(10);

        ObservableList<Node> children = board.getChildren();
        for (int i = 0; i < 6; i++) {
            for (int k = 0; k < 7; k++) {
                fields[i][k] = new Circle(i, k);
                children.add(fields[i][k]);
                fields[i][k].setOnMouseClicked(Main::click);
            }
        }

        status.setFont(new Font("System Italic", 15));
        status.setPadding(new Insets(5));

        setCenter(board);
        setBottom(status);
    }

    public void restart() {
        for (int i = 0; i < 6; i++) {
            for (int k = 0; k < 7; k++) fields[i][k].restart();
        }
    }

    public Circle get(int x, int y) {
        return fields[y][x];
    }

    public void status(boolean turn) {
        status.setText((turn ? "Yellow" : "Blue") + " turn");
    }
}
