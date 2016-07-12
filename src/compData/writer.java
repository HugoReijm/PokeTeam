package compData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import mathData.AGPokedex;
//import mathData.BSPokedex;
//import mathData.NUPokedex;
//import mathData.OUPokedex;
import mathData.Pokedex;
//import mathData.RUPokedex;
//import mathData.UUPokedex;
//import mathData.UbersPokedex;

public class writer {
	private static ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	private static ArrayList<ArrayList<String>> backupTable = new ArrayList<ArrayList<String>>();
	private static ArrayList<String[]> newTeamLog = new ArrayList<String[]>();
	private final static String currentDirectory = new File("").getAbsolutePath();

	public static void reset(Pokedex pokedex)
	{
		try
		{
			String tier = pokedex.getTier();
			int size=pokedex.getList().size();
			File file = new File(currentDirectory+"\\resources\\tables\\"+tier+"data.txt");
			File logFile = new File(currentDirectory+"\\resources\\tables\\"+tier+"log.txt");
			System.out.println(file.getCanonicalPath());
			System.out.println(logFile.getCanonicalPath());
			FileWriter output = new FileWriter(file);
			FileWriter logOutput = new FileWriter(logFile);
			BufferedWriter bufWrit = new BufferedWriter(output);
			BufferedWriter logWrit = new BufferedWriter(logOutput);
			for(int i=0;i!=size;i++)
			{
				for(int j=0;j!=size-1;j++)
				{
					bufWrit.write("0,");
				}
				bufWrit.write("0;");
				bufWrit.newLine();
			}
			logWrit.write("000,000,000,000,000,000;");
			bufWrit.close();
			logWrit.close();
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void backupReset(Pokedex pokedex)
	{
		try
		{
			String tier = pokedex.getTier();
			int size=pokedex.getList().size();
			File backupFile = new File(currentDirectory+"\\resources\\tables\\"+tier+"backup.txt");
			System.out.println(backupFile.getCanonicalPath());
			FileWriter backupOutput = new FileWriter(backupFile);
			BufferedWriter backupBuf = new BufferedWriter(backupOutput);
			for(int i=0;i!=size;i++)
			{
				for(int j=0;j!=size-1;j++)
				{
					backupBuf.write("0,");
				}
				backupBuf.write("0;");
				backupBuf.newLine();
			}
			backupBuf.close();
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
			FileReader inputPath = new FileReader(currentDirectory+"\\resources\\tables\\"+tier+"data.txt");
			FileReader backupPath = new FileReader(currentDirectory+"\\resources\\tables\\"+tier+"backup.txt");
			FileReader logPath = new FileReader(currentDirectory+"\\resources\\tables\\"+tier+"log.txt");
			BufferedReader bufRead = new BufferedReader(inputPath);
			BufferedReader backupBuf = new BufferedReader(backupPath);
			BufferedReader logBuf = new BufferedReader(logPath);
			String line = null;
			String backupline = null;
			String logline = null;
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
			
			while((backupline=backupBuf.readLine())!=null)
			{
				ArrayList<String> row = new ArrayList<String>();
				String[] pokemon = backupline.split(",");
				pokemon[pokemon.length-1]=pokemon[pokemon.length-1].replace(";", "");
				for(int i=0;i!=pokemon.length;i++)
				{
					row.add(pokemon[i]);
				}
				backupTable.add(row);
			}
			backupBuf.close();
			
			while((logline=logBuf.readLine())!=null)
			{
				String[] team = logline.split(",");
				team[team.length-1]=team[team.length-1].replace(";","");
				newTeamLog.add(team);
			}
			logBuf.close();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void adder(String[] team, Pokedex pokedex){
		newTeamLog.add(team);
		
		int[] numbers = new int[team.length];
		for(int i=0;i!=team.length;i++)
		{
			numbers[i]=pokedex.location(team[i]);
		}
		for(int i=0;i!=numbers.length;i++)
		{
			for(int j=0;j!=numbers.length;j++)
			{
				if((numbers[i]!=-1||numbers[j]!=-1)&&numbers[i]!=numbers[j])
				{
					table.get(numbers[i]).set(numbers[j],Integer.toString(Integer.parseInt(table.get(numbers[i]).get(numbers[j]))+1));
					backupTable.get(numbers[i]).set(numbers[j],Integer.toString(Integer.parseInt(backupTable.get(numbers[i]).get(numbers[j]))+1));
				}
			}
		}
	}
	
	public static void fileWriter(Pokedex pokedex)
	{
		try
		{
			String tier = pokedex.getTier();
			int size=pokedex.getList().size();
			File file = new File(currentDirectory+"\\resources\\tables\\"+tier+"data.txt");
			File backupFile = new File(currentDirectory+"\\resources\\tables\\"+tier+"backup.txt");
			File logFile = new File(currentDirectory+"\\resources\\tables\\"+tier+"log.txt");
			FileWriter output = new FileWriter(file);
			FileWriter backupOutput = new FileWriter(backupFile);
			FileWriter logOutput = new FileWriter(logFile);
			BufferedWriter bufWrit = new BufferedWriter(output);
			BufferedWriter backupBuf = new BufferedWriter(backupOutput);
			BufferedWriter logBuf = new BufferedWriter(logOutput);
			for(int i=0;i!=size;i++)
			{
				for(int j=0;j!=size-1;j++)
				{
					bufWrit.write(table.get(i).get(j)+",");
					backupBuf.write(backupTable.get(i).get(j)+",");
				}
				bufWrit.write(table.get(i).get(size-1)+";");
				backupBuf.write(backupTable.get(i).get(size-1)+";");
				bufWrit.newLine();
				backupBuf.newLine();
			}
			bufWrit.close();
			backupBuf.close();
			
			for(int i=0;i!=newTeamLog.size();i++)
			{
				for(int j=0;j!=newTeamLog.get(i).length-1;j++)
				{
					logBuf.write(newTeamLog.get(i)[j]+",");
				}
				logBuf.write(newTeamLog.get(i)[newTeamLog.get(i).length-1]+";");
				logBuf.newLine();
			}
			logBuf.close();
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static boolean teamChecker(String[] nameTeam, Pokedex pokedex)
	{
		if(nameTeam.length!=6)
		{
			System.out.println("Team does not have 6 members");
			return false;
		}
		for(int i=0;i!=6;i++)
		{
			if(pokedex.boolSearch(nameTeam[i])==false)
			{
				System.out.println("Team member #"+(i+1)+" is undefined/can't be found in the current Tier");
				return false;
			}
		}
		return true;
	}
	
	public static boolean legalChecker(String[] team, Pokedex pokedex)
	{
		int mega = 0;
		for(int i=0;i!=team.length;i++)
		{
			if(pokedex.exactSearch(team[i])[0].contains("M"))
			{
				mega++;
			}
			for(int j=i;j!=team.length;j++)
			{
				if((i!=j)&&(team[i].equals(team[j])))
				{
					System.out.println("Team fails the Species Clause: Pokemon #"+(i+1)+" and #"+(j+1)+" are the same species");
					return false;
				}
			}
		}
		if(mega>1)
		{
			System.out.println("Team has multiple Mega's");
			return false;
		}
		return true;
	}
	
	public static boolean newTeamChecker(String[] team, Pokedex pokedex)
	{
		for(int i=0;i!=newTeamLog.size();i++)
		{
			int sim = 0;
			for(int j=0;j!=6;j++)
			{
				for(int k=0;k!=6;k++)
				{
					if(newTeamLog.get(i)[j].equals(team[k]))
					{
						sim++;
					}
				}
			}
			if(sim>=6)
			{
				System.out.println("Team has already been registered");
				return false;
			}
		}
		return true;
	}
	
	public static void toPrint(Pokedex pokedex)
	{
		for(int i=0;i!=table.size();i++)
		{
			for(int j=0;j!=table.get(0).size();j++)
			{
				if(Integer.parseInt(table.get(i).get(j))!=0)
				{
					System.out.println(pokedex.getList().get(i)[0]);
					System.out.println(pokedex.getList().get(j)[0]);
					System.out.println(table.get(i).get(j));
					System.out.println("");
				}
			}
		}
	}
	
	public static void main(String[] args)
	{	
		Pokedex pokedex = new AGPokedex();
		reader(pokedex);
		boolean rerun = true;
		String respond;
		Scanner scanner = new Scanner(System.in);
		
		while(rerun==true)
		{
			System.out.println("Please input your first Pokemon");
			String poke1 = scanner.nextLine();
			System.out.println("Please input your second Pokemon");
			String poke2 = scanner.nextLine();
			System.out.println("Please input your third Pokemon");
			String poke3 = scanner.nextLine();
			System.out.println("Please input your fourth Pokemon");
			String poke4 = scanner.nextLine();
			System.out.println("Please input your fifth Pokemon");
			String poke5 = scanner.nextLine();
			System.out.println("Please input your sixth Pokemon");
			String poke6 = scanner.nextLine();
			
			String[] tempTeam = {poke1,poke2,poke3,poke4,poke5,poke6};
			if(teamChecker(tempTeam,pokedex)==true)
			{
				if(legalChecker(tempTeam,pokedex)==true)
				{
					String[] team = {pokedex.exactSearch(poke1)[0],pokedex.exactSearch(poke2)[0],pokedex.exactSearch(poke3)[0],pokedex.exactSearch(poke4)[0],pokedex.exactSearch(poke5)[0],pokedex.exactSearch(poke6)[0]};
					if(newTeamChecker(team,pokedex)==true)
					{
						adder(team,pokedex);
						System.out.println("Team registered!");
					}
				}
			}

			System.out.println("Retry/Register another team?: Y/N");
			respond = scanner.nextLine();
			if(respond.equals("N"))
			{
				rerun = false;
				fileWriter(pokedex);
				scanner.close();
				System.out.println("Done!");
			}
		}
	}
}
