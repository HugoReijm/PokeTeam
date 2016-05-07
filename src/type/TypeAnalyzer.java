package type;

import java.util.ArrayList;

public class TypeAnalyzer {

	public static ArrayList<ArrayList<Type>> wriTable(ArrayList<Type> types){
		ArrayList<ArrayList<Type>> wriTable = new ArrayList<ArrayList<Type>>(2);
		wriTable.add(new ArrayList<Type>());
		wriTable.add(new ArrayList<Type>());
		for(int j=0;j!=types.size();j++)
		{
			ArrayList<String> w = types.get(j).getWeakness();
			ArrayList<String> r = types.get(j).getResistance();
			ArrayList<String> i = types.get(j).getImmunity();
			for(int k=0;k!=w.size();k++){
				wriTable.get(0).add(Type.toType(w.get(k)));
			}
			for(int k=0;k!=r.size();k++){
				wriTable.get(1).add(Type.toType(r.get(k)));
			}
			for(int k=0;k!=i.size();k++){
				wriTable.get(1).add(Type.toType(i.get(k)));
				wriTable.get(1).add(Type.toType(i.get(k)));
			}
		}
		for(int i=0;i!=2;i++)
		{
			for(int j=wriTable.get(i).size()-1;j!=-1;j--)
			{
				if(wriTable.get(i).get(j)==null)
				{
					wriTable.get(i).remove(wriTable.get(i).get(j));
					//break;
				}
			}
		}
		return simplify(wriTable);
	}
	
	public static ArrayList<ArrayList<Type>> simplify(ArrayList<ArrayList<Type>> wriTable){
		for(int i=wriTable.get(0).size()-1;i!=-1;i--)
		{
			for(int j=wriTable.get(1).size()-1;j!=-1;j--)
			{
				if(wriTable.get(0).get(i).getName().equals(wriTable.get(1).get(j).getName()))
				{
					wriTable.get(1).remove(wriTable.get(1).get(j));
					wriTable.get(0).remove(wriTable.get(0).get(i));
					break;
				}
			}
		}
		return wriTable;
	}
	
	public static void toPrint(ArrayList<ArrayList<Type>> wriTable){
		System.out.println("WEAKNESSES");
		for(int k=0;k!=wriTable.get(0).size();k++){
			System.out.println(wriTable.get(0).get(k).getName());
		}
		System.out.println("");
		
		System.out.println("RESISTANCE");
		for(int k=0;k!=wriTable.get(1).size();k++){
			System.out.println(wriTable.get(1).get(k).getName());
		}
		System.out.println("");
	}
	
	public static double typeScore(Type type1, Type type2, ArrayList<Type> types, int origW, int origR){
		double kW = 1;
		double kR = 1;
		
		ArrayList<Type> tempTypes = new ArrayList<Type>(types.size()+2);
		for(int i=0;i!=types.size();i++)
		{
			tempTypes.add(types.get(i));
		}
		tempTypes.add(type1);
		tempTypes.add(type2);
		
		ArrayList<ArrayList<Type>> newWRITable = wriTable(tempTypes);
		int newW = newWRITable.get(0).size();
		int newR = newWRITable.get(1).size();
		
		return -kW*(newW-origW)+kR*(newR-origR);
	}
	
	public static String[][] bestScores(ArrayList<String[]> pokedex, int quantity, ArrayList<Type> types)
	{
		String[][] scores = new String[quantity][11];
		for(int i=0;i!=quantity;i++)
		{
			scores[i][10]="0.0";
		}
		
		ArrayList<ArrayList<Type>> origWRITable = wriTable(types);
		
		for(int i=0;i!=pokedex.size();i++)
		{
			for(int j=0;j!=quantity;j++)
			{
				double typeScore = typeScore(Type.toType(pokedex.get(i)[8]),Type.toType(pokedex.get(i)[9]),types,origWRITable.get(0).size(),origWRITable.get(1).size());
				if(typeScore>Double.parseDouble(scores[j][10]))
				{
					for(int k=quantity-1;k!=j;k--)
					{
						scores[k][0]=scores[k-1][0];
						scores[k][1]=scores[k-1][1];
						scores[k][2]=scores[k-1][2];
						scores[k][3]=scores[k-1][3];
						scores[k][4]=scores[k-1][4];
						scores[k][5]=scores[k-1][5];
						scores[k][6]=scores[k-1][6];
						scores[k][7]=scores[k-1][7];
						scores[k][8]=scores[k-1][8];
						scores[k][9]=scores[k-1][9];
						scores[k][10]=scores[k-1][10];
					}
					scores[j][0]=pokedex.get(i)[0];
					scores[j][1]=pokedex.get(i)[1];
					scores[j][2]=pokedex.get(i)[2];
					scores[j][3]=pokedex.get(i)[3];
					scores[j][4]=pokedex.get(i)[4];
					scores[j][5]=pokedex.get(i)[5];
					scores[j][6]=pokedex.get(i)[6];
					scores[j][7]=pokedex.get(i)[7];
					scores[j][8]=pokedex.get(i)[8];
					scores[j][9]=pokedex.get(i)[9];
					scores[j][10]=Double.toString(typeScore);
					break;
				}
			}
		}
		return scores;
	}
}