package type;

import java.util.ArrayList;

public class Rock extends Type {

	@Override
	public String getName() {
		return "Rock";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Water");
		weaknesses.add("Grass");
		weaknesses.add("Fighting");
		weaknesses.add("Ground");
		weaknesses.add("Steel");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("Normal");
		resistances.add("Fire");
		resistances.add("Poison");
		resistances.add("Flying");
		return resistances;
	}

	@Override
	public ArrayList<String> getImmunity() {
		ArrayList<String> immunities = new ArrayList<String>();
		immunities.add("");
		return immunities;
	}

}
