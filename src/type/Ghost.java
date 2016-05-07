package type;

import java.util.ArrayList;

public class Ghost extends Type {

	@Override
	public String getName() {
		return "Ghost";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Ghost");
		weaknesses.add("Dark");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("Poison");
		resistances.add("Bug");
		return resistances;
	}

	@Override
	public ArrayList<String> getImmunity() {
		ArrayList<String> immunities = new ArrayList<String>();
		immunities.add("Normal");
		immunities.add("Fighting");
		return immunities;
	}
}
