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
    
    public TierMenu()
    {
        Pokeball pb = new Pokeball(75);
        pb.setTranslateX(225);
        pb.setTranslateY(35);
    	
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

        btnBS = new MenuButton("Battle Spot Singles",uniformWidth);
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
        bg.setFill(Color.AQUA);
        bg.setOpacity(0.35);

        getChildren().addAll(bg, pb, btnBacktoStart, menu);
    }
    
    private void clickTierButton(String tier)
    {
    	UI.setCentralPokedex(Pokedex.toPokedex(tier));
    	UI.sceneReload(UI.getPrimaryStage(),UI.getBuilderMenuScene());
    	//UI.sceneReload(UI.getSecondaryStage(),UI.getPokedexMenuScene());
    }
    
    private void clickBackButton()
    {
    	UI.setCentralPokedex(null);
    	UI.sceneReload(UI.getPrimaryStage(),UI.getMenuScene());
    }
    
    public void reset(){}
    
    public void tierName(){}
}
