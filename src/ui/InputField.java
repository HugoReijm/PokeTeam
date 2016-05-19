package ui;

import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Input object for the assigning a player name.
 */
public class InputField extends StackPane {

    /**
     * Object of the field.
     */
    private Rectangle field;

    /**
     * The actual inputfield object.
     */
    private TextField input;

    /**
     * Constructor of the input field.
     */
    public InputField(int width) {
        field = new Rectangle(width, 25);
        field.setOpacity(0.5);
        field.setFill(Color.BLACK);
        field.setEffect(new GaussianBlur(3.2));
        input = new TextField();
        input.setText("");
        input.setMaxWidth(width);
        input.setStyle("-fx-background-color: white");

        getChildren().addAll(field, input);
    }

    /**
     * Returns the input.
     *
     * @return String
     */
    public String getInput() {
        String res = input.getText();
        if (res.length() > 8) {
            res = res.substring(0, 7);
        }
        return res;
    }

    /**
     * Returns the input.
     *
     * @param text
     *            String
     */
    public void setInput(final String text) {
        input.setText(text);
    }
}