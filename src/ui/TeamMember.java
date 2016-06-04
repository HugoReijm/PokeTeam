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

public final class TeamMember extends StackPane{
	
    private HBox pokemon;
    private Box nameBox;
    private Box type1Box;
    private Box type2Box;


    public TeamMember(String name, String type1, String type2)
    {
    	nameBox = new Box(name,200,30);
        type1Box = new Box(type1,115,30);
        type2Box = new Box(type2,115,30);
        
        DropShadow drop = new DropShadow(40, Color.WHITE);
        drop.setInput(new Glow());
        
        pokemon = new HBox(0);
        pokemon.getChildren().addAll(nameBox,type1Box,type2Box);
        getChildren().add(pokemon);
    }
}
