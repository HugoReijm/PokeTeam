package mathData;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UbersPokedex extends Pokedex{

	private String tier = "Ubers";
	private static ArrayList<String[]> ubersPokeList=new ArrayList<String[]>();
	
	public UbersPokedex(){
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
				ubersPokeList.add(pokemon);
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
		
		return ubersPokeList;
	}

	@Override
	public String getTier()
	{
		String tier = "Ubers";
		return tier;
	}
	
	@Override
	public ArrayList<String[]> search(String nameID) {
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		String name;
		for(int i=0;i!=ubersPokeList.size();i++)
		{
			name=ubersPokeList.get(i)[1];
			if(name.contains(nameID))
			{
				pokemon.add(ubersPokeList.get(i));
				//break;
			}
		}
		return pokemon;
	}

	@Override
	public String[] exactSearch(String nameID)
	{
		String[] pokemon = new String[ubersPokeList.get(0).length];
		String name;
		for(int i=0;i!=ubersPokeList.size();i++)
		{
			name=ubersPokeList.get(i)[1];
			if(name.equals(nameID))
			{
				pokemon=ubersPokeList.get(i);
				//break;
			}
		}
		return pokemon;
	}
	
	@Override
	public boolean boolSearch(String nameID)
	{
		String name;
		for(int i=0;i!=ubersPokeList.size();i++)
		{
			name=ubersPokeList.get(i)[1];
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
		for(int i=0;i!=ubersPokeList.size();i++)
		{
			number=ubersPokeList.get(i)[0];
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
				pokemon.add(ubersPokeList.get(i));
				//break;
			}
		}
		return pokemon;
	}

	@Override
	public String[] exactIDSearch(String id)
	{
		String[] pokemon = new String[ubersPokeList.get(0).length];
		String number="";
		for(int i=0;i!=ubersPokeList.size();i++)
		{
			number=ubersPokeList.get(i)[0];
			if(id.equals(number))
			{
				pokemon=ubersPokeList.get(i);
				//break;
			}
		}
		return pokemon;
	}
	
	@Override
	public ArrayList<String[]> typeSearch(String type) {
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		for(int i=0;i!=ubersPokeList.size();i++)
		{
			if(ubersPokeList.get(i)[8].equals(type)||ubersPokeList.get(i)[9].equals(type))
			{
				pokemon.add(ubersPokeList.get(i));
			}
		}
		return pokemon;
	}

	@Override
	public ArrayList<String[]> megaSearch() {
		ArrayList<String[]> megas = new ArrayList<String[]>();
		for(int i=0;i!=ubersPokeList.size();i++)
		{
			if(ubersPokeList.get(i)[0].contains("M"))
			{
				megas.add(ubersPokeList.get(i));
			}
		}
		return megas;
	}
	
	@Override
	public int location(String id)
	{
		{
			int size=ubersPokeList.size();
			for(int i=0;i!=size;i++)
			{
				if(ubersPokeList.get(i)[0].equals(id))
				{
					return i;
				}
			}
			return -1;
		}
	}
}
