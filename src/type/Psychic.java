package type;

import java.util.ArrayList;

public class Psychic extends Type {

	@Override
	public String getName() {
		return "Psychic";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Bug");
		weaknesses.add("Ghost");
		weaknesses.add("Dark");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("Fighting");
		resistances.add("Psychic");
		return resistances;
	}

	@Override
	public ArrayList<String> getImmunity() {
		ArrayList<String> immunities = new ArrayList<String>();
		immunities.add("");
		return immunities;
	}
}
