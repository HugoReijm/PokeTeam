package mathData;

import java.util.ArrayList;

public abstract class Pokedex {
	private static Pokedex agPokedex = new AGPokedex();
	private static Pokedex bsPokedex = new BSPokedex();
	private static Pokedex ubersPokedex = new UbersPokedex();
	private static Pokedex ouPokedex = new OUPokedex();
	private static Pokedex uuPokedex = new UUPokedex();
	private static Pokedex ruPokedex = new RUPokedex();
	private static Pokedex nuPokedex = new NUPokedex();
	private static Pokedex puPokedex = new PUPokedex();
	
	public abstract ArrayList<String[]> getList();
	
	public abstract String getTier();
	
	public abstract ArrayList<String[]> search(String nameID);
	
	public abstract String[] exactSearch(String nameID);
	
	public abstract boolean boolSearch(String nameID);
	
	public abstract ArrayList<String[]> idSearch(String id);
	
	public abstract String[] exactIDSearch(String id);
	
	public abstract ArrayList<String[]> typeSearch(String type);
	
	public abstract int location(String id);
	
	public static Pokedex toPokedex(String tier)
	{
		if(tier.equals("AG"))
		{
			return agPokedex;
		}
		else if(tier.equals("BS"))
		{
			return bsPokedex;
		}
		else if(tier.equals("Ubers"))
		{
			return ubersPokedex;
		}
		else if(tier.equals("OU"))
		{
			return ouPokedex;
		}
		else if(tier.equals("UU"))
		{
			return uuPokedex;
		}
		else if(tier.equals("RU"))
		{
			return ruPokedex;
		}
		else if(tier.equals("NU"))
		{
			return nuPokedex;
		}
		else if(tier.equals("PU"))
		{
			return puPokedex;
		}
		return null;
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
	
	public static ArrayList<String[]> inCommon(ArrayList<ArrayList<String[]>> arrays)
	{
		ArrayList<String[]> res = new ArrayList<String[]>();
		int size = arrays.size();
		
		if(size>0)
		{
			for(int i=0;i!=arrays.get(0).size();i++)
			{
				if(size>1)
				{
					if(arrays.get(1).contains(arrays.get(0).get(i)))
					{
						if(size>2)
						{
							if(arrays.get(2).contains(arrays.get(0).get(i)))
							{
								if(size>3)
								{
									if(arrays.get(3).contains(arrays.get(0).get(i)))
									{
										res.add(arrays.get(0).get(i));
									}
								}
								else
								{
									res.add(arrays.get(0).get(i));
								}
							}
						}
						else
						{
							res.add(arrays.get(0).get(i));
						}
					}
				}
				else
				{
					res.add(arrays.get(0).get(i));
				}
			}
		}
		return res;
	}
}