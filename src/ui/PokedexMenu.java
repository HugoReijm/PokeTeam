package ui;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.InputField;
import ui.MenuLabel;

public class PokedexMenu extends AbstractMenu {
	private String tier;
	private int uniformWidth=200;
	private MenuButton btnSearch;
    private InputField nameIn;
    private VBox nameInput;
    private InputField numberIn;
    private VBox numberInput;
    private InputField type1In;
    private VBox type1Input;
    private InputField type2In;
    private VBox type2Input;
    private MenuButton btnBacktoTier;
    
    public PokedexMenu()
    {
    	UI instance = UI.getInstance();
    	setTier(instance.getTier());
    	
    	VBox menu = new VBox(10);
        menu.setTranslateX(300-uniformWidth/2);
        menu.setTranslateY(100);
        
        nameInput=NameInput();
        numberInput=NumberInput();
        type1Input=Type1Input();
        type2Input=Type2Input();
        
        btnSearch = new MenuButton("Search!",uniformWidth);
        btnSearch.setOnMouseClicked(event -> {
                clickSearchButton();
            });
        
        btnBacktoTier = new MenuButton("Back",75);
        btnBacktoTier.setOnMouseClicked(event -> {
            clickBackButton();
        });
        
        menu.getChildren().addAll(nameInput,numberInput,type1Input,type2Input,btnSearch);
        Rectangle bg = new Rectangle(600, 700);
        bg.setFill(Color.GREY);
        bg.setOpacity(0.35);

        getChildren().addAll(bg,btnBacktoTier, menu);
    }
    
    public String getTier()
    {
    	return this.tier;
    }
    
    public void setTier(String tier)
    {
    	this.tier=tier;
    }
    
    public void clickSearchButton()
    {
    	System.out.println("Now we are searching the Pokedex");
    }
    
    public void clickBackButton()
    {
    	UI.sceneReload(UI.getPrimaryStage(),UI.getTierMenuScene());
    	UI.turnOffStage(UI.getSecondaryStage());
    }
    
    public VBox NameInput() 
    {
        VBox input = new VBox(0);

        input.setTranslateX(0);
        input.setTranslateY(0);
        MenuLabel plname = new MenuLabel("Name", uniformWidth);

        nameIn = new InputField(uniformWidth);
        input.getChildren().addAll(plname, nameIn);
        return input;
    }
    
    public VBox NumberInput()
    {
    	VBox input = new VBox(0);

        input.setTranslateX(0);
        input.setTranslateY(0);
        MenuLabel plname = new MenuLabel("National Pokedex Number", uniformWidth);

        numberIn = new InputField(uniformWidth);
        input.getChildren().addAll(plname, numberIn);
        return input;
    }
    
    public VBox Type1Input()
    {
    	VBox input = new VBox(0);

        input.setTranslateX(0);
        input.setTranslateY(0);
        MenuLabel plname = new MenuLabel("Type #1", uniformWidth);

        type1In = new InputField(uniformWidth);
        input.getChildren().addAll(plname, type1In);
        return input;
    }
    
    public VBox Type2Input()
    {
    	VBox input = new VBox(0);

        input.setTranslateX(0);
        input.setTranslateY(0);
        MenuLabel plname = new MenuLabel("Type #2", uniformWidth);

        type2In = new InputField(uniformWidth);
        input.getChildren().addAll(plname, type2In);
        return input;
    }
}
