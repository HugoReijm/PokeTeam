package ui;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mathData.Pokedex;
import ui.Menu;

public class UI extends Application{
    
	private static Stage primaryStage = new Stage();
	private static Stage secondaryStage = new Stage();
    private static Scene scene1;
    private static Scene scene2;
    private static Scene scene3;
    private static Scene scene4;
    private static Group root;
    private static Group root2;
    private static Group root3;
    private static Group root4;
	private static UI instance;
	
    private static AbstractMenu menu;
    private static AbstractMenu tierMenu;
    private static AbstractMenu pokedexMenu;
    private static AbstractMenu builderMenu;
    
    private static Pokedex centralPokedex;
    private static boolean typeBool = false;
    private static boolean statsBool = false;
    private static boolean popBool = false;
    
    private int maxWidth = 600;
    private int maxHeight = 700;

    public UI()
    {
    	super();
    	instance=this;
    }
    
    public static Stage getPrimaryStage()
    {
    	return primaryStage;
    }
    
    public static Stage getSecondaryStage()
    {
    	return secondaryStage;
    }
    
    public static void turnOffStage(Stage stage)
    {
    	stage.hide();
    }
    
    public static Scene getMenuScene()
    {
    	return scene1;
    }
    
    public static Scene getTierMenuScene()
    {
    	return scene2;
    }
    
    public static PokedexMenu getPokedexMenu()
    {
    	return (PokedexMenu) pokedexMenu;
    }
    
    public static Scene getPokedexMenuScene()
    {
    	return scene3;
    }
    
    public static BuilderMenu getBuilderMenu()
    {
    	return (BuilderMenu) builderMenu;
    }

    public static Pokedex getCentralPokedex()
    {
    	return centralPokedex;
    }
    
    public static void setCentralPokedex(Pokedex newPokedex)
    {
    	centralPokedex=newPokedex;
    }
    
    public static Scene getBuilderMenuScene()
    {
    	return scene4;
    }
    
    public static UI getInstance()
    {
    	return instance;
    }
    
    public static boolean getTypeBool()
    {
    	return typeBool;
    }
    
    public static void setTypeBool(boolean tempBool)
    {
    	typeBool=tempBool;
    }
    
    public static boolean getStatsBool()
    {
    	return statsBool;
    }
    
    public static void setStatsBool(boolean tempBool)
    {
    	statsBool=tempBool;
    }
    
    public static boolean getPopBool()
    {
    	return popBool;
    }
    
    public static void setPopBool(boolean tempBool)
    {
    	popBool=tempBool;
    }
    
    public static void sceneReload(Stage stage, Scene scene)
    {
    	stage.setScene(scene);
    	stage.setResizable(false);
    	stage.sizeToScene();
    	stage.show();
    	if(centralPokedex!=null)
    	{
    		if(scene.equals(scene3))
        	{
        		pokedexMenu.tierName();
        	}
    		if(scene.equals(scene4))
        	{
        		builderMenu.tierName();
        	}
    	}
    }
    
    public static void reset()
    {
    	centralPokedex=null;
    	typeBool=false;
    	statsBool=false;
    	popBool=false;
    	menu.reset();
    	tierMenu.reset();
    	builderMenu.reset();
    	pokedexMenu.reset();
    }
    
    public static String[][] finalScores(int quantity, Pokedex pokedex, ArrayList<String[]> team, double[] battleMode)
	{
    	int length = pokedex.getList().size();
    	ArrayList<String[]> finalScores = new ArrayList<String[]>(length);
    	double[] consts = {0.67,0.5,0.33,0.2,0.1};
    	double typeMax = 0.0;
    	double typeMin = 0.0;
    	double statsMax = 0.0;
    	double statsMin = 0.0;
		double compMax = 0.0;
		double compMin = 0.0;
		double typeRange = 0;
		double statsRange = 0;
		double compRange = 0;
		
		ArrayList<String[]> typeScores = new ArrayList<String[]>();
		ArrayList<String[]> statScores = new ArrayList<String[]>();
		int[] compScores = new int[pokedex.getList().size()];
		
    	if(!typeBool)
		{
			typeScores = MathAnalyzer.typeScores(pokedex,team);
			typeRange = 10;
		}
    	else
    	{
    		for(int i=0;i!=length;i++)
    		{
    			String[] score = new String[11];
    			score[10] = "0.0";
    			typeScores.add(score);
    		}
    	}
		
		if(!statsBool)
		{
			statScores = MathAnalyzer.statScore(pokedex,team,battleMode);
			statsRange = 10;
		}
		else
    	{
    		for(int i=0;i!=length;i++)
    		{
    			String[] score = new String[11];
    			score[10] = "0.0";
    			statScores.add(score);
    		}
    	}
		
    	if(!popBool)
    	{
    		compScores = CompAnalyzer.compScores(pokedex, team);
    		compRange = 1.4*typeRange+statsRange;
    		if(statsBool&&typeBool)
    		{
    			compRange=10;
    		}
    	}
		
		if(typeScores.size()==compScores.length&&statScores.size()==compScores.length)
		{
			for(int i=0;i!=length;i++)
			{
				if(!typeBool)
				{
					double tempTypeScore = Double.parseDouble(typeScores.get(i)[10]);
					if(tempTypeScore>typeMax)
					{
						typeMax=tempTypeScore;
					}
					else if(tempTypeScore<typeMin)
					{
						typeMin=tempTypeScore;
					}
				}
				
				if(!statsBool)
				{
					double tempStatsScore = Double.parseDouble(statScores.get(i)[10]);
					if(tempStatsScore>statsMax)
					{
						statsMax=tempStatsScore;
					}
					else if(tempStatsScore<statsMin)
					{
						statsMin=tempStatsScore;
					}
				}
				if(!popBool)
				{
					double tempCompScore = compScores[i];
					if(tempCompScore>compMax)
					{
						compMax=tempCompScore;
					}
					else if(tempCompScore<compMin)
					{
						compMin=tempCompScore;
					}
				}
			}
			
			for(int i=0;i!=length;i++)
			{
				String[] score = new String[15];
				for(int j=0;j!=10;j++)
				{
					score[j]=pokedex.getList().get(i)[j];
				}
				if(!typeBool)
				{
					score[10]=Double.toString((Double.parseDouble(typeScores.get(i)[10])-typeMin)*(typeRange/(typeMax-typeMin)));
				}
				else
				{
					score[10]="0.0";
				}
				
				if(!statsBool)
				{
					score[11]=Double.toString((Double.parseDouble(statScores.get(i)[10])-statsMin)*(statsRange/(statsMax-statsMin)));
				}
				else
				{
					score[11]="0.0";
				}
				
				score[12]=Double.toString(1.4*Double.parseDouble(score[10])+Double.parseDouble(score[11]));
				score[13]=Double.toString(compScores[i]);
				if(compMax>compMin)
				{
					score[14]=Double.toString((1-consts[team.size()-1])*(1.4*Double.parseDouble(score[10])+Double.parseDouble(score[11]))+consts[team.size()-1]*((compScores[i]-compMin)*(compRange/(compMax-compMin))));
				}
				else
				{
					score[14]=score[12];
				}
				finalScores.add(score);
			}
			finalScores = Pokedex.removeTeamandMegas(finalScores, team);
			String[][] finalSortedScores = MathAnalyzer.sort(finalScores, quantity);
			return finalSortedScores;
		}
		else
		{
			System.out.println("Error: score input sizes do not match");
			return null;
		}
	}
    
    public static void main(final String[] args) {
    	launch(args);
    }
    
	@Override
	public void start(final Stage stage) throws Exception{
		primaryStage.setTitle("PokeTeam Builder");
        secondaryStage.setTitle("Pokedex");
        root = new Group();
        root2 = new Group();
        root3 = new Group();
        root4 = new Group();
        
        menu = new Menu();
        tierMenu = new TierMenu();
        pokedexMenu = new PokedexMenu();
        builderMenu = new BuilderMenu();
        root.getChildren().add(menu);
        root2.getChildren().add(tierMenu);
        root3.getChildren().add(pokedexMenu);
        root4.getChildren().add(builderMenu);
        
        scene1 = new Scene(root, maxWidth, maxHeight, Color.WHITE);
        scene2 = new Scene(root2,maxWidth,maxHeight, Color.WHITE);
        scene3 = new Scene(root3,630,maxHeight, Color.WHITE);
        scene4 = new Scene(root4,1130,maxHeight, Color.WHITE);
      
        sceneReload(primaryStage,scene1);
	}
}