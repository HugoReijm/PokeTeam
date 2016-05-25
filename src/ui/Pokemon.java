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

public final class Pokemon extends StackPane{
	
    private Rectangle pokemon;

    private Text text;
    
    private String name;

    private double statsScore;
    
    private double typeScore;
    
    private double popScore;

    public Pokemon(final String name, final double statsScore, final double typeScore, final double popScore)
    {
    	this.name = name;
        this.statsScore = statsScore;
        this.typeScore = typeScore;
        this.popScore = popScore;

        text = new Text(name + "\t" + statsScore + "\t" + typeScore + "\t" + popScore);
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
        return name + " " + statsScore + " " + typeScore + " " + popScore;
    }
}
