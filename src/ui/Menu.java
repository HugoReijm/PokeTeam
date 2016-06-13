package ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mathData.Pokedex;
import ui.MenuButton;

public class Menu extends AbstractMenu{
	private int uniformWidth=175;
    private MenuButton btnStart;
    private MenuButton btnPokedex;
    private MenuButton btnExit;
    //private final String menuImagePath = "file:resources/Pokeball.jpg";
    //private ImageView menuImage;
    
    public Menu()
    {
    	/*menuImage = new ImageView(new Image(menuImagePath));
    	menuImage.setFitHeight(700);
    	menuImage.setFitWidth(1244);
        menuImage.setX(-310);*/
    	
        Pokeball pb = new Pokeball(100);
        pb.setTranslateX(200);
        pb.setTranslateY(100);
        
    	VBox menu = new VBox(10);
        menu.setTranslateX(300-uniformWidth/2);
        menu.setTranslateY(450);

        btnStart = new MenuButton("Start",uniformWidth);
        btnStart.setOnMouseClicked(event -> {
                clickStartButton();
            });

        btnPokedex = new MenuButton("Pokedex",uniformWidth);
        btnPokedex.setOnMouseClicked(event -> {
        		clickPokedexButton();
        	});
        
        btnExit = new MenuButton("Exit",uniformWidth);
        btnExit.setOnMouseClicked(event -> {
                System.exit(0);
            });

        Rectangle bg = new Rectangle(600, 700);
        bg.setFill(Color.AQUA);
        bg.setOpacity(0.35);
        
        menu.getChildren().addAll(btnStart, btnPokedex, btnExit);
        getChildren().addAll(bg,pb,menu);
    }
    
    public void clickStartButton() 
    {
    	UI.sceneReload(UI.getPrimaryStage(),UI.getTierMenuScene());
    }
    
    public void clickPokedexButton(){
    	UI instance = UI.getInstance();
    	Pokedex distributionDex = Pokedex.toPokedex("AG");
    	instance.getPokedexMenu().setPokedex(distributionDex,true);
    	UI.sceneReload(UI.getPrimaryStage(),UI.getPokedexMenuScene());
    }
}
