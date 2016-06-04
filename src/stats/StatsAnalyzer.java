package stats;

import java.util.ArrayList;

import mathData.AGPokedex;
import mathData.BSPokedex;
import mathData.NUPokedex;
import mathData.OUPokedex;
import mathData.PUPokedex;
import mathData.Pokedex;
import mathData.RUPokedex;
import mathData.UUPokedex;
import mathData.UbersPokedex;
import type.Type;
import type.TypeAnalyzer;

public class StatsAnalyzer {

	public static int hpAverage(ArrayList<String[]> pokemon)
	{
		int average = 0;
		for(int i=0;i!=pokemon.size();i++)
		{
			average=average+Integer.parseInt(pokemon.get(i)[2]);
		}
		return (int)Math.round(average/pokemon.size());
	}
	
	public static int attAverage(ArrayList<String[]> pokemon)
	{
		int average = 0;
		for(int i=0;i!=pokemon.size();i++)
		{
			average=average+Integer.parseInt(pokemon.get(i)[3]);
		}
		return (int)Math.round(average/pokemon.size());
	}
	
	public static int defAverage(ArrayList<String[]> pokemon)
	{
		int average = 0;
		for(int i=0;i!=pokemon.size();i++)
		{
			average=average+Integer.parseInt(pokemon.get(i)[4]);
		}
		return (int)Math.round(average/pokemon.size());
	}
	
	public static int spattAverage(ArrayList<String[]> pokemon)
	{
		int average = 0;
		for(int i=0;i!=pokemon.size();i++)
		{
			average=average+Integer.parseInt(pokemon.get(i)[5]);
		}
		return (int)Math.round(average/pokemon.size());
	}
	
	public static int spdefAverage(ArrayList<String[]> pokemon)
	{
		int average = 0;
		for(int i=0;i!=pokemon.size();i++)
		{
			average=average+Integer.parseInt(pokemon.get(i)[6]);
		}
		return (int)Math.round(average/pokemon.size());
	}
	
	public static int spdAverage(ArrayList<String[]> pokemon)
	{
		int average = 0;
		for(int i=0;i!=pokemon.size();i++)
		{
			average=average+Integer.parseInt(pokemon.get(i)[7]);
		}
		return (int)Math.round(average/pokemon.size());
	}
	
	public static ArrayList<String[]> tierSep(Pokedex pokedex)
	{
		String tier1=pokedex.getTier();
		ArrayList<String[]> pokemon1 = new ArrayList<String[]>();
		pokemon1.addAll(pokedex.getList());
		ArrayList<String[]> pokemon2 = new ArrayList<String[]>();
		
		if (tier1.equals("AG"))
		{
			pokemon2.addAll(new OUPokedex().getList());
		}
		else if(tier1.equals("BattleSpot"))
		{
			pokemon2.addAll(new UUPokedex().getList());
		}
		else if (tier1.equals("Ubers"))
		{
			pokemon2.addAll(new OUPokedex().getList());
		}
		else if (tier1.equals("OU"))
		{
			pokemon2.addAll(new UUPokedex().getList());
		}
		else if (tier1.equals("UU"))
		{
			pokemon2.addAll(new RUPokedex().getList());
		}
		else if (tier1.equals("RU"))
		{
			pokemon2.addAll(new NUPokedex().getList());
		}
		else if (tier1.equals("NU"))
		{
			pokemon2.addAll(new PUPokedex().getList());
		}
		else if (tier1.equals("PU"))
		{
			String[] missingno = {"000","Missingno","0","0","0","0","0","0","Null","Null"}; 
			pokemon2.add(missingno);
		}
		else 
		{
			System.out.println("Please Input a Valid Tier");
			return null;
		}
		
		for (int i=pokemon1.size()-1;i!=-1;i--)
		{
			for(int j=pokemon2.size()-1;j!=-1;j--)
			{
				if(pokemon1.get(i)[0].equals(pokemon2.get(j)[0]))
				{
					pokemon1.remove(pokemon1.get(i));
					break;
				}
			}
		}
		return pokemon1;
	}
	
	public static int[] tierStats(ArrayList<String[]> pokedex)
	{
		int[] statsAverages = new int[6];
		statsAverages[0]=StatsAnalyzer.hpAverage(pokedex);
		statsAverages[1]=StatsAnalyzer.attAverage(pokedex);
		statsAverages[2]=StatsAnalyzer.defAverage(pokedex);
		statsAverages[3]=StatsAnalyzer.spattAverage(pokedex);
		statsAverages[4]=StatsAnalyzer.spdefAverage(pokedex);
		statsAverages[5]=StatsAnalyzer.spdAverage(pokedex);
		return statsAverages;
	}
	
	public static double statsScore(int[] battleMode, ArrayList<String[]> team, int[] totalAverages, String[] pokemon)
	{	
		ArrayList<String[]> tempTeam = new ArrayList<String[]>();
		tempTeam.addAll(team);
		tempTeam.add(pokemon);
		
		int[] teamAverages = new int[6];
		teamAverages[0]=hpAverage(tempTeam);
		teamAverages[1]=attAverage(tempTeam);
		teamAverages[2]=defAverage(tempTeam);
		teamAverages[3]=spattAverage(tempTeam);
		teamAverages[4]=spdefAverage(tempTeam);
		teamAverages[5]=spdAverage(tempTeam);
				
		int hpFactor = teamAverages[0]-totalAverages[0];
		int attFactor = teamAverages[1]-totalAverages[1];
		int defFactor = teamAverages[2]-totalAverages[2];
		int spattFactor = teamAverages[3]-totalAverages[3];
		int spdefFactor = teamAverages[4]-totalAverages[4];
		int spdFactor = teamAverages[5]-totalAverages[5];
		
		return (battleMode[0]*hpFactor)+(battleMode[1]*attFactor)+(battleMode[2]*defFactor)+(battleMode[3]*spattFactor)+(battleMode[4]*spdefFactor)+(battleMode[5]*spdFactor);
	}
	
	public static String[][] bestScores(int[] battleMode, ArrayList<String[]> pokedex, int quantity, ArrayList<String[]> team, int[] totalAverages)
	{
		String[][] scores = new String[quantity][11];
		for(int i=0;i!=quantity;i++)
		{
			scores[i][10]="0.0";
		}
		
		for(int i=0;i!=pokedex.size();i++)
		{
			for(int j=0;j!=quantity;j++)
			{
				double statsScore = statsScore(battleMode, team, totalAverages, pokedex.get(i));
				if(statsScore>Double.parseDouble(scores[j][10]))
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
					scores[j][0]=pokedex.get(i)[0];
					scores[j][1]=pokedex.get(i)[1];
					scores[j][2]=pokedex.get(i)[2];
					scores[j][3]=pokedex.get(i)[3];
					scores[j][4]=pokedex.get(i)[4];
					scores[j][5]=pokedex.get(i)[5];
					scores[j][6]=pokedex.get(i)[6];
					scores[j][7]=pokedex.get(i)[7];
					scores[j][8]=pokedex.get(i)[8];
					scores[j][9]=pokedex.get(i)[9];
					scores[j][10]=Double.toString(statsScore);
					break;
				}
			}
		}
		return scores;
	}
}
