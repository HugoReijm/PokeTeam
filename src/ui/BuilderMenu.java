package ui;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mathData.Pokedex;
import ui.InputField;
import ui.MenuLabel;
import ui.MenuButton;

public class BuilderMenu extends AbstractMenu {
	private int uniformWidth=200;
	private MenuLabel tierLabel;
	private MenuButton btnDefensive;
	private MenuButton btnBalanced;
	private MenuButton btnAggressive;
	private InputField teamMemberIn;
	private VBox teamMemberInput;
	private MenuButton btnCalculate;
	private VBox teamDisplay;
	private MenuLabel teamLabel;
	private HBox teamLabels;
	private VBox suggestionDisplay;
	private MenuLabel suggestionLabel;
	private HBox suggestionLabels;
	private MenuButton btnBacktoTier;
	private MenuButton btnBacktoStart;
	
	private Pokedex pokedex;
	private ArrayList<String[]> team = new ArrayList<String[]>(6);
	private int[] battleMode = new int[6];
	private boolean battleModeChosen=false;
	private List<TeamMember> teamList = new ArrayList<TeamMember>();
	private List<Suggestion> suggestionList = new ArrayList<Suggestion>();
	
	public BuilderMenu()
	{	
        HBox backMenu = new HBox(8);
		
        tierLabel = new MenuLabel("",200);
        tierLabel.setTranslateX(350);
        
        MenuLabel modeLabel = new MenuLabel("Choose Your Team Style",200);
        modeLabel.setTranslateX(350);
        modeLabel.setTranslateY(85);
        		
        HBox modeMenu = new HBox(8);
        modeMenu.setTranslateX(292);
        modeMenu.setTranslateY(125);
        
    	VBox menu = new VBox(5);
        menu.setTranslateX(450-uniformWidth/2);
        menu.setTranslateY(200);
        
        teamDisplay = makeTeamDisplay();
        
        suggestionDisplay = makeSuggDisplay();
        
        btnDefensive = new MenuButton("Defensive",uniformWidth/2);
        btnDefensive.setOnMouseClicked(event -> {
        	btnDefensive.setColor(Color.AQUA);
        	btnBalanced.setColor(Color.BLACK);
        	btnAggressive.setColor(Color.BLACK);
        	clickBattleModeButton("defensive");
        });
        
        btnBalanced = new MenuButton("Balanced",uniformWidth/2);
        btnBalanced.setOnMouseClicked(event -> {
        	btnDefensive.setColor(Color.BLACK);
        	btnBalanced.setColor(Color.AQUA);
        	btnAggressive.setColor(Color.BLACK);
        	clickBattleModeButton("balanced");
        });
        
        btnAggressive = new MenuButton("Aggressive",uniformWidth/2);
        btnAggressive.setOnMouseClicked(event -> {
        	btnDefensive.setColor(Color.BLACK);
        	btnBalanced.setColor(Color.BLACK);
        	btnAggressive.setColor(Color.AQUA);
        	clickBattleModeButton("aggressive");
        });
        
        teamMemberInput=TeamMemberInput();
        
        btnCalculate = new MenuButton("GO!",uniformWidth/2);
        btnCalculate.setOnMouseClicked(event -> {
            if(battleModeChosen&&(teamMemberIn.getInput()!=null&&!teamMemberIn.getInput().trim().isEmpty()))
            {
            	clickCalcButton();
            }
            else if(battleModeChosen&&!(teamMemberIn.getInput()!=null&&!teamMemberIn.getInput().trim().isEmpty()))
            {
            	teamMemberIn.setInput("Input is Empty!");
            }
            else if(!battleModeChosen)
            {
            	teamMemberIn.setInput("First Choose a Team Style");
            }
        });
        
        btnBacktoTier = new MenuButton("Back",75);
        btnBacktoTier.setOnMouseClicked(event -> {
    		clickBackButton();
        });
        
        btnBacktoStart = new MenuButton("Back to Start", 150);
        btnBacktoStart.setOnMouseClicked(event -> {
    		clickBackStartButton();
        });
        
        backMenu.getChildren().addAll(btnBacktoTier, btnBacktoStart);
        modeMenu.getChildren().addAll(btnDefensive, btnBalanced, btnAggressive);
        menu.getChildren().addAll(teamMemberInput, btnCalculate);
        Rectangle bg = new Rectangle(900, 700);
        bg.setFill(Color.GREY);
        bg.setOpacity(0.35);

        getChildren().addAll(bg,tierLabel,modeLabel,backMenu,modeMenu,menu,teamDisplay,suggestionDisplay);
	}
	
    public void setPokedex(Pokedex pokedex)
    {
    	this.pokedex = pokedex;
    	tierLabel.setText(pokedex.getTier());
    }
    
    private void clickBattleModeButton(String mode)
    {
    	battleModeChosen = true;
    	if(mode.equals("defensive"))
    	{
	    	battleMode[0]=3;
	    	battleMode[1]=1;
	    	battleMode[2]=3;
	    	battleMode[3]=1;
	    	battleMode[4]=3;
	    	battleMode[5]=1;
    	}
    	else if(mode.equals("balanced"))
    	{
	    	battleMode[0]=1;
	    	battleMode[1]=3;
	    	battleMode[2]=1;
	    	battleMode[3]=3;
	    	battleMode[4]=1;
	    	battleMode[5]=3;
    	}
    	else if(mode.equals("aggressive"))
    	{
	    	battleMode[0]=1;
	    	battleMode[1]=1;
	    	battleMode[2]=1;
	    	battleMode[3]=1;
	    	battleMode[4]=1;
	    	battleMode[5]=1;
    	}
    }
    
    private void clickCalcButton()
    {
    	if(team.size()<6)
    	{
	    	String[] member = pokedex.exactSearch(teamMemberIn.getInput());
	    	if(member[1]!=null)
	    	{
		    	teamMemberIn.setInput("");
	    		team.add(member);
		    	PokemonMaker pokemonMaker = new PokemonMaker(pokedex,team,battleMode);
		    	teamList = pokemonMaker.makeTeamList();
		    	teamReload(teamList);
		    	if(team.size()<6)
		    	{
			    	suggestionList = pokemonMaker.makeSuggestionList();
		    	}
		    	else
		    	{
		    		suggestionList = new ArrayList<Suggestion>();
		    	}
		    	suggestionReload(suggestionList);
	    	}
	    	else
	    	{
	    		teamMemberIn.setInput("Couldn't Find a Match");
	    	}
    	}
    	else
    	{
    		teamMemberIn.setInput("Your Team is Full");
    	}
    }
    
	private void clickBackButton()
    {
		reset();
		UI.sceneReload(UI.getPrimaryStage(),UI.getTierMenuScene());
    }
	
	private void clickBackStartButton()
	{
		reset();
		UI.sceneReload(UI.getPrimaryStage(),UI.getMenuScene());
	}
	
	private VBox TeamMemberInput() 
    {
        VBox input = new VBox(0);

        input.setTranslateX(0);
        input.setTranslateY(0);
        MenuLabel plname = new MenuLabel("Choose Your Pokemon", uniformWidth);

        teamMemberIn = new InputField(uniformWidth);
        input.getChildren().addAll(plname, teamMemberIn);
        return input;
    }
	
	private VBox makeTeamDisplay()
	{
		VBox teamBox = new VBox(0);
		teamBox.setTranslateX(10);
        teamBox.setTranslateY(320);
        
        teamLabel = new MenuLabel("Current Team", 430);
        teamLabels = new HBox(0);
        MenuLabel name = new MenuLabel("Name",200);
        MenuLabel type1 = new MenuLabel("Type #1",115);
        MenuLabel type2 = new MenuLabel("Type #2",115);
        teamLabels.getChildren().addAll(name,type1,type2);
        teamBox.getChildren().addAll(teamLabel,teamLabels);
		return teamBox;
	}
	
	private VBox makeSuggDisplay()
	{
		VBox suggs = new VBox(0);
        suggs.setTranslateX(460);
        suggs.setTranslateY(320);
        
        suggestionLabel = new MenuLabel("Suggestions", 430);
        suggestionLabels = new HBox(0);
        MenuLabel name = new MenuLabel("Name",193);
        MenuLabel stats = new MenuLabel("Type Score",80);
        MenuLabel type = new MenuLabel("Stats Score",80);
        MenuLabel pop = new MenuLabel("Popularity",77);
        suggestionLabels.getChildren().addAll(name,stats,type,pop);
        suggs.getChildren().addAll(suggestionLabel,suggestionLabels);
	    
        return suggs;
	}
	
	private void teamReload(List<TeamMember> team)
	{
		if(battleModeChosen)
		{
			teamDisplay.getChildren().clear();
			teamDisplay.getChildren().addAll(teamLabel,teamLabels);
			teamDisplay.getChildren().addAll(team);
		}
	}
	
	private void suggestionReload(List<Suggestion> team)
	{
		if(battleModeChosen)
		{
			suggestionDisplay.getChildren().clear();
			suggestionDisplay.getChildren().addAll(suggestionLabel,suggestionLabels);
			suggestionDisplay.getChildren().addAll(team);
		}
	}
	
	private void reset()
	{
    	btnDefensive.setColor(Color.BLACK);
    	btnBalanced.setColor(Color.BLACK);
    	btnAggressive.setColor(Color.BLACK);
    	teamDisplay.getChildren().clear();
		teamDisplay.getChildren().addAll(teamLabel,teamLabels);
		suggestionDisplay.getChildren().clear();
		suggestionDisplay.getChildren().addAll(suggestionLabel,suggestionLabels);
    	
		team.clear();
		teamList.clear();
		suggestionList.clear();
    	battleMode=new int[6];
    	battleModeChosen=false;
	}
}
