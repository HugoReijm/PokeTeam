package ui;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuButton extends StackPane {

    private Text text;

    private Rectangle bg;

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
                text.setFont(Font.font(19));
            });

        this.setOnMouseExited(event -> {
        		text.setFont(Font.font(17));
            });

        DropShadow drop = new DropShadow(40, Color.WHITE);
        drop.setInput(new Glow());

        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));
    }
    
    public void setColor(Color color) {
        bg.setFill(color);
    }
}
