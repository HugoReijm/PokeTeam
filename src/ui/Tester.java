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
import type.Fairy;
import type.Null;
import type.Psychic;
import type.Type;
import type.TypeAnalyzer;

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
	
	public static ArrayList<Double> statsScoreRanger(Pokedex database)
	{
		ArrayList<Double> scores=new ArrayList<Double>();
		ArrayList<String[]> pokedex=database.getList();
		int[] missingno = {0,0,0,0,0,0};
		int[] averages = {70, 79, 74, 73, 72, 69};
		for(int i=0;i!=pokedex.size();i++)
		{
			scores.add(StatsAnalyzer.statsScore(missingno, averages,pokedex.get(i)));
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
			scores.add(TypeAnalyzer.typeScore(Type.toType(pokedex.get(i)[8]),Type.toType(pokedex.get(i)[9]),types,origWRITable.get(0).size(),origWRITable.get(1).size()));
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
		ArrayList<Type> types0 = new ArrayList<Type>();
		ArrayList<Type> types1 = new ArrayList<Type>();
		Pokedex pokedex = new AGPokedex();
		types0.add(new Psychic());
		types0.add(new Fairy());
		types1.add(new Psychic());
		types1.add(new Fairy());
		
		double k1 = 1.0;
		double k2 = 0.02;
		int[] gard = {68, 65, 65, 125, 115, 80};
		int[] allTotals = {70, 79, 74, 73, 72, 69};
		int[] ouTotals = {100, 123, 103, 126, 106, 108};
		int[] uuTotals = {84, 96, 87, 93, 87, 83};
		int[] ruTotals = {77, 93, 86, 80, 86, 73};
		int[] nuTotals = {78, 87, 84, 77, 77, 72};
		int[] puTotals = {59, 64, 62, 58, 60, 57};
		int quantity = 10;
		//ArrayList<String[]> pokemonStats=StatsAnalyzer.bestScores(pokedex.getList("BattleSpot"), quantity, gard, totals);
		//String[][] pokemonTypes=TypeAnalyzer.bestScores(pokedex.getList("BattleSpot"), quantity, types1);
		//ArrayList<String[]> scores = StatsAnalyzer.mathScores(pokedex.idSearch(282),pokedex,TypeAnalyzer.wriTable(types0));
		/*for(int i=0;i!=quantity;i++)
		{
			System.out.println(scores.get(i)[1]);
			System.out.println(scores.get(i)[10]);
			//System.out.println(pokemonTypes[i][1]);
			//System.out.println(pokemonTypes[i][10]);
			System.out.println("");
		}*/
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