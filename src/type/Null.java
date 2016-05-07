package type;

import java.util.ArrayList;

public class Null extends Type {

	@Override
	public String getName() {
		return "Null";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("");
		return resistances;
	}

	@Override
	public ArrayList<String> getImmunity() {
		ArrayList<String> immunities = new ArrayList<String>();
		immunities.add("");
		return immunities;
	}
}
