package type;

import java.util.ArrayList;

public class Fighting extends Type {

	@Override
	public String getName() {
		return "Fighting";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Flying");
		weaknesses.add("Psychic");
		weaknesses.add("Fairy");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("Bug");
		resistances.add("Rock");
		resistances.add("Dark");
		return resistances;
	}

	@Override
	public ArrayList<String> getImmunity() {
		ArrayList<String> immunities = new ArrayList<String>();
		immunities.add("");
		return immunities;
	}

}
