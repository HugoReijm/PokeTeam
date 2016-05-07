package type;

import java.util.ArrayList;

public class Flying extends Type {

	@Override
	public String getName() {
		return "Flying";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Electric");
		weaknesses.add("Ice");
		weaknesses.add("Rock");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("Grass");
		resistances.add("Fighting");
		resistances.add("Bug");
		return resistances;
	}

	@Override
	public ArrayList<String> getImmunity() {
		ArrayList<String> immunities = new ArrayList<String>();
		immunities.add("Ground");
		return immunities;
	}

}
