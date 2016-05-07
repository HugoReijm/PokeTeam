package type;

import java.util.ArrayList;

public class Water extends Type {

	@Override
	public String getName() {
		return "Water";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Grass");
		weaknesses.add("Electric");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("Fire");
		resistances.add("Water");
		resistances.add("Ice");
		resistances.add("Steel");
		return resistances;
	}

	@Override
	public ArrayList<String> getImmunity() {
		ArrayList<String> immunities = new ArrayList<String>();
		immunities.add("");
		return immunities;
	}
}
