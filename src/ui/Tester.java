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
	
	public static double[] constsCalc(Pokedex pokedex,int[] battleMode)
	{
		double[] consts = new double[2];
		double complb = 0.0;
		double compub = 0.0;
		double mathlb = 0.0;
		double mathub = 0.0;
		
		ArrayList<ArrayList<String>> chart = CompAnalyzer.getChart(pokedex);
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		String[] missingNo = {"000","Missingno","0","0","0","0","0","0","Null","Null"};
		pokemon.add(missingNo);
		
		ArrayList<String[]> mathScores = MathAnalyzer.mathScores(battleMode, pokemon, pokedex);
		double mathscore;
		for(int i=0;i!=mathScores.size();i++)
		{
			mathscore = Double.parseDouble(mathScores.get(i)[10]);
			if(mathscore>mathub)
			{
				mathub=mathscore;
			}
			else if(mathscore<mathlb)
			{
				mathlb=mathscore;
			}
		}
		
		double compscore;
		for(int i=0;i!=chart.size();i++)
		{
			for(int j=0;j!=chart.get(i).size();j++)
			{
				compscore = Double.parseDouble(chart.get(i).get(j));
				if(compscore>compub)
				{
					compub=compscore;
				}
			}
		}
		System.out.println(consts[0]);
		System.out.println(consts[1]);
		return consts;
	}
	
	public static String[][] finalScores(int quantity, int[] battleMode, ArrayList<String[]> team, Pokedex pokedex)
	{
		double max = 0.9;
		double min = 0.1;
		double a1 = 5.0;
		double b1 = 0.0;
		double scale = (max/min+1)*Math.sqrt(max/min)*max;
		double center = (max/min)*Math.sqrt(max/min);
		double exponent = Math.log(max/min)/4;
		
		ArrayList<String[]> mathScores = MathAnalyzer.mathScores(battleMode, team, pokedex);
		int[] compScores = CompAnalyzer.compScores(pokedex, team);
		
		if(mathScores.size()==compScores.length)
		{
			for(int i=0;i!=mathScores.size();i++)
			{
				mathScores.get(i)[10]=Double.toString(((max+min)-scale/(center+Math.exp(2*exponent*team.size())))*Double.parseDouble(mathScores.get(i)[10])+(scale/(center+Math.exp(2*exponent*team.size())))*(a1*compScores[i]+b1));
			}
			mathScores = Pokedex.removeTeamandMegas(mathScores, team);
			String[][] finalScores = MathAnalyzer.sort(mathScores, quantity);
			return finalScores;
		}
		else
		{
			System.out.println("Error: score input sizes do not match");
			return null;
		}
	}

	public static void main(String[] args) {
		Pokedex pokedex = new BSPokedex();
		int[] battleMode = {1,1,1,1,1,1};
		
		ArrayList<String[]> otwimilt = new ArrayList<String[]>();
		String[] teamMember1=pokedex.exactSearch("Mega Sableye");
		String[] teamMember2=pokedex.exactSearch("Heatran");
		String[] teamMember3=pokedex.exactSearch("Therian Forme Landorus");
		String[] teamMember4=pokedex.exactSearch("Breloom");
		String[] teamMember5=pokedex.exactSearch("Wash Rotom");
		String[] teamMember6=pokedex.exactSearch("Cresselia");
		otwimilt.add(teamMember1);
		//otwimilt.add(teamMember2);
		//otwimilt.add(teamMember3);
		//otwimilt.add(teamMember4);
		//otwimilt.add(teamMember5);
		//otwimilt.add(teamMember6);
		
		int[] allTotals = {70, 79, 74, 73, 72, 69};
		int[] ouTotals = {100, 123, 103, 126, 106, 108};
		int[] uuTotals = {84, 96, 87, 93, 87, 83};
		int[] ruTotals = {77, 93, 86, 80, 86, 73};
		int[] nuTotals = {78, 87, 84, 77, 77, 72};
		int[] puTotals = {59, 64, 62, 58, 60, 57};
		
		int quantity = 10;
		//ArrayList<String[]> mathScores = MathAnalyzer.mathScores(battleMode,otwimilt,pokedex);
		//int[] compScores = CompAnalyzer.compScores(pokedex,otwimilt);
		String[][] finalScores = finalScores(quantity,battleMode,otwimilt,pokedex);
		for(int i=0;i!=quantity;i++)
		{
			//System.out.println(mathScores.get(i)[1]);
			//System.out.println(mathScores.get(i)[10]);
			//System.out.println(compScores[i]);
			System.out.println(finalScores[i][1]);
			System.out.println(finalScores[i][10]);
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