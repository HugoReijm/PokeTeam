package ui;

import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ui.MenuLabel;
import ui.MenuButton;

public class Menu extends AbstractMenu{
	private int uniformWidth=175;
    private MenuButton btnStart;
    private MenuButton btnPokedex;
    private MenuButton btnExit;
    private final String menuImagePath = "file:resources/space-invaders-wallpaper.gif";
    private ImageView menuImage;
    
    public Menu()
    {
        VBox menu = new VBox(10);
        menu.setTranslateX(300-uniformWidth/2);
        menu.setTranslateY(450);

        btnStart = new MenuButton("Start",uniformWidth);
        btnStart.setOnMouseClicked(event -> {
                FadeTransition ft = new FadeTransition(Duration.seconds(1),this);
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.setOnFinished(evt -> this.setVisible(false));
                ft.play();
                clickStartButton();
            });

        btnPokedex = new MenuButton("Pokedex",uniformWidth);
        btnPokedex.setOnMouseClicked(event -> {
        		FadeTransition ft = new FadeTransition(Duration.seconds(0.45),this);
        		ft.setFromValue(1);
        		ft.setToValue(0);
        		ft.setOnFinished(evt -> this.setVisible(false));
        		ft.play();
        		clickPokedexButton();
        	});
        
        btnExit = new MenuButton("Exit",uniformWidth);
        btnExit.setOnMouseClicked(event -> {
                System.exit(0);
            });

        menu.getChildren().addAll(btnStart, btnPokedex, btnExit);
        Rectangle bg = new Rectangle(600, 700);
        bg.setFill(Color.GREY);
        bg.setOpacity(0.35);

        getChildren().addAll(bg, menu);
    }
    
    public void clickStartButton() 
    {
    	UI.sceneReload(UI.getPrimaryStage(),UI.getTierMenuScene());
    }
    
    public void clickPokedexButton(){
    	UI.sceneReload(UI.getSecondaryStage(),UI.getPokedexMenuScene());
    }
}
