package ui;

import java.util.ArrayList;
import mathData.AGPokedex;
import mathData.BSPokedex;
import mathData.NUPokedex;
import mathData.OUPokedex;
import mathData.Pokedex;
import mathData.PUPokedex;
import mathData.RUPokedex;
import mathData.UbersPokedex;
import mathData.UUPokedex;

import stats.StatsAnalyzer;
import type.Dark;
import type.Dragon;
import type.Fairy;
import type.Fire;
import type.Flying;
import type.Ghost;
import type.Grass;
import type.Ground;
import type.Null;
import type.Psychic;
import type.Steel;
import type.Type;
import type.TypeAnalyzer;
import type.Water;

public class Tester {
	
	public static void lengthChecker(String[] pokemon){
		if(pokemon.length!=10)
		{
			System.out.println(pokemon[1]+" doesn't have the right length");
		}
	}
	
	public static void typeChecker(String[] pokemon){
		if(!pokemon[8].equals("Bug")&&!pokemon[8].equals("Dark")&&!pokemon[8].equals("Dragon")&&!pokemon[8].equals("Electric")&&!pokemon[8].equals("Fairy")&&!pokemon[8].equals("Fighting")&&!pokemon[8].equals("Fire")&&!pokemon[8].equals("Flying")&&!pokemon[8].equals("Ghost")&&!pokemon[8].equals("Grass")&&!pokemon[8].equals("Ground")&&!pokemon[8].equals("Ice")&&!pokemon[8].equals("Normal")&&!pokemon[8].equals("Null")&&!pokemon[8].equals("Poison")&&!pokemon[8].equals("Psychic")&&!pokemon[8].equals("Rock")&&!pokemon[8].equals("Steel")&&!pokemon[8].equals("Water")){
			System.out.println(pokemon[1]+" doesn't have the right first type");
		}
		if(!pokemon[9].equals("Bug")&&!pokemon[9].equals("Dark")&&!pokemon[9].equals("Dragon")&&!pokemon[9].equals("Electric")&&!pokemon[9].equals("Fairy")&&!pokemon[9].equals("Fighting")&&!pokemon[9].equals("Fire")&&!pokemon[9].equals("Flying")&&!pokemon[9].equals("Ghost")&&!pokemon[9].equals("Grass")&&!pokemon[9].equals("Ground")&&!pokemon[9].equals("Ice")&&!pokemon[9].equals("Normal")&&!pokemon[9].equals("Null")&&!pokemon[9].equals("Poison")&&!pokemon[9].equals("Psychic")&&!pokemon[9].equals("Rock")&&!pokemon[9].equals("Steel")&&!pokemon[9].equals("Water")){
			System.out.println(pokemon[1]+" doesn't have the right second type");
		}
	}
	
	public static void formChecker(String[] pokemon){
		if(pokemon[0].length()>3)
		{
			if(!pokemon[0].contains("M")&&!pokemon[0].contains("F"))
			{
				System.out.println(pokemon[1]);
			}
		}
	}
	
	public static ArrayList<Double> statsScoreRanger(int[] battleMode,Pokedex database)
	{
		ArrayList<Double> scores=new ArrayList<Double>();
		ArrayList<String[]> pokedex=database.getList();
		int[] missingno = {0,0,0,0,0,0};
		int[] averages = {70, 79, 74, 73, 72, 69};
		for(int i=0;i!=pokedex.size();i++)
		{
			scores.add(StatsAnalyzer.statsScore(battleMode,missingno, averages, pokedex.get(i)));
		}
		
		double temp;
		
		for(int i=0;i!=scores.size();i++)
		{
			for(int j=i+1;j!=scores.size();j++)
			{
				if(scores.get(i)>scores.get(j))
				{
					temp = scores.get(i);
					scores.set(i,scores.get(j));
					scores.set(j,temp);
				}
			}
		}
		return scores;
	}
	
	public static ArrayList<Double> typeScoreRanger(Pokedex database)
	{
		ArrayList<Double> scores=new ArrayList<Double>();
		ArrayList<String[]> pokedex=database.getList();
		ArrayList<Type> types = new ArrayList<Type>();
		types.add(new Null());
		types.add(new Null());
		ArrayList<ArrayList<Type>> origWRITable = TypeAnalyzer.wriTable(types);
		for(int i=0;i!=pokedex.size();i++)
		{
			scores.add(TypeAnalyzer.typeScore(Type.toType(pokedex.get(i)[8]),Type.toType(pokedex.get(i)[9]),types,origWRITable));
		}
		
		double temp;
		
		for(int i=0;i!=scores.size();i++)
		{
			for(int j=i+1;j!=scores.size();j++)
			{
				if(scores.get(i)>scores.get(j))
				{
					temp = scores.get(i);
					scores.set(i,scores.get(j));
					scores.set(j,temp);
				}
			}
		}
		return scores;
	}
	
	public static void main(String[] args) {
		Pokedex pokedex = new BSPokedex();
		//ArrayList<Type> types0 = new ArrayList<Type>();
		//types0.add(new Dark());
		//types0.add(new Ghost());
		//types0.add(new Fire());
		//types0.add(new Steel());
		//types0.add(new Fairy());
		//types0.add(new Steel());
		
		double k1 = 1.0;
		double k2 = 0.02;
		int[] battleMode = {1,3,1,3,1,3};
		//int[] sabl = {50, 85, 125, 85, 115, 20};
		
		ArrayList<String[]> otwimilt = new ArrayList<String[]>();
		String[] teamMember1=pokedex.exactSearch("Mega Salamence");
		String[] teamMember2=pokedex.exactSearch("Heatran");
		String[] teamMember3=pokedex.exactSearch("Therian Forme Landorus");
		String[] teamMember4=pokedex.exactSearch("Cloyster");
		String[] teamMember5=pokedex.exactSearch("Lucario");
		String[] teamMember6=pokedex.exactSearch("Gengar");
		otwimilt.add(teamMember1);
		otwimilt.add(teamMember2);
		otwimilt.add(teamMember3);
		otwimilt.add(teamMember4);
		otwimilt.add(teamMember5);
		otwimilt.add(teamMember6);
		
		int[] allTotals = {70, 79, 74, 73, 72, 69};
		int[] ouTotals = {100, 123, 103, 126, 106, 108};
		int[] uuTotals = {84, 96, 87, 93, 87, 83};
		int[] ruTotals = {77, 93, 86, 80, 86, 73};
		int[] nuTotals = {78, 87, 84, 77, 77, 72};
		int[] puTotals = {59, 64, 62, 58, 60, 57};
		
		int quantity = 10;
		//String[][] pokemonStats=StatsAnalyzer.bestScores(battleMode,pokedex.getList(), quantity, sabl, allTotals);
		//String[][] pokemonTypes=TypeAnalyzer.bestScores(pokedex.getList(), quantity, types0);
		String[][] scores = MathAnalyzer.mathScores(battleMode,otwimilt,pokedex,quantity);
		for(int i=0;i!=quantity;i++)
		{
			System.out.println(scores[i][1]);
			System.out.println(scores[i][10]);
			//System.out.println(pokemonTypes[i][1]);
			//System.out.println(pokemonTypes[i][10]);
			//System.out.println(pokemonStats[i][1]);
			//System.out.println(pokemonStats[i][10]);
			System.out.println("");
		}
		
		/*ArrayList<Double> stats = statsScoreRanger(pokedex);
		System.out.println("Stats Start");
		for(int i=0;i!=stats.size();i++)
		{
			System.out.println(stats.get(i));
		}*/
		/*ArrayList<Double> types = typeScoreRanger(pokedex);
		System.out.println("Type Start");
		for(int i=0;i!=types.size();i++)
		{
			System.out.println(types.get(i));
		}*/
	}
}