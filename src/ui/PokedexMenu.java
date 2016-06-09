package ui;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import mathData.Pokedex;
import ui.InputField;
import ui.MenuLabel;

public class PokedexMenu extends AbstractMenu {
	private int uniformWidth=200;
	private boolean fromMenu=false;
	private MenuLabel tierLabel;
	private MenuButton btnSearch;
    private InputField nameIn;
    private VBox nameInput;
    private InputField numberIn;
    private VBox numberInput;
    private InputField type1In;
    private VBox type1Input;
    private InputField type2In;
    private VBox type2Input;
    private VBox resDisplay;
    private MenuLabel resLabel;
    private Pokemon resLabels;
    private ObservableList<Pokemon> observableList;
    private ListView<Pokemon> resScroll;
    private MenuButton btnBacktoTier;
	private final String menuImagePath = "file:resources/kalosPokedex3.jpg";
    private ImageView menuImage;
    
    private Pokedex pokedex;
    private List<Pokemon> results = new ArrayList<Pokemon>();
    
    public PokedexMenu()
    {
    	menuImage = new ImageView(new Image(menuImagePath));
		menuImage.setOpacity(0.40);
    	menuImage.setFitHeight(700);
    	menuImage.setFitWidth(630);

    	VBox menu = new VBox(8);
        menu.setTranslateX(215);
        menu.setTranslateY(0);
        
        tierLabel = new MenuLabel("",200);
        nameInput=NameInput();
        numberInput=NumberInput();
        type1Input=Type1Input();
        type2Input=Type2Input();
        
        resDisplay=ResDisplay();
        resDisplay.setTranslateX(10);
        resDisplay.setTranslateY(350);
        
        btnSearch = new MenuButton("Search!",uniformWidth);
        btnSearch.setOnMouseClicked(event -> {
                clickSearchButton();
            });
        
        btnBacktoTier = new MenuButton("Back",75);
        btnBacktoTier.setOnMouseClicked(event -> {
            clickBackButton();
        });
        
        menu.getChildren().addAll(tierLabel,nameInput,numberInput,type1Input,type2Input,btnSearch);
        getChildren().addAll(menuImage,btnBacktoTier,menu,resDisplay);
    }
    
    public void setPokedex(Pokedex pokedex, boolean fromMenu)
    {
    	this.fromMenu=fromMenu;
    	this.pokedex = pokedex;
    	tierLabel.setText(pokedex.getTier());
    }
    
    private void clickSearchButton()
    {
    	ArrayList<ArrayList<String[]>> resArrays = new ArrayList<ArrayList<String[]>>();
    	boolean nameBool=false;
    	boolean numberBool=false;
    	boolean type1Bool=false;
    	boolean type2Bool=false;
    	
    	if(nameIn.getInput()!=null&&!nameIn.getInput().trim().isEmpty())
    	{
    		if(nameIn.getInput().equals("Please Search a Name"))
    		{
    			nameIn.setInput("");
    		}
    		else
    		{
    			resArrays.add(pokedex.search(nameIn.getInput()));
    			nameBool=true;
    		}
    	}
    	if(numberIn.getInput()!=null&&!numberIn.getInput().trim().isEmpty())
    	{
    		if(numberIn.getInput().equals("or an ID Number"))
    		{
    			numberIn.setInput("");
    		}
    		else
    		{
    			resArrays.add(pokedex.idSearch(numberIn.getInput()));
    			numberBool=true;
    		}
    	}
    	if(type1In.getInput()!=null&&!type1In.getInput().trim().isEmpty())
    	{
    		if(type1In.getInput().equals("or a Type"))
    		{
    			type1In.setInput("");
    		}
    		else
    		{
    			resArrays.add(pokedex.typeSearch(type1In.getInput()));
    			type1Bool=true;
    		}
    	}
    	if(type2In.getInput()!=null&&!type2In.getInput().trim().isEmpty())
		{
			if(type2In.getInput().equals("or a Type"))
			{
				type2In.setInput("");
			}
			else
			{
				resArrays.add(pokedex.typeSearch(type2In.getInput()));
				type2Bool=true;
			}
		}
    	if(!nameBool&&!numberBool&&!type1Bool&&!type2Bool)
    	{
    		nameIn.setInput("Please Search a Name");
    		numberIn.setInput("or an ID Number");
    		type1In.setInput("or a Type");
    		type2In.setInput("or a Type");
    	}
    	
    	ArrayList<String[]> inCommon = Pokedex.inCommon(resArrays);
    	results.clear();
    	results.add(resLabels);
    	results.addAll(arraytoList(inCommon));
    	resultReload();
    }
    
    private void clickBackButton()
    {
    	if(fromMenu)
    	{
        	reset();
        	UI.reset("BuilderMenu");
    		UI.sceneReload(UI.getPrimaryStage(),UI.getMenuScene());
    	}
    	else
    	{
        	reset();
        	UI.reset("BuilderMenu");
    		UI.sceneReload(UI.getPrimaryStage(),UI.getTierMenuScene());
	    	UI.turnOffStage(UI.getSecondaryStage());
    	}
    }
    
    private VBox NameInput() 
    {
        VBox input = new VBox(0);
        MenuLabel plname = new MenuLabel("Name", uniformWidth);
        nameIn = new InputField(uniformWidth);
        input.getChildren().addAll(plname, nameIn);
        return input;
    }
    
    private VBox NumberInput()
    {
    	VBox input = new VBox(0);
        MenuLabel plname = new MenuLabel("National Pokedex Number", uniformWidth);
        numberIn = new InputField(uniformWidth);
        input.getChildren().addAll(plname, numberIn);
        return input;
    }
    
    private VBox Type1Input()
    {
    	VBox input = new VBox(0);
        MenuLabel plname = new MenuLabel("Type #1", uniformWidth);
        type1In = new InputField(uniformWidth);
        input.getChildren().addAll(plname, type1In);
        return input;
    }
    
    private VBox Type2Input()
    {
    	VBox input = new VBox(0);
        MenuLabel plname = new MenuLabel("Type #2", uniformWidth);
        type2In = new InputField(uniformWidth);
        input.getChildren().addAll(plname, type2In);
        return input;
    }
    
    private VBox ResDisplay()
    {
    	VBox resBox = new VBox(0);
    	resLabel = new MenuLabel("Results",610);
    	
    	resScroll = new ListView<Pokemon>();
    	observableList = FXCollections.observableList(results);
    	resScroll.setItems(observableList);
    	resScroll.setPrefWidth(610);
    	resScroll.setPrefHeight(310);

    	resLabels = new Pokemon("Name","HP","Atk","Def","Sp.Atk","Sp.Def","Spd","Type #1","Type #2");
        resLabels.setTextColor(Color.AQUA);
    	results.add(resLabels);
        resultReload();
    	
    	resBox.getChildren().addAll(resLabel,resScroll);
        return resBox;
    }
    
    public void reset()
	{
    	fromMenu=false;
    	nameIn.setInput("");
    	numberIn.setInput("");
    	type1In.setInput("");
    	type2In.setInput("");
    	
    	results.clear();
    	results.add(resLabels);
    	observableList=FXCollections.observableList(results);
    	resScroll.setItems(observableList);
	}
    
    private void resultReload()
    {
    	nameIn.setInput("");
    	numberIn.setInput("");
    	type1In.setInput("");
    	type2In.setInput("");
    	observableList=FXCollections.observableList(results);
    	resScroll.setItems(observableList);
    }
    
    private List<Pokemon> arraytoList(ArrayList<String[]> resArray)
    {
    	List<Pokemon> resList = new ArrayList<Pokemon>();
    	for(int i=0;i!=resArray.size();i++)
    	{
    		Pokemon p = new Pokemon(resArray.get(i)[1],resArray.get(i)[2],resArray.get(i)[3],resArray.get(i)[4],resArray.get(i)[5],resArray.get(i)[6],resArray.get(i)[7],resArray.get(i)[8],resArray.get(i)[9]);
    		resList.add(p);
    	}
        return resList;
    }
}
