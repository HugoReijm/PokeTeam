package ui;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public final class Pokemon extends StackPane{
	
    private HBox pokemon;
    private Box name;
    private Box hp;
    private Box att;
    private Box def;
    private Box spatt;
    private Box spdef;
    private Box spd;
    private Box type1;
    private Box type2;

    public Pokemon(String name,String hp,String att,String def,String spatt,String spdef,String spd,String type1,String type2)
    {
    	this.name = new Box(name,190,30);
    	this.hp = new Box(hp,40,30);
        this.att = new Box(att,40,30);
        this.def = new Box(def,40,30);
        this.spatt = new Box(spatt,50,30);
        this.spdef = new Box(spdef,50,30);
        this.spd = new Box(spd,40,30);
        this.type1 = new Box(type1,65,30);
        this.type2 = new Box(type2,65,30);
        
        DropShadow drop = new DropShadow(40, Color.WHITE);
        drop.setInput(new Glow());
        
        pokemon = new HBox(0);
        pokemon.getChildren().addAll(this.name,this.hp,this.att,this.def,this.spatt,this.spdef,this.spd,this.type1,this.type2);
        getChildren().add(pokemon);
    }
    
    public void setTextColor(Color color)
    {
    	name.setTextColor(color);
    	hp.setTextColor(color);
    	att.setTextColor(color);
    	def.setTextColor(color);
    	spatt.setTextColor(color);
    	spdef.setTextColor(color);
    	spd.setTextColor(color);
    	type1.setTextColor(color);
    	type2.setTextColor(color);
    }
}
