package type;

import java.util.ArrayList;

public class Ground extends Type {

	@Override
	public String getName() {
		return "Ground";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Water");
		weaknesses.add("Grass");
		weaknesses.add("Ice");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("Poison");
		resistances.add("Rock");
		return resistances;
	}

	@Override
	public ArrayList<String> getImmunity() {
		ArrayList<String> immunities = new ArrayList<String>();
		immunities.add("Electric");
		return immunities;
	}
}
