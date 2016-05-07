package type;

import java.util.ArrayList;

public class Ice extends Type {

	@Override
	public String getName() {
		return "Ice";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Fire");
		weaknesses.add("Fighting");
		weaknesses.add("Rock");
		weaknesses.add("Steel");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("Ice");
		return resistances;
	}

	@Override
	public ArrayList<String> getImmunity() {
		ArrayList<String> immunities = new ArrayList<String>();
		immunities.add("");
		return immunities;
	}
}
