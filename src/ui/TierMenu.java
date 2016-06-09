package ui;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mathData.Pokedex;
import ui.UI;
import ui.MenuButton;

public class TierMenu extends AbstractMenu {
	private int uniformWidth=175;
    private MenuButton btnAG;
    private MenuButton btnUbers;
    private MenuButton btnBS;
    private MenuButton btnOU;
    private MenuButton btnUU;
    private MenuButton btnRU;
    private MenuButton btnNU;
    private MenuButton btnBacktoStart;
    //private final String menuImagePath = "file:resources/";
    //private ImageView menuImage;
    
    public TierMenu()
    {
        //menuImage = new ImageView(new Image(menuImagePath));
        //getChildren().addAll(menuImage);

        VBox menu = new VBox(10);
        menu.setTranslateX(300-uniformWidth/2);
        menu.setTranslateY(225);

        btnAG = new MenuButton("Anything Goes",uniformWidth);
        btnAG.setOnMouseClicked(event -> {
                clickTierButton("AG");
            });

        btnUbers = new MenuButton("Ubers",uniformWidth);
        btnUbers.setOnMouseClicked(event -> {
        		clickTierButton("Ubers");
        	});

        btnBS = new MenuButton("BS",uniformWidth);
        btnBS.setOnMouseClicked(event -> {
        		clickTierButton("BS");
        	});
        
        btnOU = new MenuButton("OU",uniformWidth);
        btnOU.setOnMouseClicked(event -> {
        		clickTierButton("OU");
        	});
        
        btnUU = new MenuButton("UU",uniformWidth);
        btnUU.setOnMouseClicked(event -> {
        		clickTierButton("UU");
        	});
        
        btnRU = new MenuButton("RU",uniformWidth);
        btnRU.setOnMouseClicked(event -> {
        		clickTierButton("RU");
        	});
        
        btnNU = new MenuButton("NU",uniformWidth);
        btnNU.setOnMouseClicked(event -> {
        		clickTierButton("NU");
        	});
        
        btnBacktoStart = new MenuButton("Back",75);
        btnBacktoStart.setOnMouseClicked(event -> {
	        	clickBackButton();
        	});
        
        menu.getChildren().addAll(btnAG, btnUbers, btnBS, btnOU, btnUU, btnRU, btnNU);
        Rectangle bg = new Rectangle(600, 700);
        bg.setFill(Color.GREY);
        bg.setOpacity(0.35);

        getChildren().addAll(bg, btnBacktoStart, menu);
    }
    
    private void clickTierButton(String tier)
    {
    	UI instance = UI.getInstance();
    	Pokedex distributionDex = Pokedex.toPokedex(tier);
    	instance.getBuilderMenu().setPokedex(distributionDex);
    	instance.getPokedexMenu().setPokedex(distributionDex,false);
    	UI.sceneReload(UI.getPrimaryStage(),UI.getBuilderMenuScene());
    	UI.sceneReload(UI.getSecondaryStage(), UI.getPokedexMenuScene());
    }
    
    private void clickBackButton()
    {
    	UI.sceneReload(UI.getPrimaryStage(),UI.getMenuScene());
    }
}
