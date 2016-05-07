package type;

import java.util.ArrayList;

public class Fire extends Type{

	@Override
	public String getName() {
		return "Fire";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Water");
		weaknesses.add("Ground");
		weaknesses.add("Rock");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("Fire");
		resistances.add("Grass");
		resistances.add("Ice");
		resistances.add("Bug");
		resistances.add("Steel");
		resistances.add("Fairy");
		return resistances;
	}

	@Override
	public ArrayList<String> getImmunity() {
		ArrayList<String> immunities = new ArrayList<String>();
		immunities.add("");
		return immunities;
	}
}
