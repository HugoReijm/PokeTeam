package type;

import java.util.ArrayList;

public class Dragon extends Type {

	@Override
	public String getName() {
		return "Dragon";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Ice");
		weaknesses.add("Dragon");
		weaknesses.add("Fairy");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("Fire");
		resistances.add("Water");
		resistances.add("Grass");
		resistances.add("Electric");
		return resistances;
	}

	@Override
	public ArrayList<String> getImmunity() {
		ArrayList<String> immunities = new ArrayList<String>();
		immunities.add("");
		return immunities;
	}
}
