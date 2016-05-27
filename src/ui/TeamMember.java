package ui;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public final class TeamMember extends StackPane{
	
    private Rectangle pokemon;
    private Text text;
    private String name;
    private String type1;
    private String type2;


    public TeamMember(String name, String type1, String type2)
    {
    	this.name = name;
        this.type1 = type1;
        this.type2 = type2;

        text = new Text(name+"\t"+type1+"\t"+type2);
        text.setFont(Font.font(15));
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.LEFT);

        pokemon = new Rectangle(280, 30);
        pokemon.setOpacity(0.5);
        pokemon.setFill(Color.BLACK);
        pokemon.setEffect(new GaussianBlur(3.2));

        DropShadow drop = new DropShadow(40, Color.WHITE);
        drop.setInput(new Glow());

        getChildren().addAll(pokemon, text);
    }

    @Override
    public String toString() 
    {
        return name + " " + " " + type1 + " " + type2;
    }
}
