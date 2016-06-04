package ui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
    private String tier;
	
    private static AbstractMenu menu;
    private static AbstractMenu tierMenu;
    private static AbstractMenu pokedexMenu;
    private static AbstractMenu builderMenu;
    
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

    public static Scene getBuilderMenuScene()
    {
    	return scene4;
    }
    
    public static UI getInstance()
    {
    	return instance;
    }
    
    public String getTier()
    {
    	return instance.tier;
    }
    
    public void setTier(String tier)
    {
    	instance.tier=tier;
    }
    
    public static void sceneReload(Stage stage, Scene scene)
    {
    	stage.setScene(scene);
    	stage.show();
    }
    
    public static void main(final String[] args) {
    	launch(args);
    }
    
	@Override
	public void start(final Stage stage) throws Exception{
		primaryStage.setTitle("PokeTeam Builder");
        root = new Group();
        root2 = new Group();
        root3 = new Group();
        root4 = new Group();
        scene1 = new Scene(root, maxWidth, maxHeight, Color.WHITE);
        scene2 = new Scene(root2,maxWidth,maxHeight, Color.WHITE);
        scene3 = new Scene(root3,630,maxHeight, Color.WHITE);
        scene4 = new Scene(root4,900,maxHeight, Color.WHITE);
        sceneReload(primaryStage,scene1);
        
        menu = new Menu();
        tierMenu = new TierMenu();
        pokedexMenu = new PokedexMenu();
        builderMenu = new BuilderMenu();
        root.getChildren().add(menu);
        root2.getChildren().add(tierMenu);
        root3.getChildren().add(pokedexMenu);
        root4.getChildren().add(builderMenu);
        
        secondaryStage.setTitle("Pokedex");
	}
}