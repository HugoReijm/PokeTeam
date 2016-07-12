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

    private Rectangle bg;
    private Label name;

    public MenuLabel(final String text, final int width) {
    	name = new Label(text);
        name.setFont(Font.font(15));
        name.setTextFill(Color.AQUA);
        bg = new Rectangle(width, 30);
        bg.setOpacity(0.5);
        bg.setFill(Color.BLACK);
        bg.setEffect(new GaussianBlur(3.2));

        getChildren().addAll(bg, name);
    }
    
    public void setText(String text)
    {
    	name.setText(text);
    }
    
    public void setFillColor(Color color)
    {
    	bg.setFill(color);
    }
    
    public void setTextColor(Color color)
    {
    	name.setTextFill(color);
    }
}