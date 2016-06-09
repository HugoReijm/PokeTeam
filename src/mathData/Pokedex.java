package mathData;

import java.util.ArrayList;

public abstract class Pokedex {
	
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
			return new AGPokedex();
		}
		else if(tier.equals("BS"))
		{
			return new BSPokedex();
		}
		else if(tier.equals("Ubers"))
		{
			return new UbersPokedex();
		}
		else if(tier.equals("OU"))
		{
			return new OUPokedex();
		}
		else if(tier.equals("UU"))
		{
			return new UUPokedex();
		}
		else if(tier.equals("RU"))
		{
			return new RUPokedex();
		}
		else if(tier.equals("NU"))
		{
			return new NUPokedex();
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