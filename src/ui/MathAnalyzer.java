package ui;

import java.util.ArrayList;
import mathData.Pokedex;
import stats.StatsAnalyzer;
import type.Null;
import type.Type;
import type.TypeAnalyzer;

public class MathAnalyzer 
{
	public static ArrayList<String[]> mathScores(int[] battleMode,ArrayList<String[]> pokemon, Pokedex pokedex)
	{
		ArrayList<String[]> pokeList = pokedex.getList();
		ArrayList<Type> types = new ArrayList<Type>();
		ArrayList<String[]> scores = new ArrayList<String[]>();
		int[] totalAverages = StatsAnalyzer.tierStats(StatsAnalyzer.tierSep(pokedex));
		int[] teamAverages = new int[6];
		double[] constants = calcConstants(battleMode,pokeList);
		double kStats = constants[0];
		double cStats = constants[1];
		
		for(int i=0;i!=pokemon.size();i++)
		{
			types.add(Type.toType(pokemon.get(i)[8]));
			types.add(Type.toType(pokemon.get(i)[9]));
		}
		ArrayList<ArrayList<Type>> origWRITable = TypeAnalyzer.wriTable(types);
		
		teamAverages[0]=StatsAnalyzer.hpAverage(pokemon);
		teamAverages[1]=StatsAnalyzer.attAverage(pokemon);
		teamAverages[2]=StatsAnalyzer.defAverage(pokemon);
		teamAverages[3]=StatsAnalyzer.spattAverage(pokemon);
		teamAverages[4]=StatsAnalyzer.spdefAverage(pokemon);
		teamAverages[5]=StatsAnalyzer.spdAverage(pokemon);
		
		for(int i=0;i!=pokeList.size();i++)
		{
			String[] score = new String[11];
			for(int j=0;j!=10;j++)
			{
				score[j]=pokeList.get(i)[j];
			}
			score[10]=Double.toString(TypeAnalyzer.typeScore(Type.toType(pokeList.get(i)[8]), Type.toType(pokeList.get(i)[9]), types,origWRITable)+kStats*StatsAnalyzer.statsScore(battleMode, teamAverages, totalAverages, pokeList.get(i))+cStats);
			scores.add(score);
		}
		
		//String[][] finalScores = sort(scores,quantity);
		//return finalScores;
		return scores;
	}
	
	public static double[] calcConstants(int[] battleMode,ArrayList<String[]> pokeList)
	{
		double[] constants = new double[2];
		double statslb = 0.0;
		double statsub = 0.0;
		double typeslb = 0.0;
		double typesub = 0.0;
		
		double tempStats;
		double tempTypes;
		
		ArrayList<Type> typeInput = new ArrayList<Type>();
		typeInput.add(new Null());
		int[] missingno = {0,0,0,0,0,0};
		ArrayList<ArrayList<Type>> origWRI = TypeAnalyzer.wriTable(typeInput);
		for(int i=0;i!= pokeList.size();i++)
		{
			tempTypes = TypeAnalyzer.typeScore(Type.toType(pokeList.get(i)[8]),Type.toType(pokeList.get(i)[9]),typeInput,origWRI);
			tempStats = StatsAnalyzer.statsScore(battleMode,missingno,StatsAnalyzer.tierStats(pokeList),pokeList.get(i));
			
			if(tempStats<statslb)
			{
				statslb=tempStats;
			}
			else if(tempStats>statsub)
			{
				statsub=tempStats;
			}
			else if(tempTypes<typeslb)
			{
				typeslb=tempTypes;
			}
			else if(tempTypes>typesub)
			{
				typesub=tempTypes;
			}
		}
		constants[0]=(typesub-typeslb)/(statsub-statslb);
		constants[1]=typeslb-constants[0]*statslb;
		return constants;
	}
	
	public static String[][] sort(ArrayList<String[]> tempScores,int quantity)
	{
		String[][] scores = new String[quantity][11];
		for(int i=0;i!=quantity;i++)
		{
			scores[i][10]="0";
		}
		
		for(int i=0;i!=tempScores.size();i++)
		{
			for(int j=0;j!=quantity;j++)
			{
				if(Double.parseDouble(tempScores.get(i)[10])>Double.parseDouble(scores[j][10]))
				{
					for(int k=quantity-1;k!=j;k--)
					{
						scores[k][0]=scores[k-1][0];
						scores[k][1]=scores[k-1][1];
						scores[k][2]=scores[k-1][2];
						scores[k][3]=scores[k-1][3];
						scores[k][4]=scores[k-1][4];
						scores[k][5]=scores[k-1][5];
						scores[k][6]=scores[k-1][6];
						scores[k][7]=scores[k-1][7];
						scores[k][8]=scores[k-1][8];
						scores[k][9]=scores[k-1][9];
						scores[k][10]=scores[k-1][10];
					}
					scores[j][0]=tempScores.get(i)[0];
					scores[j][1]=tempScores.get(i)[1];
					scores[j][2]=tempScores.get(i)[2];
					scores[j][3]=tempScores.get(i)[3];
					scores[j][4]=tempScores.get(i)[4];
					scores[j][5]=tempScores.get(i)[5];
					scores[j][6]=tempScores.get(i)[6];
					scores[j][7]=tempScores.get(i)[7];
					scores[j][8]=tempScores.get(i)[8];
					scores[j][9]=tempScores.get(i)[9];
					scores[j][10]=tempScores.get(i)[10];
					break;
				}
			}
		}
		return scores;
	}
}
