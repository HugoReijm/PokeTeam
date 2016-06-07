package mathData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RUPokedex extends Pokedex{
	private String tier = "RU";
	private static ArrayList<String[]> ruPokeList=new ArrayList<String[]>();
	
	public RUPokedex(){
		try 
		{
			String currentDirectory = new File("").getAbsolutePath();
			FileReader inputPath = new FileReader(currentDirectory+"\\resources\\pokedex\\"+tier+".txt");
			BufferedReader bufRead = new BufferedReader(inputPath);
			String line = null;
			while((line=bufRead.readLine())!=null)
			{
				String[] pokemon = line.split(", ");
				pokemon[pokemon.length-1]=pokemon[pokemon.length-1].replace(";", "");
				ruPokeList.add(pokemon);
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
		
		return ruPokeList;
	}

	@Override
	public String getTier()
	{
		String tier = "RU";
		return tier;
	}
	
	@Override
	public ArrayList<String[]> search(String nameID) {
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		String name;
		for(int i=0;i!=ruPokeList.size();i++)
		{
			name=ruPokeList.get(i)[1];
			if(name.contains(nameID))
			{
				pokemon.add(ruPokeList.get(i));
				//break;
			}
		}
		return pokemon;
	}

	@Override
	public String[] exactSearch(String nameID)
	{
		String[] pokemon = new String[ruPokeList.get(0).length];
		String name;
		for(int i=0;i!=ruPokeList.size();i++)
		{
			name=ruPokeList.get(i)[1];
			if(name.equals(nameID))
			{
				pokemon=ruPokeList.get(i);
				//break;
			}
		}
		return pokemon;
	}
	
	@Override
	public boolean boolSearch(String nameID)
	{
		String name;
		for(int i=0;i!=ruPokeList.size();i++)
		{
			name=ruPokeList.get(i)[1];
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
		for(int i=0;i!=ruPokeList.size();i++)
		{
			number=ruPokeList.get(i)[0];
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
				pokemon.add(ruPokeList.get(i));
				//break;
			}
		}
		return pokemon;
	}

	@Override
	public String[] exactIDSearch(String id)
	{
		String[] pokemon = new String[ruPokeList.get(0).length];
		String number="";
		for(int i=0;i!=ruPokeList.size();i++)
		{
			number=ruPokeList.get(i)[0];
			if(id.equals(number))
			{
				pokemon=ruPokeList.get(i);
				//break;
			}
		}
		return pokemon;
	}
	
	@Override
	public ArrayList<String[]> typeSearch(String type) {
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		for(int i=0;i!=ruPokeList.size();i++)
		{
			if(ruPokeList.get(i)[8].equals(type)||ruPokeList.get(i)[9].equals(type))
			{
				pokemon.add(ruPokeList.get(i));
			}
		}
		return pokemon;
	}

	@Override
	public ArrayList<String[]> megaSearch() {
		ArrayList<String[]> megas = new ArrayList<String[]>();
		for(int i=0;i!=ruPokeList.size();i++)
		{
			if(ruPokeList.get(i)[0].contains("M"))
			{
				megas.add(ruPokeList.get(i));
			}
		}
		return megas;
	}

	@Override
	public int location(String id)
	{
		{
			int size=ruPokeList.size();
			for(int i=0;i!=size;i++)
			{
				if(ruPokeList.get(i)[0].equals(id))
				{
					return i;
				}
			}
			return -1;
		}
	}
}
