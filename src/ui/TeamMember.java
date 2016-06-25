package ui;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public final class TeamMember extends StackPane{
	
    private HBox pokemon;
    private Box nameBox;
    private Box type1Box;
    private Box type2Box;
    private MenuButton deleteButton;

    public TeamMember(String name, String type1, String type2, BuilderMenu builderMenu)
    {
    	nameBox = new Box(name,200,30);
        type1Box = new Box(type1,100,30);
        type2Box = new Box(type2,100,30);
        deleteButton = new MenuButton("X",30);
        deleteButton.setTextColor(Color.WHITE, Color.RED);
        deleteButton.setOnMouseClicked(event -> {
    		builderMenu.clickCalcButton(name,false);
        });
        
        DropShadow drop = new DropShadow(40, Color.WHITE);
        drop.setInput(new Glow());
        
        pokemon = new HBox(0);
        pokemon.getChildren().addAll(nameBox,type1Box,type2Box,deleteButton);
        getChildren().add(pokemon);
        
        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));
    }

    public String getName()
    {
    	return this.nameBox.getText();
    }
}
