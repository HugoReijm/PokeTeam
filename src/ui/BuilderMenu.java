package ui;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
	private HBox suggestionButtons;
	private MenuButton typeToggleButton;
	private MenuButton statsToggleButton;
	private MenuButton popToggleButton;
	private MenuButton btnBacktoTier;
	private MenuButton btnBacktoStart;
	private final String menuImagePath = "file:resources/allPokemon.png";
    private ImageView menuImage;
	
	private ArrayList<String[]> team = new ArrayList<String[]>(6);
	private int[] battleMode = new int[6];
	private boolean battleModeChosen=false;
	private List<TeamMember> teamList = new ArrayList<TeamMember>();
	private List<Suggestion> suggestionList = new ArrayList<Suggestion>();
	
	public BuilderMenu()
	{	
		menuImage = new ImageView(new Image(menuImagePath));
		menuImage.setOpacity(0.25);
    	menuImage.setFitHeight(700);
    	menuImage.setFitWidth(1244);

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
            	//long stTime = System.currentTimeMillis();
            	clickCalcButton(teamMemberIn.getInput(),true);
                //long endTime = System.currentTimeMillis();
                //System.out.println("Time: " + (endTime - stTime));
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
        getChildren().addAll(menuImage,tierLabel,modeLabel,backMenu,modeMenu,menu,teamDisplay,suggestionDisplay);
	}
    
    private void clickBattleModeButton(String mode)
    {
    	battleModeChosen = true;
    	if(mode.equals("defensive"))
    	{
	    	battleMode[0]=9/5;
	    	battleMode[1]=1/5;
	    	battleMode[2]=9/5;
	    	battleMode[3]=1/5;
	    	battleMode[4]=9/5;
	    	battleMode[5]=1/5;
    	}
    	else if(mode.equals("balanced"))
    	{
	    	battleMode[0]=1;
	    	battleMode[1]=1;
	    	battleMode[2]=1;
	    	battleMode[3]=1;
	    	battleMode[4]=1;
	    	battleMode[5]=1;
    	}
    	else if(mode.equals("aggressive"))
    	{
    		battleMode[0]=1/5;
	    	battleMode[1]=9/5;
	    	battleMode[2]=1/5;
	    	battleMode[3]=9/5;
	    	battleMode[4]=1/5;
	    	battleMode[5]=9/5;
    	}
    }
    
    public void clickCalcButton(String input, boolean added)
    {
    	if(team.size()<6)
    	{
	    	String[] member = UI.getCentralPokedex().exactSearch(input);
	    	if(member[1]!=null)
	    	{
	    		teamMemberIn.setInput("");
	    		if(added)
		    	{
		    		team.add(member);
		    	}
		    	else
		    	{
		    		team.remove(member);
		    	}
		    	run();
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
		UI.sceneReload(UI.getPrimaryStage(),UI.getTierMenuScene());
		UI.turnOffStage(UI.getSecondaryStage());
		UI.reset();
    }
	
	private void clickBackStartButton()
	{
		UI.reset();
		UI.sceneReload(UI.getPrimaryStage(),UI.getMenuScene());
		UI.turnOffStage(UI.getSecondaryStage());
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
        MenuLabel type1 = new MenuLabel("Type #1",100);
        MenuLabel type2 = new MenuLabel("Type #2",100);
        MenuLabel delete = new MenuLabel("X",30);
        teamLabels.getChildren().addAll(name,type1,type2,delete);
        teamBox.getChildren().addAll(teamLabel,teamLabels);
		return teamBox;
	}
	
	private VBox makeSuggDisplay()
	{
		VBox suggs = new VBox(0);
        suggs.setTranslateX(460);
        suggs.setTranslateY(320);
        
        suggestionLabel = new MenuLabel("Suggestions", 430);
        suggestionButtons = new HBox(0);
        MenuLabel name = new MenuLabel("Name",193);
        
        typeToggleButton = new MenuButton("Type Score",80);
        typeToggleButton.setFont(15);
        typeToggleButton.setOnMouseClicked(event -> {
    		UI.setTypeBool(!UI.getTypeBool());
    		if(UI.getTypeBool())
    		{
    			typeToggleButton.setColor(Color.RED);
    		}
    		else
    		{
    			typeToggleButton.setColor(Color.BLACK);
    		}
    		
    		if(team.size()>0)
    		{
	    		if(team.size()<6)
	        	{
		    		run();
	        	}
	        	else
	        	{
	        		teamMemberIn.setInput("Your Team is Full");
	        	}
    		}
        });
        
        statsToggleButton = new MenuButton("Stats Score",80);
        statsToggleButton.setFont(15);
        statsToggleButton.setOnMouseClicked(event -> {
        	UI.setStatsBool(!UI.getStatsBool());
        	if(UI.getStatsBool())
    		{
    			statsToggleButton.setColor(Color.RED);
    		}
    		else
    		{
    			statsToggleButton.setColor(Color.BLACK);
    		}
        	
        	if(team.size()>0)
        	{
	        	if(team.size()<6)
	        	{
		    		run();
	        	}
	        	else
	        	{
	        		teamMemberIn.setInput("Your Team is Full");
	        	}
        	}
        });
        
        popToggleButton = new MenuButton("Popularity",77);
        popToggleButton.setFont(15);
        popToggleButton.setOnMouseClicked(event -> {
        	UI.setPopBool(!UI.getPopBool());
        	if(UI.getPopBool())
    		{
    			popToggleButton.setColor(Color.RED);
    		}
    		else
    		{
    			popToggleButton.setColor(Color.BLACK);
    		}
        	
        	if(team.size()>0)
        	{
	        	if(team.size()<6)
	        	{
		    		run();
	        	}
	        	else
	        	{
	        		teamMemberIn.setInput("Your Team is Full");
	        	}
        	}
        });
        
        suggestionButtons.getChildren().addAll(name,typeToggleButton,statsToggleButton,popToggleButton);
        suggs.getChildren().addAll(suggestionLabel,suggestionButtons);
	    
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
			suggestionDisplay.getChildren().addAll(suggestionLabel,suggestionButtons);
			suggestionDisplay.getChildren().addAll(team);
		}
	}
	
	private void run()
	{
		PokemonMaker pokemonMaker = new PokemonMaker(UI.getCentralPokedex(),team,battleMode,this);
    	teamList = pokemonMaker.makeTeamList();
    	teamReload(teamList);
    	if(team.size()<6&&team.size()>0)
    	{
    		suggestionList = pokemonMaker.makeSuggestionList();
    	}
    	else
    	{
    		suggestionList = new ArrayList<Suggestion>();
    	}
	    suggestionReload(suggestionList);
	}
	
	public void reset()
	{
    	btnDefensive.setColor(Color.BLACK);
    	btnBalanced.setColor(Color.BLACK);
    	btnAggressive.setColor(Color.BLACK);
    	typeToggleButton.setColor(Color.BLACK);
    	statsToggleButton.setColor(Color.BLACK);
    	popToggleButton.setColor(Color.BLACK);
    	teamDisplay.getChildren().clear();
		teamDisplay.getChildren().addAll(teamLabel,teamLabels);
		suggestionDisplay.getChildren().clear();
		suggestionDisplay.getChildren().addAll(suggestionLabel,suggestionButtons);
    	
		team.clear();
		teamList.clear();
		suggestionList.clear();
    	battleMode=new int[6];
    	battleModeChosen=false;
    	teamMemberIn.setInput("");
	}
	
	public void tierName()
    {
    	tierLabel.setText(UI.getCentralPokedex().getTier());
    }
}
