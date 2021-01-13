package GUI;

import Calculator.Calculator;
import Calculator.InvalidExpressionException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * GUI Հաշվիչ
 *
 * Հաշվում է արտահայտության արժեքը
 * Ստուգում արտահայտության իսկությունը
 * Արգելում անթույլատրելի արժեքների ներմուծումը
 * Փոփոխել ներմուծման դաշտում կուրսորի դիրքը ←, →, ↓, ↑ ստեղններով
 */
public class Window extends Application {

    /** Պատուհանի լայնություն */
    private static final double width = 200;

    /** Պատուհանի բարձրություն */
    private static final double height = 280;

    /** Հաշվիչի տեղնաշարի ստեղների զանգված */
    private static final char[] buttons = {'(', ')', '%', '/', '7', '8', '9', '*', '4', '5', '6', '-', '1', '2', '3', '+', '.', '0', '←', '='};

    /** Հաշվիչի ներմուծման դաշտ */
    private TextField field;

    /** Դաշտը, որում տեղադրվում են GUI-ի բոլոր էլեմենտները */
    private Pane screen;
    
    /** Սխալ թվային արտահայտության մասին տեքստ */
    private Label warning;

    /** Անթույլատրելի ստեղները սեղմելիս հնչող ձայն */
    private AudioClip sound;

    /** Սեղմված և բաց չթողնված ստեղնների բազմություն */
    private ArrayList<Key> keys = new ArrayList<>();
    
    /** {@link #field} դաշտում ներմուծման կուրսոր */
    private int cursor = 0;
    
    /** true, եթե կատարվել է հաշվարկ, այլ կերպ ասած, սեղմվել է "=", "Enter" ստեղնը false */
    private boolean calculated = false;
    
    /** Ներմուծման դաշտում բացված և դեռևս չփակված փակագծերի քանակ */
    private int openedParentheses = 0;
    

    /** {@link #warning} դաշտի արժևորում */
    private void initWarning() {
        warning = new Label("Invalid expression!");
        warning.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 10));
        warning.setTextFill(Color.RED);
        warning.setLayoutY(40);
        warning.setLayoutX(width / 4 - warning.getWidth());
        warning.setVisible(false);
    }

    /** {@link #field} դաշտի արժևորում */
    private void initField() {
        field = new TextField();
        field.setMinWidth(width - 20);
        field.setLayoutX(10);
        field.setLayoutY(10);
        field.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        field.setDisable(true);
        field.setOpacity(1);
    }

    /** {@link #sound} դաշտի արժևորում */
    private void initSound() {

         sound = new AudioClip(Paths.get("files/D3.wav").toUri().toString());
         sound.setVolume(0);
         sound.play();
         sound.setVolume(100);
    }

    /** {@link #screen} դաշտի արժևորում */
    private void initEnvironment() {
        screen = new Pane(initKeyboard());
        screen.getChildren().addAll(field, warning);
        screen.setOnKeyPressed(this::pressed);
        screen.setOnKeyReleased(this::released);
    }

    /**
     * Օգտագործելով {@link #buttons} դաշտը, ստեղծում է ստեղնաշար
     * @return {@link Button} դասի տարրերով զանգված
     */
    private Button[] initKeyboard() {
        Button[] keyboard = new Button[20];
        for (int i = 0, x = 15, y = 55; i < 20; i++, x += 45) {

            keyboard[i] = new Button(String.valueOf(buttons[i]));
            if (i != 0 && i % 4 == 0) {
                y += 45;
                x = 15;
            }
            keyboard[i].setMinHeight(35);
            keyboard[i].setMinWidth(35);
            keyboard[i].setLayoutX(x);
            keyboard[i].setLayoutY(y);

            if (i != 18 && i != 19) {
                keyboard[i].setOnMouseClicked(this::submit);
            }
        }

        // Սեղմելիս ջնջել
        keyboard[18].setOnMouseClicked(this::delete);

        // Սեղմելիս հաշվել
        keyboard[19].setOnMouseClicked(this::calculate);
        return keyboard;
    }

    /** Կառուցում է պատուհանը, արժևորում պատուհանի էլեմենտները */
    @Override
    public void start(Stage win) {

        initSound();
        initField();
        initWarning();
        initEnvironment();

        Scene scene = new Scene(screen, width, height);

        win.getIcons().add(new Image(Paths.get("files/logo.png").toUri().toString()));
        win.setTitle("[JC]");
        win.setScene(scene);
        win.setResizable(false);
        win.show();

        // ֆոկուսը բերում ենք ամբողջ պատուհանի վրա
        screen.requestFocus();
    }

    /** Եթե սեղմվել է հաշվիչի 'Enter' ստեղնը */
    private void calculate(MouseEvent e) {
        calculate();
    }

    /**
     * Եթե սեղմվել է ստեղնաշարի ստեղն
     * Հաշվում-պահպանում է սեղմած ստեղների մասին տվյալները {@link #keys} դաշտում
     */
    private void pressed(KeyEvent event) {
        keys.add(new Key(event.getCode().getCode(), event.getText()));
    }

    /** Երբ ստեղնաշարի սեղմած որևէ ստեղն բաց է թողնվել  */
    private void released(KeyEvent event) {
        // եթե մեկ ստեղն է սեղմվել
        if (keys.size() == 1) {
            // ստանում ենք ստեղնը
            Key key = keys.get(0);

            // մաքրում ենք ստեղնների պահպանման դաշտը
            keys.clear();

            // ի՞նչ ստեղն է սեղմվել
            switch (key.code) {
                case 8: { // Backspace
                    delete();
                    return;
                }
                case 10: { // Enter
                    calculate();
                    return;
                }
                case 127: { // Delete
                    clear();
                    return;
                }
                case 37: { // ←
                    // տեղաշարժում ենք կուրսորը հետ
                    if(cursor > 0) cursor--;
                    return;
                }
                case 38: { // ↑
                    // տեղափոխում ենք կուրսորը սկիզբ
                    cursor = 0;
                    return;
                }
                case 39: { // →
                    // տեղաշարժում ենք կուրսորը առաջ
                    if(cursor < field.getText().length()) cursor++;
                    return;
                }
                case 40: { // ↓
                    // տեղափոխում ենք կուրսորը վերջ
                    cursor = field.getText().length();
                    return;
                }
            }

            // Ֆունկցիոնալ ստեղները արժեք չունեն
            if(key.value.length() == 1) submit(key.value.charAt(0));
            return;
        }

        // եթե միաժամանակ սեղմվել են երկու ստեղններ
        if(keys.size() == 2) {
            // Եթե դրանցից մեկը 'SHIFT' ստեղն է
            if(keys.contains(Key.SHIFT)) {

                // մյուսը՝ '9'-ն
                if(keys.contains(Key.NINE)) submit('(');

                //  մյուսը՝ '0'-ն
                if(keys.contains(Key.ZERO)) submit(')');
            }

            // Ջնջում ենք բաց թողնված ստեղնը
            keys.remove(new Key(event.getCode().getCode(), event.getText()));
        }
    }

    /** Եթե սեղմվել է հաշվիչի GUI-ի ստեղն */
    private void submit(MouseEvent event) {
        submit(((Button) event.getSource()).getText().charAt(0));
        screen.requestFocus();
    }

    /** եթե սեղմվել է հաշվիչի ջնջելու ստեղնը */
    private void delete(MouseEvent e) {
        delete();
    }

    /** Կատարում է {@link #field} դաշտում պարունակվող արտահայտության հաշվարկ և արդյունքը գրում վերջինիս փոխարեն */
    private void calculate() {
        try {
            // եթե այն դատարկ է
            if (field.getText().equals("")) return;

            // հաշվում ենք, եթե ստացված թիվը մնացորդ չունի, ջնջում ենք "․0"-ն
            field.setText(String.valueOf(new Calculator(field.getText()).calculate()).replace(".0", ""));

            // վերանայում ենք ընթացիկ դաշտերը
            cursor = field.getText().length();
            field.positionCaret(cursor);
            calculated = true;
            openedParentheses = 0;

        } catch (InvalidExpressionException e) {
            // եթե արտահայտությունը սխալ է, ցուցադրում ենք սխալի մասին ինֆորմացիա
            warning.setVisible(true);
        }

        screen.requestFocus();
    }

    /** Ջնջում ենք {@link #field} դաշից */
    private void delete() {
        warning.setVisible(false);
        screen.requestFocus();

        // եթե կատարվել է հաշվարկ, ջնջում ենք ամբողջը
        if (calculated) clear();

        String value = field.getText();

        if (!value.equals("")) {
            // ջնջում ենք վերջին նիշը
            char c = value.charAt(value.length() - 1);
            field.setText(value.substring(0, value.length() - 1));

            // եթե ջնջվել է բաց փակագիծ
            if (c == '(') openedParentheses--;
            //եթե ջնջվել է փակ փակագիծ
            if (c == ')') openedParentheses++;
            cursor--;
        }
    }

    /** ջնջում ենք {@link #field} դաշտի ամբողջ տեքստը  */
    private void clear() {
        calculated = false;
        field.setText("");
        cursor = 0;
        openedParentheses = 0;
    }

    /**
     * Կատարում է որոշակի գործողություն, կախված ներմուծված արժեքից.։
     * ընդ որում արժեքը․
     * -1 → անընդունելի է, հնչում է ձայն
     *  0 → ավելացվում է ներմուծման դաշտի վերջում
     *  1 → փոխարինում է իրենից առաջ գտվող նիշին
     *  2 → փոխարինում է ներմուծման դաշտի ողջ պարունակությանը
     *  3 → ավելացվում է ներմուծման դաշտի վերջում,
     *      ավելացվում է բացված և չփակված փակագծերի քանակը
     *  4 → ավելացվում է ներմուծման դաշտի վերջում,
     *      նվազեցվում է բացված և չփակված փակագծերի քանակը
     * @param value ներմուծված արժեք
     */
    private void submit(char value)  {

        StringBuilder text = new StringBuilder(field.getText());

        switch (new Validator(value, field.getText(), cursor, calculated, openedParentheses).validate()) {
            case -1: {
                sound.play();
                break;
            }
            case 0: {
                text.insert(cursor, value);
                field.setText(text.toString());
                cursor += 1;
                break;
            }
            case 1: {
                text.deleteCharAt(cursor - 1);
                text.insert(cursor - 1, value);
                field.setText(text.toString());
                break;
            }
            case 2: {
                field.setText(String.valueOf(value));
                cursor = 1;
                break;
            }
            case 3: {
                openedParentheses++;
                text.insert(cursor, value);
                field.setText(text.toString());
                cursor += 1;
                break;
            }
            case 4: {
                openedParentheses--;
                text.insert(cursor, value);
                field.setText(text.toString());
                cursor += 1;
                break;
            }
        }

        warning.setVisible(false);
        calculated = false;
    }


    /** Սկսում ենք ծրագիրը */
    public static void main(String[] args) {
        Application.launch(args);
    }
}