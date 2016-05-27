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
	private MenuButton btnDefensive;
	private MenuButton btnBalanced;
	private MenuButton btnAggressive;
	private InputField teamMemberIn;
	private VBox teamMemberInput;
	private MenuButton btnCalculate;
	private VBox teamDisplay;
	private MenuLabel teamLabel;
	private VBox suggestionDisplay;
	private MenuLabel suggestionLabel;
	private MenuButton btnBacktoTier;
	private MenuButton btnBacktoStart;
	
	private Pokedex pokedex;
	private ArrayList<String[]> team = new ArrayList<String[]>(6);
	private int[] battleMode = new int[6];
	private boolean battleModeChosen=false;
	private List<TeamMember> teamList;
	private List<Suggestion> suggestionList;
	
	public BuilderMenu()
	{
		UI instance = UI.getInstance();
		
    	VBox menu = new VBox(5);
        menu.setTranslateX(300-uniformWidth/2);
        menu.setTranslateY(200);
        
        teamDisplay = makeTeamDisplay();
        
        suggestionDisplay = makeSuggDisplay();
        
        HBox modeMenu = new HBox(8);
        modeMenu.setTranslateX(140);
        modeMenu.setTranslateY(88);
        
        HBox backMenu = new HBox(8);
        
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
        Rectangle bg = new Rectangle(600, 700);
        bg.setFill(Color.GREY);
        bg.setOpacity(0.35);

        getChildren().addAll(bg,backMenu,modeMenu,menu,teamDisplay,suggestionDisplay);
	}
	
    public void setPokedex(Pokedex pokedex)
    {
    	this.pokedex = pokedex;
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
	    	teamMemberIn.setInput("");
	    	if(member[0]!=null)
	    	{
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
    	}
    }
    
	private void clickBackButton()
    {
		pokedex = null;
		UI.sceneReload(UI.getPrimaryStage(),UI.getTierMenuScene());
    }
	
	private void clickBackStartButton()
	{
		pokedex = null;
		UI.sceneReload(UI.getPrimaryStage(),UI.getMenuScene());
	}
	
	private VBox TeamMemberInput() 
    {
        VBox input = new VBox(0);

        input.setTranslateX(0);
        input.setTranslateY(0);
        MenuLabel plname = new MenuLabel("Team Member", uniformWidth);

        teamMemberIn = new InputField(uniformWidth);
        input.getChildren().addAll(plname, teamMemberIn);
        return input;
    }
	
	private VBox makeTeamDisplay()
	{
		VBox teamBox = new VBox(2);
		teamBox.setTranslateX(10);
        teamBox.setTranslateY(350);
        
        teamLabel = new MenuLabel("Current Team", 280);
        teamBox.getChildren().add(teamLabel);
	    //teamL = new ArrayList<Pokemon>();
		return teamBox;
	}
	
	private VBox makeSuggDisplay()
	{
		VBox suggs = new VBox(0);
        suggs.setTranslateX(310);
        suggs.setTranslateY(350);
        
        suggestionLabel = new MenuLabel("Suggestions", 280);
        suggs.getChildren().add(suggestionLabel);
        
	        /*ArrayList<String[]> suggslist=new ArrayList<String[]>();
	        suggslist.add(pokedex.exactSearch("Mega Sableye"));
	        int[] battleMode={1,1,1,1,1,1};
	        PokemonMaker pm = new PokemonMaker(pokedex,suggslist,battleMode);
	        suggsL = pm.makePokemonList();*/
	        //suggs.getChildren().addAll(suggsL);
	        
        return suggs;
	}
	
	private void teamReload(List<TeamMember> team)
	{
		if(battleModeChosen)
		{
			teamDisplay.getChildren().clear();
			teamDisplay.getChildren().add(teamLabel);
			teamDisplay.getChildren().addAll(team);
		}
	}
	
	private void suggestionReload(List<Suggestion> team)
	{
		if(battleModeChosen)
		{
			suggestionDisplay.getChildren().clear();
			suggestionDisplay.getChildren().add(suggestionLabel);
			suggestionDisplay.getChildren().addAll(team);
		}
	}
}
