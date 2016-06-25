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
    private Color mainColor;
    private Color secondaryColor;

    public MenuButton(final String name,int width) {
        mainColor = Color.WHITE;
    	secondaryColor = Color.AQUA;
    	text = new Text(name);
        text.setFont(Font.font(17));
        text.setFill(mainColor);
        bg = new Rectangle(width,30);
        bg.setOpacity(0.5);
        bg.setFill(Color.BLACK);
        bg.setEffect(new GaussianBlur(3.2));

        getChildren().addAll(bg, text);

        this.setOnMouseEntered(event -> {
                text.setFill(secondaryColor);
            });

        this.setOnMouseExited(event -> {
        		text.setFill(mainColor);
            });

        DropShadow drop = new DropShadow(40, Color.WHITE);
        drop.setInput(new Glow());

        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));
    }
    
    public void setColor(Color color)
    {
    	bg.setFill(color);
    }
    
    public void setTextColor(Color color1, Color color2) 
    {
    	mainColor = color1;
    	secondaryColor = color2;
    	text.setFill(mainColor);
    }
    
    public void setFont(int font)
    {
    	text.setFont(Font.font(font));
    }
}
