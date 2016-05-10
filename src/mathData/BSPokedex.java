package mathData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BSPokedex extends Pokedex {
	private String tier = "BattleSpot";
	private static ArrayList<String[]> bsPokeList=new ArrayList<String[]>();
	
	public BSPokedex(){
		try 
		{
			//FileReader inputPath = new FileReader("interfaz","pokedex",tier+".txt");
			FileReader inputPath = new FileReader("C:\\Users\\Hugo\\Desktop\\BEP\\BEP\\PokeTeam\\src\\mathData\\pokedex\\"+tier+".txt");
			BufferedReader bufRead = new BufferedReader(inputPath);
			String line = null;
			while((line=bufRead.readLine())!=null)
			{
				String[] pokemon = line.split(", ");
				pokemon[pokemon.length-1]=pokemon[pokemon.length-1].replace(";", "");
				bsPokeList.add(pokemon);
			}
			bufRead.close();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<String[]> getList() {
		return bsPokeList;
	}

	@Override
	public String getTier()
	{
		String tier = "BattleSpot";
		return tier;
	}
	
	@Override
	public ArrayList<String[]> search(String nameID) {
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		String name;
		for(int i=0;i!=bsPokeList.size();i++)
		{
			name=bsPokeList.get(i)[1];
			if(name.contains(nameID))
			{
				pokemon.add(bsPokeList.get(i));
				//break;
			}
		}
		return pokemon;
	}

	@Override
	public String[] exactSearch(String nameID)
	{
		String[] pokemon = new String[bsPokeList.get(0).length];
		String name;
		for(int i=0;i!=bsPokeList.size();i++)
		{
			name=bsPokeList.get(i)[1];
			if(name.equals(nameID))
			{
				pokemon=bsPokeList.get(i);
				//break;
			}
		}
		return pokemon;
	}
	
	@Override
	public boolean boolSearch(String nameID)
	{
		String name;
		for(int i=0;i!=bsPokeList.size();i++)
		{
			name=bsPokeList.get(i)[1];
			if(name.equals(nameID))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayList<String[]> idSearch(String id) {
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		String number="";
		for(int i=0;i!=bsPokeList.size();i++)
		{
			number=bsPokeList.get(i)[0];
			if(number.contains("M"))
			{
				number=number.substring(0,number.indexOf("M"));
			}
			if(number.contains("F"))
			{
				number=number.substring(0,number.indexOf("F"));
			}
			
			if(id.equals(number))
			{
				pokemon.add(bsPokeList.get(i));
				//break;
			}
		}
		return pokemon;
	}

	@Override
	public String[] exactIDSearch(String id)
	{
		String[] pokemon = new String[bsPokeList.get(0).length];
		String number="";
		for(int i=0;i!=bsPokeList.size();i++)
		{
			number=bsPokeList.get(i)[0];
			if(id.equals(number))
			{
				pokemon=bsPokeList.get(i);
				//break;
			}
		}
		return pokemon;
	}
	
	@Override
	public ArrayList<String[]> typeSearch(String type) {
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		for(int i=0;i!=bsPokeList.size();i++)
		{
			if(bsPokeList.get(i)[8].equals(type)||bsPokeList.get(i)[9].equals(type))
			{
				pokemon.add(bsPokeList.get(i));
			}
		}
		return pokemon;
	}

	@Override
	public ArrayList<String[]> megaSearch() {
		ArrayList<String[]> megas = new ArrayList<String[]>();
		for(int i=0;i!=bsPokeList.size();i++)
		{
			if(bsPokeList.get(i)[0].contains("M"))
			{
				megas.add(bsPokeList.get(i));
			}
		}
		return megas;
	}
	
	@Override
	public int location(String id)
	{
		{
			int size=bsPokeList.size();
			for(int i=0;i!=size;i++)
			{
				if(bsPokeList.get(i)[0].equals(id))
				{
					return i;
				}
			}
			return -1;
		}
	}
}
