package mathData;

import java.util.ArrayList;

public abstract class Pokedex {
	
	public abstract ArrayList<String[]> getList();
	
	public abstract String getTier();
	
	public abstract ArrayList<String[]> search(String nameID);
	
	//Be careful when searching: searching for Pokemon 020, for example, will yield Pokemon 16. Instead, you need to search just for Pokemon 20 
	public abstract ArrayList<String[]> idSearch(int id);
	
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
}