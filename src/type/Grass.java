package type;

import java.util.ArrayList;

public class Grass extends Type {

	@Override
	public String getName() {
		return "Grass";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Fire");
		weaknesses.add("Ice");
		weaknesses.add("Poison");
		weaknesses.add("Flying");
		weaknesses.add("Bug");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("Water");
		resistances.add("Grass");
		resistances.add("Electric");
		resistances.add("Ground");
		return resistances;
	}

	@Override
	public ArrayList<String> getImmunity() {
		ArrayList<String> immunities = new ArrayList<String>();
		immunities.add("");
		return immunities;
	}
}
