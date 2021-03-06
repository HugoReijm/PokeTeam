package mathData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NUPokedex extends Pokedex {
	private String tier = "NU";
	private static ArrayList<String[]> nuPokeList=new ArrayList<String[]>();
	
	public NUPokedex(){
		try 
		{
			FileReader inputPath = new FileReader("resources/pokedex/"+tier+".txt");
			BufferedReader bufRead = new BufferedReader(inputPath);
			String line = null;
			while((line=bufRead.readLine())!=null)
			{
				String[] pokemon = line.split(", ");
				pokemon[pokemon.length-1]=pokemon[pokemon.length-1].replace(";", "");
				nuPokeList.add(pokemon);
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
		
		return nuPokeList;
	}

	@Override
	public String getTier()
	{
		String tier = "NU";
		return tier;
	}
	
	@Override
	public ArrayList<String[]> search(String nameID) {
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		String name;
		for(int i=0;i!=nuPokeList.size();i++)
		{
			name=nuPokeList.get(i)[1];
			if(name.contains(nameID))
			{
				pokemon.add(nuPokeList.get(i));
				//break;
			}
		}
		return pokemon;
	}

	@Override
	public String[] exactSearch(String nameID)
	{
		String[] pokemon = new String[nuPokeList.get(0).length];
		String name;
		for(int i=0;i!=nuPokeList.size();i++)
		{
			name=nuPokeList.get(i)[1];
			if(name.equals(nameID))
			{
				pokemon=nuPokeList.get(i);
				//break;
			}
		}
		return pokemon;
	}
	
	@Override
	public boolean boolSearch(String nameID)
	{
		String name;
		for(int i=0;i!=nuPokeList.size();i++)
		{
			name=nuPokeList.get(i)[1];
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
		for(int i=0;i!=nuPokeList.size();i++)
		{
			number=nuPokeList.get(i)[0];
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
				pokemon.add(nuPokeList.get(i));
				//break;
			}
		}
		return pokemon;
	}

	@Override
	public String[] exactIDSearch(String id)
	{
		String[] pokemon = new String[nuPokeList.get(0).length];
		String number="";
		for(int i=0;i!=nuPokeList.size();i++)
		{
			number=nuPokeList.get(i)[0];
			if(id.equals(number))
			{
				pokemon=nuPokeList.get(i);
				//break;
			}
		}
		return pokemon;
	}
	
	@Override
	public ArrayList<String[]> typeSearch(String type) {
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		for(int i=0;i!=nuPokeList.size();i++)
		{
			if(nuPokeList.get(i)[8].equals(type)||nuPokeList.get(i)[9].equals(type))
			{
				pokemon.add(nuPokeList.get(i));
			}
		}
		return pokemon;
	}
	
	@Override
	public int location(String id)
	{
		{
			int size=nuPokeList.size();
			for(int i=0;i!=size;i++)
			{
				if(nuPokeList.get(i)[0].equals(id))
				{
					return i;
				}
			}
			return -1;
		}
	}
}
