package type;

import java.util.ArrayList;

public abstract class Type {
	
	public abstract String getName();
	
	public abstract ArrayList<String> getWeakness();
	
	public abstract ArrayList<String> getResistance();
	
	public abstract ArrayList<String> getImmunity();
	
	public static Type toType(String type){
		if(type.equals("Bug"))
		{
			Bug bug = new Bug();
			return bug;
		}
		else if(type.equals("Dark"))
		{
			Dark dark = new Dark();
			return dark;
		}
		else if(type.equals("Dragon"))
		{
			Dragon dragon = new Dragon();
			return dragon;
		}
		else if(type.equals("Electric"))
		{
			Electric electric = new Electric();
			return electric;
		}
		else if(type.equals("Fairy"))
		{
			Fairy fairy = new Fairy();
			return fairy;
		}
		else if(type.equals("Fighting"))
		{
			Fighting fighting = new Fighting();
			return fighting;
		}
		else if(type.equals("Fire"))
		{
			Fire fire = new Fire();
			return fire;
		}
		else if(type.equals("Flying"))
		{
			Flying flying = new Flying();
			return flying;
		}
		else if(type.equals("Ghost"))
		{
			Ghost ghost = new Ghost();
			return ghost;
		}
		else if(type.equals("Grass"))
		{
			Grass grass = new Grass();
			return grass;
		}
		else if(type.equals("Ground"))
		{
			Ground ground = new Ground();
			return ground;
		}
		else if(type.equals("Ice"))
		{
			Ice ice = new Ice();
			return ice;
		}
		else if(type.equals("Normal"))
		{
			Normal normal = new Normal();
			return normal;
		}
		else if(type.equals("Null"))
		{
			Null nul = new Null();
			return nul;
		}
		else if(type.equals("Poison"))
		{
			Poison poison = new Poison();
			return poison;
		}
		else if(type.equals("Psychic"))
		{
			Psychic psychic = new Psychic();
			return psychic;
		}
		else if(type.equals("Rock"))
		{
			Rock rock = new Rock();
			return rock;
		}
		else if(type.equals("Steel"))
		{
			Steel steel = new Steel();
			return steel;
		}
		else if(type.equals("Water"))
		{
			Water water = new Water();
			return water;
		}
		else
		{
			return null;
		}
	}

}
