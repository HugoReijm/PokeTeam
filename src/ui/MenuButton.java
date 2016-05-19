package ui;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Class for the menu buttons.
 */
public class MenuButton extends StackPane {

	/**
     * Text object.
     */
    private Text text;

    /**
     * Rectangle object.
     */
    private Rectangle bg;

    /**
     * Create menu buttons.
     *
     * @param name
     *            on button
     */
    public MenuButton(final String name,int width) {
        text = new Text(name);
        text.setFont(Font.font(17));
        text.setFill(Color.WHITE);
        bg = new Rectangle(width, 27.5);
        bg.setOpacity(0.5);
        bg.setFill(Color.BLACK);
        bg.setEffect(new GaussianBlur(3.2));

        getChildren().addAll(bg, text);

        this.setOnMouseEntered(event -> {
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });

        this.setOnMouseExited(event -> {
                bg.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });

        DropShadow drop = new DropShadow(40, Color.WHITE);
        drop.setInput(new Glow());

        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));
    }

    /**
     * Text getter.
     *
     * @return Text
     */
    public Text getText() {
        return text;
    }
}
