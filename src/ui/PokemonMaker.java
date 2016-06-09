package ui;

import java.util.ArrayList;
import java.util.List;

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
    	String[][] scores = UI.finalScores(10,pokedex,team,battleMode);
    	for(int i=0;i!=scores.length;i++)
    	{
    		double typeScore = Math.round(Double.parseDouble(scores[i][10])*100)/100.0;
    		double statScore = Math.round(Double.parseDouble(scores[i][11])*100)/100.0;
    		double compScore = Integer.parseInt(scores[i][13]);
    		Suggestion p = new Suggestion(scores[i][1],typeScore,statScore,compScore);
    		list.add(p);
    	}
    	return list;
    }
}
