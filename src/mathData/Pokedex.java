package mathData;

import java.util.ArrayList;

public abstract class Pokedex {
	
	public abstract ArrayList<String[]> getList();
	
	public abstract String getTier();
	
	public abstract ArrayList<String[]> search(String nameID);
	
	public abstract String[] exactSearch(String nameID);
	
	public abstract boolean boolSearch(String nameID);
	
	//Be careful when searching: searching for Pokemon 020, for example, will yield Pokemon 16. Instead, you need to search just for Pokemon 20 
	public abstract ArrayList<String[]> idSearch(String id);
	
	public abstract String[] exactIDSearch(String id);
	
	public abstract ArrayList<String[]> typeSearch(String type);
	
	public abstract ArrayList<String[]> megaSearch();
	
	public abstract int location(String id);
	
	public static void alphabetic(ArrayList<String[]> pokedex)
	{
		String[] temp;
		
		for(int i=0;i!=pokedex.size();i++)
		{
			for(int j=i+1;j!=pokedex.size();j++)
			{
				if(pokedex.get(i)[1].compareTo(pokedex.get(j)[1])>0)
				{
					temp = pokedex.get(i);
					pokedex.set(i,pokedex.get(j));
					pokedex.set(j,temp);
				}
			}
		}
	}
	
	public static ArrayList<String[]> removeTeamandMegas(ArrayList<String[]> tempPokeList,ArrayList<String[]> team)
	{
		ArrayList<String[]> pokeList = new ArrayList<String[]>();
		for(int i=0;i!=tempPokeList.size();i++)
		{
			pokeList.add(tempPokeList.get(i));
		}
		
		int megas = 0;
		for(int i=0;i!=team.size();i++)
		{
			if(team.get(i)[0].contains("M"))
			{
				megas++;
			}
			for(int j=pokeList.size()-1;j!=-1;j--)
			{
				if(pokeList.get(j)[0].contains(team.get(i)[0].substring(0,3)))
				{
					pokeList.remove(pokeList.get(j));
				}
			}
		}
		
		if(megas>=1)
		{
			for(int j=pokeList.size()-1;j!=-1;j--)
			{
				if(pokeList.get(j)[0].contains("M"))
				{
					pokeList.remove(pokeList.get(j));
				}
			}
		}
		return pokeList;
	}
}