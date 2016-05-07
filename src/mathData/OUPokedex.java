package mathData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OUPokedex extends Pokedex{
	private String tier = "OU";
	private static ArrayList<String[]> ouPokeList=new ArrayList<String[]>();
	
	public OUPokedex(){
		try 
		{
			//FileReader inputPath = new FileReader("interfaz","pokedex",tier+".txt");
			FileReader inputPath = new FileReader("C:\\Users\\Hugo\\workspace\\BEP\\src\\mathData\\pokedex\\"+tier+".txt");
			BufferedReader bufRead = new BufferedReader(inputPath);
			String line = null;
			while((line=bufRead.readLine())!=null)
			{
				String[] pokemon = line.split(", ");
				pokemon[pokemon.length-1]=pokemon[pokemon.length-1].replace(";", "");
				ouPokeList.add(pokemon);
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
		
		return ouPokeList;
	}

	@Override
	public String getTier()
	{
		String tier = "OU";
		return tier;
	}
	
	@Override
	public ArrayList<String[]> search(String nameID) {
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		String name;
		for(int i=0;i!=ouPokeList.size();i++)
		{
			name=ouPokeList.get(i)[1];
			if(name.contains(nameID))
			{
				pokemon.add(ouPokeList.get(i));
				//break;
			}
		}
		return pokemon;
	}

	@Override
	public ArrayList<String[]> idSearch(int id) {
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		String number="";
		for(int i=0;i!=ouPokeList.size();i++)
		{
			number=ouPokeList.get(i)[0];
			if(number.contains("M"))
			{
				number=number.substring(0,number.indexOf("M"));
			}
			if(number.contains("F"))
			{
				number=number.substring(0,number.indexOf("F"));
			}
			
			if(id==Integer.parseInt(number))
			{
				pokemon.add(ouPokeList.get(i));
				//break;
			}
		}
		return pokemon;
	}

	@Override
	public ArrayList<String[]> typeSearch(String type) {
		ArrayList<String[]> pokemon = new ArrayList<String[]>();
		for(int i=0;i!=ouPokeList.size();i++)
		{
			if(ouPokeList.get(i)[8].equals(type)||ouPokeList.get(i)[9].equals(type))
			{
				pokemon.add(ouPokeList.get(i));
			}
		}
		return pokemon;
	}

	@Override
	public ArrayList<String[]> megaSearch() {
		ArrayList<String[]> megas = new ArrayList<String[]>();
		for(int i=0;i!=ouPokeList.size();i++)
		{
			if(ouPokeList.get(i)[0].contains("M"))
			{
				megas.add(ouPokeList.get(i));
			}
		}
		return megas;
	}

	@Override
	public int location(String id)
	{
		{
			int size=ouPokeList.size();
			for(int i=0;i!=size;i++)
			{
				if(ouPokeList.get(i)[0].equals(id))
				{
					return i;
				}
			}
			return -1;
		}
	}
}
