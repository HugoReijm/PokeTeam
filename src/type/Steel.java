package type;

import java.util.ArrayList;

public class Steel extends Type {

	@Override
	public String getName() {
		return "Steel";
	}
	
	@Override
	public ArrayList<String> getWeakness() {
		ArrayList<String> weaknesses = new ArrayList<String>();
		weaknesses.add("Fire");
		weaknesses.add("Fighting");
		weaknesses.add("Ground");
		return weaknesses;
	}

	@Override
	public ArrayList<String> getResistance() {
		ArrayList<String> resistances = new ArrayList<String>();
		resistances.add("Normal");
		resistances.add("Grass");
		resistances.add("Ice");
		resistances.add("Flying");
		resistances.add("Psychic");
		resistances.add("Bug");
		resistances.add("Rock");
		resistances.add("Dragon");
		resistances.add("Steel");
		resistances.add("Fairy");
		return resistances;
	}

	@Override
	public ArrayList<String> getImmunity() {
		ArrayList<String> immunities = new ArrayList<String>();
		immunities.add("Poison");
		return immunities;
	}
}
