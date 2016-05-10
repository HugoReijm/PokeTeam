package mathData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UUPokedex extends Pokedex{
	private String tier = "UU";
	private static ArrayList<String[]> uuPokeList=new ArrayList<String[]>();
	
	public UUPokedex(){
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
				uuPokeList.add(pokemon);
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
		
		return uuPokeList;
	}

	@Override
	public String getTier()
	{
		String tier = "UU";
		return tier;
	}
	
	@Override
	public ArrayList<String[]> search(String nameID) {
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		String name;
		for(int i=0;i!=uuPokeList.size();i++)
		{
			name=uuPokeList.get(i)[1];
			if(name.contains(nameID))
			{
				pokemon.add(uuPokeList.get(i));
				//break;
			}
		}
		return pokemon;
	}

	@Override
	public String[] exactSearch(String nameID)
	{
		String[] pokemon = new String[uuPokeList.get(0).length];
		String name;
		for(int i=0;i!=uuPokeList.size();i++)
		{
			name=uuPokeList.get(i)[1];
			if(name.equals(nameID))
			{
				pokemon=uuPokeList.get(i);
				//break;
			}
		}
		return pokemon;
	}
	
	@Override
	public boolean boolSearch(String nameID)
	{
		String name;
		for(int i=0;i!=uuPokeList.size();i++)
		{
			name=uuPokeList.get(i)[1];
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
		for(int i=0;i!=uuPokeList.size();i++)
		{
			number=uuPokeList.get(i)[0];
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
				pokemon.add(uuPokeList.get(i));
				//break;
			}
		}
		return pokemon;
	}

	@Override
	public String[] exactIDSearch(String id)
	{
		String[] pokemon = new String[uuPokeList.get(0).length];
		String number="";
		for(int i=0;i!=uuPokeList.size();i++)
		{
			number=uuPokeList.get(i)[0];
			if(id.equals(number))
			{
				pokemon=uuPokeList.get(i);
				//break;
			}
		}
		return pokemon;
	}
	
	@Override
	public ArrayList<String[]> typeSearch(String type) {
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		for(int i=0;i!=uuPokeList.size();i++)
		{
			if(uuPokeList.get(i)[8].equals(type)||uuPokeList.get(i)[9].equals(type))
			{
				pokemon.add(uuPokeList.get(i));
			}
		}
		return pokemon;
	}

	@Override
	public ArrayList<String[]> megaSearch() {
		ArrayList<String[]> megas = new ArrayList<String[]>();
		for(int i=0;i!=uuPokeList.size();i++)
		{
			if(uuPokeList.get(i)[0].contains("M"))
			{
				megas.add(uuPokeList.get(i));
			}
		}
		return megas;
	}

	@Override
	public int location(String id)
	{
		{
			int size=uuPokeList.size();
			for(int i=0;i!=size;i++)
			{
				if(uuPokeList.get(i)[0].equals(id))
				{
					return i;
				}
			}
			return -1;
		}
	}
}
