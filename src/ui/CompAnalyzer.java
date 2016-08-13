package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import mathData.Pokedex;

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
	
	private static void reader(Pokedex pokedex)
	{
		try
		{
			chart.clear();
			String tier = pokedex.getTier();
			FileReader inputPath = new FileReader("resources/tables/"+tier+"data.txt");
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
}
