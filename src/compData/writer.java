package compData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import mathData.AGPokedex;
import mathData.Pokedex;

public class writer {
	private static ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

	public static void starter(Pokedex pokedex)
	{
		try
		{
			String tier = pokedex.getTier();
			int size=pokedex.getList().size();
			File logFile = new File("C:\\Users\\Hugo\\workspace\\BEP\\src\\compData\\tables\\"+tier+".txt");
			System.out.println(logFile.getCanonicalPath());
			FileWriter output = new FileWriter(logFile);
			BufferedWriter bufWrit = new BufferedWriter(output);
			for(int i=0;i!=size;i++)
			{
				for(int j=0;j!=size-1;j++)
				{
					bufWrit.write("0,");
				}
				bufWrit.write("0;");
				bufWrit.newLine();
			}
			bufWrit.close();
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void reader(Pokedex pokedex)
	{
		try 
		{
			String tier = pokedex.getTier();
			FileReader inputPath = new FileReader("C:\\Users\\Hugo\\workspace\\BEP\\src\\compData\\tables\\"+tier+".txt");
			BufferedReader bufRead = new BufferedReader(inputPath);
			String line = null;
			while((line=bufRead.readLine())!=null)
			{
				ArrayList<String> row = new ArrayList<String>();
				String[] pokemon = line.split(",");
				pokemon[pokemon.length-1]=pokemon[pokemon.length-1].replace(";", "");
				for(int i=0;i!=pokemon.length;i++)
				{
					row.add(pokemon[i]);
				}
				table.add(row);
			}
			bufRead.close();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void adder(String[] team, Pokedex pokedex){
		int[] numbers = new int[team.length];
		for(int i=0;i!=team.length;i++)
		{
			numbers[i]=pokedex.location(team[i]);
		}
		
		for(int i=0;i!=numbers.length;i++)
		{
			for(int j=0;j!=numbers.length;j++)
			{
				if(numbers[i]!=numbers[j])
				{
					table.get(numbers[i]).set(numbers[j],Integer.toString(Integer.parseInt(table.get(numbers[i]).get(numbers[j]))+1));
				}
			}
		}
	}
	
	public static void writer(Pokedex pokedex)
	{
		try
		{
			String tier = pokedex.getTier();
			int size=pokedex.getList().size();
			File logFile = new File("C:\\Users\\Hugo\\workspace\\BEP\\src\\compData\\tables\\"+tier+".txt");
			FileWriter output = new FileWriter(logFile);
			BufferedWriter bufWrit = new BufferedWriter(output);
			for(int i=0;i!=size;i++)
			{
				for(int j=0;j!=size-1;j++)
				{
					bufWrit.write(table.get(i).get(j)+",");
				}
				bufWrit.write(table.get(i).get(size-1)+";");
				bufWrit.newLine();
			}
			bufWrit.close();
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		Pokedex pokedex = new AGPokedex();
		starter(pokedex);
		reader(pokedex);
		String[] team = {"059","282M1","445","461","479F2","598"};
		adder(team,pokedex);
		//writer(pokedex);
		
		for(int i=0;i!=table.size();i++)
		{
			for(int j=0;j!=table.get(i).size();j++)
			{
				if(Integer.parseInt(table.get(i).get(j))!=0)
				{
					System.out.println(pokedex.getList().get(i)[0]);
					System.out.println(pokedex.getList().get(j)[0]);
					System.out.println(table.get(i).get(j));
					System.out.println("");
				}
				//System.out.print(table.get(i).get(j));
			}
			//System.out.println();
		}
		
		//System.out.println(table.get(pokedex.location("282M1")).get(pokedex.location("445")));
	}
}
