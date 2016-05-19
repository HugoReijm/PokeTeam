package ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import ui.UI;
import ui.MenuLabel;
import ui.InputField;
import ui.MenuButton;

public class TierMenu extends AbstractMenu {
	private String tier;
	private int uniformWidth=175;
    private MenuButton btnAG;
    private MenuButton btnUbers;
    private MenuButton btnBS;
    private MenuButton btnOU;
    private MenuButton btnUU;
    private MenuButton btnRU;
    private MenuButton btnNU;
    private MenuButton btnPU;
    private MenuButton btnBacktoStart;
    private final String menuImagePath = "file:resources/space-invaders-wallpaper.gif";
    private ImageView menuImage;
    
    public TierMenu()
    {
        //menuImage = new ImageView(new Image(menuImagePath));
        //getChildren().addAll(menuImage);

        VBox menu = new VBox(10);
        menu.setTranslateX(300-uniformWidth/2);
        menu.setTranslateY(225);

        btnAG = new MenuButton("Anything Goes",uniformWidth);
        btnAG.setOnMouseClicked(event -> {
                FadeTransition ft = new FadeTransition(Duration.seconds(0.45),this);
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.setOnFinished(evt -> this.setVisible(false));
                ft.play();
                clickTierButton("AG");
            });

        btnUbers = new MenuButton("Ubers",uniformWidth);
        btnUbers.setOnMouseClicked(event -> {
        		FadeTransition ft = new FadeTransition(Duration.seconds(0.45),this);
        		ft.setFromValue(1);
        		ft.setToValue(0);
        		ft.setOnFinished(evt -> this.setVisible(false));
        		ft.play();
        		clickTierButton("Ubers");
        	});

        btnBS = new MenuButton("BS",uniformWidth);
        btnBS.setOnMouseClicked(event -> {
        		FadeTransition ft = new FadeTransition(Duration.seconds(0.45),this);
        		ft.setFromValue(1);
        		ft.setToValue(0);
        		ft.setOnFinished(evt -> this.setVisible(false));
        		ft.play();
        		clickTierButton("BS");
        	});
        
        btnOU = new MenuButton("OU",uniformWidth);
        btnOU.setOnMouseClicked(event -> {
        		FadeTransition ft = new FadeTransition(Duration.seconds(0.45),this);
        		ft.setFromValue(1);
        		ft.setToValue(0);
        		ft.setOnFinished(evt -> this.setVisible(false));
        		ft.play();
        		clickTierButton("OU");
        	});
        
        btnUU = new MenuButton("UU",uniformWidth);
        btnUU.setOnMouseClicked(event -> {
        		FadeTransition ft = new FadeTransition(Duration.seconds(0.45),this);
        		ft.setFromValue(1);
        		ft.setToValue(0);
        		ft.setOnFinished(evt -> this.setVisible(false));
        		ft.play();
        		clickTierButton("UU");
        	});
        
        btnRU = new MenuButton("RU",uniformWidth);
        btnRU.setOnMouseClicked(event -> {
        		FadeTransition ft = new FadeTransition(Duration.seconds(0.45),this);
        		ft.setFromValue(1);
        		ft.setToValue(0);
        		ft.setOnFinished(evt -> this.setVisible(false));
        		ft.play();
        		clickTierButton("RU");
        	});
        
        btnNU = new MenuButton("NU",uniformWidth);
        btnNU.setOnMouseClicked(event -> {
        		FadeTransition ft = new FadeTransition(Duration.seconds(0.45),this);
        		ft.setFromValue(1);
        		ft.setToValue(0);
        		ft.setOnFinished(evt -> this.setVisible(false));
        		ft.play();
        		clickTierButton("NU");
        	});
        
        btnPU = new MenuButton("PU",uniformWidth);
        btnPU.setOnMouseClicked(event -> {
        		FadeTransition ft = new FadeTransition(Duration.seconds(0.45),this);
        		ft.setFromValue(1);
        		ft.setToValue(0);
        		ft.setOnFinished(evt -> this.setVisible(false));
        		ft.play();
        		clickTierButton("PU");
        	});
        
        btnBacktoStart = new MenuButton("Back",75);
        btnBacktoStart.setOnMouseClicked(event -> {
	    		FadeTransition ft = new FadeTransition(Duration.seconds(0.45),this);
	    		ft.setFromValue(1);
	    		ft.setToValue(0);
	    		ft.setOnFinished(evt -> this.setVisible(false));
	    		ft.play();
	        	clickBackButton();
        	});
        
        menu.getChildren().addAll(btnAG, btnUbers, btnBS, btnOU, btnUU, btnRU, btnNU, btnPU);
        Rectangle bg = new Rectangle(600, 700);
        bg.setFill(Color.GREY);
        bg.setOpacity(0.35);

        getChildren().addAll(bg, btnBacktoStart, menu);
    }
    
    public void clickTierButton(String tier)
    {
    	setTier(tier);
    	UI instance = UI.getInstance();
    	instance.setTier(tier);
    	UI.sceneReload(UI.getSecondaryStage(), UI.getPokedexMenuScene());
    	UI.sceneReload(UI.getPrimaryStage(),UI.getBuilderMenuScene());
    }
    
    public void clickBackButton()
    {
    	UI.sceneReload(UI.getPrimaryStage(),UI.getMenuScene());
    }
    
    public String getTier()
    {
    	return this.tier;
    }
    
    public void setTier(String tier)
    {
    	this.tier=tier;
    }
}
