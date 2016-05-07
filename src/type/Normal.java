package type;

import java.util.ArrayList;

public class Normal extends Type {

	@Override
	public String getName() {
		return "Normal";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Fighting");
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
		immunities.add("Ghost");
		return immunities;
	}
}
