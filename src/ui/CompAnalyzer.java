package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import mathData.AGPokedex;
import mathData.BSPokedex;
import mathData.OUPokedex;
import mathData.Pokedex;
import mathData.UbersPokedex;
import stats.StatsAnalyzer;
import type.Null;
import type.Type;
import type.TypeAnalyzer;

public class CompAnalyzer 
{
	private static ArrayList<ArrayList<String>> chart=new ArrayList<ArrayList<String>>();
	
	public static ArrayList<ArrayList<String>> getChart(Pokedex pokedex)
	{
		reader(pokedex);
		return chart;
	}
	
	public static int[] compScores(Pokedex pokedex,ArrayList<String[]> team)
	{
		reader(pokedex);
		int[] teamPreferences = new int[chart.get(0).size()];
		for(int i=0;i!=team.size();i++)
		{
			int l=pokedex.location(team.get(i)[0]);
			for(int j=0;j!=chart.get(l).size();j++)
			{
				if(Integer.parseInt(chart.get(l).get(j))>0)
				{
					teamPreferences[j]=teamPreferences[j]+Integer.parseInt(chart.get(l).get(j));
				}
			}
		}
		return teamPreferences;
	}
	
	public static void reader(Pokedex pokedex)
	{
		try
		{
			chart.clear();
			String tier = pokedex.getTier();
			FileReader inputPath = new FileReader("C:\\Users\\Hugo\\Desktop\\BEP\\BEP\\PokeTeam\\src\\compData\\tables\\"+tier+"data.txt");
			BufferedReader bufRead = new BufferedReader(inputPath);
			String line = null;
			while((line=bufRead.readLine())!=null)
			{
				ArrayList<String> row = new ArrayList<String>();
				String[] pokemon = line.split(",");
				pokemon[pokemon.length-1]=pokemon[pokemon.length-1].replace(";", "");
				for(int i=0;i!=pokemon.length;i++)
				{
					row.add(pokemon[i]);
				}
				chart.add(row);
			}
			bufRead.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		Pokedex pokedex=new OUPokedex();
		
		ArrayList<String[]> otwimilt = new ArrayList<String[]>();
		String[] teamMember1=pokedex.exactSearch("Mega Sableye");
		String[] teamMember2=pokedex.exactSearch("Heatran");
		otwimilt.add(teamMember1);
		otwimilt.add(teamMember2);
		
		int[] p=compScores(pokedex,otwimilt);
		int l=pokedex.location(otwimilt.get(0)[0]);
		System.out.println("Start");
		for(int i=0;i!=p.length;i++)
		{
			if(p[i]!=0)
			{
				System.out.println(pokedex.getList().get(i)[1]+": "+p[i]);
			}
		}
	}
}
