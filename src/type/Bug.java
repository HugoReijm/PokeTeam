package type;

import java.util.ArrayList;

public class Bug extends Type {

	@Override
	public String getName() {
		return "Bug";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Fire");
		weaknesses.add("Flying");
		weaknesses.add("Rock");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("Grass");
		resistances.add("Fighting");
		resistances.add("Ground");
		return resistances;
	}

	@Override
	public ArrayList<String> getImmunity() {
		ArrayList<String> immunities = new ArrayList<String>();
		immunities.add("");
		return immunities;
	}

}
