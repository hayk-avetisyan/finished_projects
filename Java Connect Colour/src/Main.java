import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    private static boolean turn = new Random().nextBoolean();
    private static BoardPane board = new BoardPane();
    private static WinStage win = new WinStage();

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage main) {
        main.getIcons().add(new Image("logo.png"));
        main.setTitle("Connect Four");
        main.setResizable(false);
        main.setScene(new Scene(board));

        board.status(turn);

        main.show();
    }

    public static void click(MouseEvent mouse) {
        Circle circle = (Circle) mouse.getSource();
        if(circle.click(turn)) {
            if(new WinChecker(circle).checkForWin()) {
                win.setWinner(turn);
                win.showAndWait();
                board.restart();
            }
            else turn = !turn;

            board.status(turn);
        }
    }

    private static class WinChecker {

        private int x, y;

        private WinChecker(Circle c) {
            this.x = c.x();
            this.y = c.y();
        }

        private boolean checkForWin() {
            return checkForRow() || checkForCol() || checkFirstDiagonal() || checkSecondDiagonal();
        }

        private boolean checkForRow() {
            int count = countRowLeft(x - 1) + countRowRight(x + 1);
            return (count >= 3);
        }

        private int countRowLeft(int x) {
            if(x >= 0 && board.get(x,y).check(turn)) return 1 + countRowLeft(x - 1);
            return 0;
        }

        private int countRowRight(int x) {
            if(x < 7 && board.get(x,y).check(turn)) return 1 + countRowRight(x + 1);
            return 0;
        }

        private boolean checkForCol() {
            int count = countBottom(y + 1) + countTop(y - 1);
            return (count >= 3);
        }

        private int countTop(int y) {
            if(y >= 0 && board.get(x, y).check(turn)) return 1 + countTop(y - 1);
            return 0;
        }

        private int countBottom(int y) {
            if(y < 6 && board.get(x, y).check(turn)) return 1 + countBottom(y + 1);
            return 0;
        }

        private boolean checkFirstDiagonal() {
            int count = countFistDiagonalUp(x - 1, y - 1) + countFistDiagonalDown(x + 1, y + 1);
            return (count >= 3);
        }

        private int countFistDiagonalUp(int x, int y) {
            if(x >= 0 && y >= 0 && board.get(x, y).check(turn)) return 1 + countFistDiagonalUp(x - 1, y - 1);
            return 0;
        }

        private int countFistDiagonalDown(int x, int y) {
            if(x < 7 && y < 6 && board.get(x, y).check(turn)) return 1 + countFistDiagonalDown(x + 1, y + 1);
            return 0;
        }

        private boolean checkSecondDiagonal() {
            int count = countSecondDiagonalUp(x + 1, y - 1) + countSecondDiagonalDown(x - 1, y + 1);
            return (count >= 3);
        }

        private int countSecondDiagonalUp(int x, int y) {
            if(x < 7 && y >= 0 && board.get(x, y).check(turn)) return 1 + countSecondDiagonalUp(x + 1, y - 1);
            return 0;
        }

        private int countSecondDiagonalDown(int x, int y) {
            if(x >= 0 && y < 6 && board.get(x, y).check(turn)) return 1 + countSecondDiagonalDown(x - 1, y + 1);
            return 0;
        }


    }

}
