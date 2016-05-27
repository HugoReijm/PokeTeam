package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mathData.AGPokedex;
import mathData.Pokedex;

public class PokemonMaker 
{
	private static int[] battleMode;
    private static ArrayList<String[]> team;
    private static Pokedex pokedex;
	
	public PokemonMaker(Pokedex pokedex, ArrayList<String[]> team, int[] battleMode) 
    {
		this.pokedex=pokedex;
		this.team=team;
		this.battleMode=battleMode;
    }

	public static List<TeamMember> makeTeamList()
	{
		List<TeamMember> list = new ArrayList<TeamMember>();
		for(int i=0;i!=team.size();i++)
		{
			TeamMember p = new TeamMember(team.get(i)[1],team.get(i)[8],team.get(i)[9]);
			list.add(p);
		}
		return list;
	}
	
    public static List<Suggestion> makeSuggestionList()
    {
    	List<Suggestion> list = new ArrayList<Suggestion>(10);
    	String[][] scores = finalScores(10,pokedex,team,battleMode);
    	for(int i=0;i!=scores.length;i++)
    	{
    		double score1 = Math.round(Double.parseDouble(scores[i][10])*100)/100.0;
    		Suggestion p = new Suggestion(scores[i][1],score1,score1,score1);
    		list.add(p);
    	}
    	return list;
    }
    
    private static String[][] finalScores(int quantity, Pokedex pokedex, ArrayList<String[]> team, int[] battleMode)
	{
		double max = 0.9;
		double min = 0.1;
		double a1 = 5;
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
}
