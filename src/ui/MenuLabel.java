package ui;

import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * Label object for the menu.
 */
public class MenuLabel extends StackPane {

    /**
     * Object for the label.
     */
    private Rectangle bg;

    /**
     * Constructor for the label.
     *
     * @param text
     *            in the label
     * @param width
     *            of the label
     */
    public MenuLabel(final String text, final int width) {
        Label plname = new Label(text);
        plname.setFont(Font.font(15));
        plname.setTextFill(Color.WHITE);
        bg = new Rectangle(width, 28);
        bg.setOpacity(0.5);
        bg.setFill(Color.BLACK);
        bg.setEffect(new GaussianBlur(3.2));

        getChildren().addAll(bg, plname);
    }
}