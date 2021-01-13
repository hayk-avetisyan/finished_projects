import javafx.scene.paint.Color;

public class Circle extends javafx.scene.shape.Circle {

    private boolean clicked = false;
    private boolean status;
    private int x;
    private int y;

    public Circle(int y, int x) {
        super(20, Color.WHITE);
        this.x = x;
        this.y = y;
    }

    public boolean check(boolean status) {
        return clicked && this.status == status;
    }

    public boolean click(boolean status) {
        if(!clicked) {
            setFill(status ? Color.YELLOW : Color.BLUE);
            this.status = status;
            clicked = true;
            return true;
        }
        return false;
    }

    public void restart() {
        if(clicked) {
            setFill(Color.WHITE);
            clicked = false;
        }
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}
