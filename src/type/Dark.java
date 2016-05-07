package type;

import java.util.ArrayList;

public class Dark extends Type {

	@Override
	public String getName() {
		return "Dark";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Fighting");
		weaknesses.add("Bug");
		weaknesses.add("Fairy");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("Ghost");
		resistances.add("Dark");
		return resistances;
	}

	@Override
	public ArrayList<String> getImmunity() {
		ArrayList<String> immunities = new ArrayList<String>();
		immunities.add("Psychic");
		return immunities;
	}
}
