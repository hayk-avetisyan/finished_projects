package Controllers;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;

import java.nio.file.Paths;

public abstract class Base
{

    protected static AudioClip sound;
    protected TextField input;
    protected TextField output;
    protected Button button;

    private String value = "";

    static {
        sound = new AudioClip(Paths.get("files/D3.wav").toUri().toString());
        sound.setVolume(0);
        sound.play();
        sound.setVolume(100);
    }

    protected Base(Parent page) {
        input   = (TextField) page.lookup("#" + setInputFieldID());
        output  = (TextField) page.lookup("#" + setOutputFieldID());
        button  = (Button)    page.lookup("#" + setButtonID());

        input.setOnKeyTyped(this::input);
        input.setOnKeyPressed(this::pressed);
        button.setOnMouseClicked(mouse -> convert());
    }

    private void pressed(KeyEvent e) {
        if(e.getCode() == KeyCode.ENTER) convert();
    }

    private void input(KeyEvent e) {
        String text = input.getText().toUpperCase();
        int cursor = input.getCaretPosition();

        if(validExpression(text)) value = text;
        else {
            cursor = value.length();
            sound.play();
        }

        input.setText(value);
        input.positionCaret(cursor);
    }

    abstract protected void convert();
    abstract protected boolean validExpression(String e);

    abstract protected String setInputFieldID();
    abstract protected String setOutputFieldID();
    abstract protected String setButtonID();
}
