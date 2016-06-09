package mathData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PUPokedex extends Pokedex{
	private String tier = "PU";
	private static ArrayList<String[]> puPokeList=new ArrayList<String[]>();
	
	public PUPokedex(){
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
				puPokeList.add(pokemon);
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
		
		return puPokeList;
	}

	@Override
	public String getTier()
	{
		String tier = "PU";
		return tier;
	}
	
	@Override
	public ArrayList<String[]> search(String nameID) {
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		String name;
		for(int i=0;i!=puPokeList.size();i++)
		{
			name=puPokeList.get(i)[1];
			if(name.contains(nameID))
			{
				pokemon.add(puPokeList.get(i));
				//break;
			}
		}
		return pokemon;
	}

	@Override
	public String[] exactSearch(String nameID)
	{
		String[] pokemon = new String[puPokeList.get(0).length];
		String name;
		for(int i=0;i!=puPokeList.size();i++)
		{
			name=puPokeList.get(i)[1];
			if(name.equals(nameID))
			{
				pokemon=puPokeList.get(i);
				//break;
			}
		}
		return pokemon;
	}
	
	@Override
	public boolean boolSearch(String nameID)
	{
		String name;
		for(int i=0;i!=puPokeList.size();i++)
		{
			name=puPokeList.get(i)[1];
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
		for(int i=0;i!=puPokeList.size();i++)
		{
			number=puPokeList.get(i)[0];
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
				pokemon.add(puPokeList.get(i));
				//break;
			}
		}
		return pokemon;
	}

	@Override
	public String[] exactIDSearch(String id)
	{
		String[] pokemon = new String[puPokeList.get(0).length];
		String number="";
		for(int i=0;i!=puPokeList.size();i++)
		{
			number=puPokeList.get(i)[0];
			if(id.equals(number))
			{
				pokemon=puPokeList.get(i);
				//break;
			}
		}
		return pokemon;
	}
	
	@Override
	public ArrayList<String[]> typeSearch(String type) {
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		for(int i=0;i!=puPokeList.size();i++)
		{
			if(puPokeList.get(i)[8].equals(type)||puPokeList.get(i)[9].equals(type))
			{
				pokemon.add(puPokeList.get(i));
			}
		}
		return pokemon;
	}

	@Override
	public int location(String id)
	{
		{
			int size=puPokeList.size();
			for(int i=0;i!=size;i++)
			{
				if(puPokeList.get(i)[0].equals(id))
				{
					return i;
				}
			}
			return -1;
		}
	}
}

