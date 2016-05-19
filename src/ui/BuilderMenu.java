package ui;

import java.util.List;

import javafx.animation.FadeTransition;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ui.InputField;
import ui.MenuLabel;
import ui.MenuButton;

public class BuilderMenu extends AbstractMenu {
	private String tier;
	private int uniformWidth=200;
	private boolean defensive=false;
	private MenuButton btnDefensive;
	private boolean balanced=false;
	private MenuButton btnBalanced;
	private boolean aggressive=false;
	private MenuButton btnAggressive;
	private InputField teamMemberIn;
	private VBox teamMemberInput;
	private MenuButton btnCalculate;
	private MenuButton btnBacktoTier;
	private MenuButton btnBacktoStart;
	
	//private List<Pokemon> teamL;
	//private List<Pokemon> suggsL;
	
	public BuilderMenu()
	{
		UI instance = UI.getInstance();
    	setTier(instance.getTier());
    	
    	VBox menu = new VBox(5);
        menu.setTranslateX(300-uniformWidth/2);
        menu.setTranslateY(200);
        
        VBox teamDisplay = makeTeamDisplay();
        
        VBox suggestionDisplay = makeSuggDisplay();
        
        HBox modeMenu = new HBox(8);
        modeMenu.setTranslateX(140);
        modeMenu.setTranslateY(88);
        
        HBox backMenu = new HBox(8);
        
        btnDefensive = new MenuButton("Defensive",uniformWidth/2);
        btnDefensive.setOnMouseClicked(event -> {
            defensive = true;
            balanced = false;
            aggressive = false;
            System.out.println("defensive: "+defensive);
        });
        
        btnBalanced = new MenuButton("Balanced",uniformWidth/2);
        btnBalanced.setOnMouseClicked(event -> {
            defensive = false;
            balanced = true;
            aggressive = false;
            System.out.println("balanced: "+balanced);
        });
        
        btnAggressive = new MenuButton("Aggressive",uniformWidth/2);
        btnAggressive.setOnMouseClicked(event -> {
            defensive = false;
            balanced = false;
            aggressive = true;
            System.out.println("aggressive: "+aggressive);
        });
        
        teamMemberInput=TeamMemberInput();
        
        btnCalculate = new MenuButton("GO!",uniformWidth/2);
        btnCalculate.setOnMouseClicked(event -> {
            System.out.println("Now we're calculating! WEEEEE");
        });
        
        btnBacktoTier = new MenuButton("Back",75);
        btnBacktoTier.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.45),this);
    		ft.setFromValue(1);
    		ft.setToValue(0);
    		ft.setOnFinished(evt -> this.setVisible(false));
    		ft.play();
    		clickBackButton();
        });
        
        btnBacktoStart = new MenuButton("Back to Start", 150);
        btnBacktoStart.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.45),this);
    		ft.setFromValue(1);
    		ft.setToValue(0);
    		ft.setOnFinished(evt -> this.setVisible(false));
    		ft.play();
    		clickBackStartButton();
        });
        
        backMenu.getChildren().addAll(btnBacktoTier, btnBacktoStart);
        modeMenu.getChildren().addAll(btnDefensive, btnBalanced, btnAggressive);
        menu.getChildren().addAll(teamMemberInput, btnCalculate);
        Rectangle bg = new Rectangle(600, 700);
        bg.setFill(Color.GREY);
        bg.setOpacity(0.35);

        getChildren().addAll(bg,backMenu,modeMenu,menu,teamDisplay,suggestionDisplay);
	}
	
	public String getTier()
    {
    	return this.tier;
    }
    
    public void setTier(String tier)
    {
    	this.tier=tier;
    }
	
	public void clickBackButton()
    {
		UI.sceneReload(UI.getPrimaryStage(),UI.getTierMenuScene());
    }
	
	public void clickBackStartButton()
	{
		UI.sceneReload(UI.getPrimaryStage(),UI.getMenuScene());
	}
	
	public VBox TeamMemberInput() 
    {
        VBox input = new VBox(0);

        input.setTranslateX(0);
        input.setTranslateY(0);
        MenuLabel plname = new MenuLabel("Team Member", uniformWidth);

        teamMemberIn = new InputField(uniformWidth);
        input.getChildren().addAll(plname, teamMemberIn);
        return input;
    }
	
	public VBox makeTeamDisplay()
	{
		VBox team = new VBox(0);
		team.setTranslateX(62.5);
        team.setTranslateY(350);
        
        MenuLabel teamlabel = new MenuLabel("Current Team", 175);
        //teamL = 
        team.getChildren().add(teamlabel);
        //team.getChildren().addAll(teamAl);
		return team;
	}
	
	public VBox makeSuggDisplay()
	{
		VBox suggs = new VBox(0);
        suggs.setTranslateX(362.5);
        suggs.setTranslateY(350);
        
        MenuLabel suggslabel = new MenuLabel("Suggestions", 175);
        //suggsL = 
        suggs.getChildren().add(suggslabel);
        //suggs.getChildren().addAll(suggsL);
        return suggs;
	}
}
