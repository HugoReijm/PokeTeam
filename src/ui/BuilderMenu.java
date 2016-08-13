package ui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import type.Type;
import type.TypeAnalyzer;
import ui.InputField;
import ui.MenuLabel;
import ui.MenuButton;

public class BuilderMenu extends AbstractMenu {
	private int uniformWidth=200;
	private final MenuLabel tierLabel;
	private final MenuButton btnDefensive;
	private final MenuButton btnBalanced;
	private final MenuButton btnAggressive;
	private InputField teamMemberIn;
	private final VBox teamMemberInput;
	private final MenuButton btnCalculate;
	private final VBox teamDisplay;
	private final MenuLabel teamLabel;
	private final HBox teamLabels;
	private final VBox suggestionDisplay;
	private final MenuLabel suggestionLabel;
	private final HBox suggestionButtons;
	private final MenuButton typeToggleButton;
	private final MenuButton statsToggleButton;
	private final MenuButton popToggleButton;
	private final MenuButton btnBacktoTier;
	private final MenuButton btnBacktoStart;
	private final VBox typeChart;
	private final HBox typeLabels;
	private final MenuLabel types;
	private final MenuLabel typeScores;
	private final String menuImagePath = "file:resources/allPokemon.png";
    private ImageView menuImage;
	
	private final ArrayList<String[]> team = new ArrayList<String[]>(6);
	private final double[] battleMode = new double[6];
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
        
        teamLabel = new MenuLabel("Current Team", 430);
        teamLabels = new HBox(0);
        teamDisplay = makeTeamDisplay();
        
        suggestionLabel = new MenuLabel("Suggestions", 430);
        suggestionButtons = new HBox(0);
        typeToggleButton = new MenuButton("Type Score",80);
        statsToggleButton = new MenuButton("Stats Score",80);
        popToggleButton = new MenuButton("Popularity",77);
        suggestionDisplay = makeSuggDisplay();
        
        btnDefensive = new MenuButton("Defensive",uniformWidth/2);
        btnBalanced = new MenuButton("Balanced",uniformWidth/2);
        btnAggressive = new MenuButton("Aggressive",uniformWidth/2);
        
        btnDefensive.setOnMouseClicked(event -> {
        	btnDefensive.setColor(Color.AQUA);
        	btnBalanced.setColor(Color.BLACK);
        	btnAggressive.setColor(Color.BLACK);
        	clickBattleModeButton("defensive");
        });
        
        btnBalanced.setOnMouseClicked(event -> {
        	btnDefensive.setColor(Color.BLACK);
        	btnBalanced.setColor(Color.AQUA);
        	btnAggressive.setColor(Color.BLACK);
        	clickBattleModeButton("balanced");
        });
        
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
        
        MenuLabel typeChartLabel = new MenuLabel("Current Team Type Chart",200);
        typeChartLabel.setTranslateX(920);
		typeChart = new VBox(5);
        typeLabels = new HBox(0);
        types = new MenuLabel("Type",100);
        typeScores = new MenuLabel("Type Scores",100);
        makeTypeChart(100,100);
        
        backMenu.getChildren().addAll(btnBacktoTier, btnBacktoStart);
        modeMenu.getChildren().addAll(btnDefensive, btnBalanced, btnAggressive);
        menu.getChildren().addAll(teamMemberInput, btnCalculate);
        getChildren().addAll(menuImage,tierLabel,modeLabel,backMenu,modeMenu,menu,teamDisplay,suggestionDisplay,typeChartLabel,typeChart);
	}
    
    private void clickBattleModeButton(String mode)
    {
    	battleModeChosen = true;
    	if(mode.equals("defensive"))
    	{
	    	battleMode[0]=(double)9/5;
	    	battleMode[1]=(double)1/5;
	    	battleMode[2]=(double)9/5;
	    	battleMode[3]=(double)1/5;
	    	battleMode[4]=(double)9/5;
	    	battleMode[5]=(double)1/5;
    	}
    	else if(mode.equals("balanced"))
    	{
	    	battleMode[0]=(double)1;
	    	battleMode[1]=(double)1;
	    	battleMode[2]=(double)1;
	    	battleMode[3]=(double)1;
	    	battleMode[4]=(double)1;
	    	battleMode[5]=(double)1;
    	}
    	else if(mode.equals("aggressive"))
    	{
    		battleMode[0]=(double)1/5;
	    	battleMode[1]=(double)9/5;
	    	battleMode[2]=(double)1/5;
	    	battleMode[3]=(double)9/5;
	    	battleMode[4]=(double)1/5;
	    	battleMode[5]=(double)9/5;
    	}
    }
    
    public void clickCalcButton(String input, boolean added)
    {
    	if(team.size()<6||!added)
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
        
        MenuLabel name = new MenuLabel("Name",193);
        
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
	
	private void makeTypeChart(int typeWidth, int weakWidth)
	{
        typeChart.setTranslateX(920);
        typeChart.setTranslateY(30);
        typeLabels.getChildren().addAll(types,typeScores);
        typeChart.getChildren().add(typeLabels);
        typeReload(typeWidth,weakWidth);
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
		typeReload(100,100);
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
		typeReload(100,100);
		teamList.clear();
		suggestionList.clear();
    	for(int i=0;i!=battleMode.length;i++)
    	{
    		battleMode[i]=0.0;
    	}
    	battleModeChosen=false;
    	teamMemberIn.setInput("");
    	
	}
	
	public void tierName()
    {
    	tierLabel.setText(UI.getCentralPokedex().getTier());
    }
	
	private void typeReload(int typeWidth, int weakWidth)
	{
		typeChart.getChildren().clear();
		typeChart.getChildren().add(typeLabels);
		double[] tempChart = new double[19];
		ArrayList<Type> teamTypes = new ArrayList<Type>();
		for(int i=0;i!=team.size();i++)
		{
			teamTypes.add(Type.toType(team.get(i)[8]));
			teamTypes.add(Type.toType(team.get(i)[9]));
		}
		ArrayList<ArrayList<Type>> wriTable = TypeAnalyzer.wriTable(teamTypes);
		double[] typeChartScores = TypeAnalyzer.minusWeakness(tempChart,wriTable);
		typeChartScores = TypeAnalyzer.plusResistance(typeChartScores,wriTable);
		
		HBox bug = new HBox(0);
			MenuLabel bugLabel = new MenuLabel("Bug",typeWidth);
			MenuLabel bugWeakness = new MenuLabel(Double.toString(typeChartScores[0]),weakWidth);
			typeChartColorChanger(bugWeakness,typeChartScores[0]);
			bug.getChildren().addAll(bugLabel,bugWeakness);
		
		HBox dark = new HBox(0);
			MenuLabel darkLabel = new MenuLabel("Dark",typeWidth);
			MenuLabel darkWeakness = new MenuLabel(Double.toString(typeChartScores[1]),weakWidth);
			typeChartColorChanger(darkWeakness,typeChartScores[1]);
			dark.getChildren().addAll(darkLabel,darkWeakness);
		
		HBox dragon = new HBox(0);
			MenuLabel dragonLabel = new MenuLabel("Dragon",typeWidth);
			MenuLabel dragonWeakness = new MenuLabel(Double.toString(typeChartScores[2]),weakWidth);
			typeChartColorChanger(dragonWeakness,typeChartScores[2]);
			dragon.getChildren().addAll(dragonLabel,dragonWeakness);
		
		HBox electric = new HBox(0);
			MenuLabel electricLabel = new MenuLabel("Electric",typeWidth);
			MenuLabel electricWeakness = new MenuLabel(Double.toString(typeChartScores[3]),weakWidth);
			typeChartColorChanger(electricWeakness,typeChartScores[3]);
			electric.getChildren().addAll(electricLabel,electricWeakness);
		
		HBox fairy = new HBox(0);
			MenuLabel fairyLabel = new MenuLabel("Fairy",typeWidth);
			MenuLabel fairyWeakness = new MenuLabel(Double.toString(typeChartScores[4]),weakWidth);
			typeChartColorChanger(fairyWeakness,typeChartScores[4]);
			fairy.getChildren().addAll(fairyLabel,fairyWeakness);
		
		HBox fighting = new HBox(0);
			MenuLabel fightingLabel = new MenuLabel("Fighting",typeWidth);
			MenuLabel fightingWeakness = new MenuLabel(Double.toString(typeChartScores[5]),weakWidth);
			typeChartColorChanger(fightingWeakness,typeChartScores[5]);
			fighting.getChildren().addAll(fightingLabel,fightingWeakness);
		
		HBox fire = new HBox(0);
			MenuLabel fireLabel = new MenuLabel("Fire",typeWidth);
			MenuLabel fireWeakness = new MenuLabel(Double.toString(typeChartScores[6]),weakWidth);
			typeChartColorChanger(fireWeakness,typeChartScores[6]);
			fire.getChildren().addAll(fireLabel,fireWeakness);
		
		HBox flying = new HBox(0);
			MenuLabel flyingLabel = new MenuLabel("Flying",typeWidth);
			MenuLabel flyingWeakness = new MenuLabel(Double.toString(typeChartScores[7]),weakWidth);
			typeChartColorChanger(flyingWeakness,typeChartScores[7]);
			flying.getChildren().addAll(flyingLabel,flyingWeakness);
		
		HBox ghost = new HBox(0);
			MenuLabel ghostLabel = new MenuLabel("Ghost",typeWidth);
			MenuLabel ghostWeakness = new MenuLabel(Double.toString(typeChartScores[8]),weakWidth);
			typeChartColorChanger(ghostWeakness,typeChartScores[8]);
			ghost.getChildren().addAll(ghostLabel,ghostWeakness);
		
		HBox grass = new HBox(0);
			MenuLabel grassLabel = new MenuLabel("Grass",typeWidth);
			MenuLabel grassWeakness = new MenuLabel(Double.toString(typeChartScores[9]),weakWidth);
			typeChartColorChanger(grassWeakness,typeChartScores[9]);
			grass.getChildren().addAll(grassLabel,grassWeakness);
		
		HBox ground = new HBox(0);
			MenuLabel groundLabel = new MenuLabel("Ground",typeWidth);
			MenuLabel groundWeakness = new MenuLabel(Double.toString(typeChartScores[10]),weakWidth);
			typeChartColorChanger(groundWeakness,typeChartScores[10]);
			ground.getChildren().addAll(groundLabel,groundWeakness);
	
		HBox ice = new HBox(0);
			MenuLabel iceLabel = new MenuLabel("Ice",typeWidth);
			MenuLabel iceWeakness = new MenuLabel(Double.toString(typeChartScores[11]),weakWidth);
			typeChartColorChanger(iceWeakness,typeChartScores[11]);
			ice.getChildren().addAll(iceLabel,iceWeakness);
		
		HBox normal = new HBox(0);
			MenuLabel normalLabel = new MenuLabel("Normal",typeWidth);
			MenuLabel normalWeakness = new MenuLabel(Double.toString(typeChartScores[12]),weakWidth);
			typeChartColorChanger(normalWeakness,typeChartScores[12]);
			normal.getChildren().addAll(normalLabel,normalWeakness);		
		
		HBox poison = new HBox(0);
			MenuLabel poisonLabel = new MenuLabel("Poison",typeWidth);
			MenuLabel poisonWeakness = new MenuLabel(Double.toString(typeChartScores[14]),weakWidth);
			typeChartColorChanger(poisonWeakness,typeChartScores[14]);
			poison.getChildren().addAll(poisonLabel,poisonWeakness);		
		
		HBox psychic = new HBox(0);
			MenuLabel psychicLabel = new MenuLabel("Psychic",typeWidth);
			MenuLabel psychicWeakness = new MenuLabel(Double.toString(typeChartScores[15]),weakWidth);
			typeChartColorChanger(psychicWeakness,typeChartScores[15]);
			psychic.getChildren().addAll(psychicLabel,psychicWeakness);
		
		HBox rock = new HBox(0);
			MenuLabel rockLabel = new MenuLabel("Rock",typeWidth);
			MenuLabel rockWeakness = new MenuLabel(Double.toString(typeChartScores[16]),weakWidth);
			typeChartColorChanger(rockWeakness,typeChartScores[16]);
			rock.getChildren().addAll(rockLabel,rockWeakness);
		
		HBox steel = new HBox(0);
			MenuLabel steelLabel = new MenuLabel("Steel",typeWidth);
			MenuLabel steelWeakness = new MenuLabel(Double.toString(typeChartScores[17]),weakWidth);
			typeChartColorChanger(steelWeakness,typeChartScores[17]);
			steel.getChildren().addAll(steelLabel,steelWeakness);
		
		HBox water = new HBox(0);
			MenuLabel waterLabel = new MenuLabel("Water",typeWidth);
			MenuLabel waterWeakness = new MenuLabel(Double.toString(typeChartScores[18]),weakWidth);
			typeChartColorChanger(waterWeakness,typeChartScores[18]);
			water.getChildren().addAll(waterLabel,waterWeakness);
		typeChart.getChildren().addAll(bug,dark,dragon,electric,fairy,fighting,fire,flying,ghost,grass,ground,ice,normal,poison,psychic,rock,steel,water);
	}
	
	private void typeChartColorChanger(MenuLabel label, double score)
	{
		if(score==0)
		{
			label.setFillColor(Color.BLACK);
			label.setTextColor(Color.AQUA);
		}
		else if(score<-1.0)
		{
			label.setFillColor(Color.FIREBRICK);
			label.setTextColor(Color.BLACK);
		}
		else if(score<0.0)
		{
			label.setFillColor(Color.CORAL);
			label.setTextColor(Color.BLACK);
		}
		else if(score>1.0)
		{
			label.setFillColor(Color.LIME);
			label.setTextColor(Color.BLACK);
		}
		else if(score>0.0)
		{
			label.setFillColor(Color.LAWNGREEN);
			label.setTextColor(Color.BLACK);
		}
	}
}
