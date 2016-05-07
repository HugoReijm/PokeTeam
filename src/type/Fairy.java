package type;

import java.util.ArrayList;

public class Fairy extends Type {

	@Override
	public String getName() {
		return "Fairy";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Poison");
		weaknesses.add("Steel");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("Fighting");
		resistances.add("Bug");
		resistances.add("Dark");
		return resistances;
	}

	@Override
	public ArrayList<String> getImmunity() {
		ArrayList<String> immunities = new ArrayList<String>();
		immunities.add("Dragon");
		return immunities;
	}
}
