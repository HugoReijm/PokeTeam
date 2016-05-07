package type;

import java.util.ArrayList;

public class Poison extends Type {

	@Override
	public String getName() {
		return "Poison";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Ground");
		weaknesses.add("Psychic");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("Grass");
		resistances.add("Fighting");
		resistances.add("Poison");
		resistances.add("Bug");
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
