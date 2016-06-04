package ui;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Box extends StackPane{

	private Text text;
	private Rectangle rectangle;
	private int height;
	private int width;
	
	public Box(String text,int width,int height)
	{
		this.text = new Text(text);
		this.height=height;
		this.width=width;
        this.text.setFont(Font.font(15));
        this.text.setFill(Color.WHITE);
        this.text.setTextAlignment(TextAlignment.LEFT);
		rectangle = new Rectangle(width,height);
		rectangle.setOpacity(0.5);
		rectangle.setFill(Color.BLACK);
		rectangle.setEffect(new GaussianBlur(3.2));
		getChildren().addAll(rectangle,this.text);
	}
	
	public void setTextColor(Color color)
	{
		text.setFill(color);
	}
}
