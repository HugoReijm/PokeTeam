package ui;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Suggestion extends StackPane
{
    private HBox pokemon;
    private Box nameBox;
    private Box statsBox;
    private Box typeBox;
    private Box popBox;

    public Suggestion(String name, double statsScore, double typeScore, double popScore)
    {
        nameBox = new Box(name,193,30);
        statsBox = new Box(Double.toString(statsScore),80,30);
        typeBox = new Box(Double.toString(typeScore),80,30);
        popBox = new Box(Double.toString(popScore),77,30);
        
        DropShadow drop = new DropShadow(40, Color.WHITE);
        drop.setInput(new Glow());
        
        pokemon = new HBox(0);
        pokemon.getChildren().addAll(nameBox,statsBox,typeBox,popBox);
        getChildren().add(pokemon);

        this.setOnMouseEntered(event -> {
            nameBox.setTextColor(Color.AQUA);
            statsBox.setTextColor(Color.AQUA);
            typeBox.setTextColor(Color.AQUA);
            popBox.setTextColor(Color.AQUA);
        });

        this.setOnMouseExited(event -> {
        	nameBox.setTextColor(Color.WHITE);
            statsBox.setTextColor(Color.WHITE);
            typeBox.setTextColor(Color.WHITE);
            popBox.setTextColor(Color.WHITE);
        });
        
        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));
    }
    
    public String getName()
    {
    	return this.nameBox.getText();
    }
}
