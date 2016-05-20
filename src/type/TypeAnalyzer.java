package type;

import java.util.ArrayList;

import mathData.AGPokedex;
import mathData.BSPokedex;
import mathData.Pokedex;

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
	
	public static double typeScore(Type type1, Type type2, ArrayList<Type> types,ArrayList<ArrayList<Type>> origWRITable)
	{
		double[] newtypeArray = new double[19];
		double[] origtypeArray = new double[19];
		double[] bias = {1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		
		ArrayList<Type> tempTypes = new ArrayList<Type>(types.size()+2);
		for(int i=0;i!=types.size();i++)
		{
			tempTypes.add(types.get(i));
		}
		tempTypes.add(type1);
		tempTypes.add(type2);
		
		ArrayList<ArrayList<Type>> newWRITable = wriTable(tempTypes);
		bias = biasChanger(bias, origWRITable);
		origtypeArray = minusWeakness(origtypeArray,origWRITable);
		origtypeArray = plusResistance(origtypeArray,origWRITable);
		newtypeArray = minusWeakness(newtypeArray,newWRITable);
		newtypeArray = plusResistance(newtypeArray,newWRITable);
		
		double sum=0.0;
		for(int i=0;i!=newtypeArray.length;i++)
		{
			sum=sum+(newtypeArray[i]-origtypeArray[i])*bias[i];
		}
	
		return sum;
	}
	
	public static String[][] bestScores(ArrayList<String[]> pokedex, int quantity, ArrayList<Type> types)
	{
		String[][] scores = new String[quantity][11];
		for(int i=0;i!=quantity;i++)
		{
			scores[i][10]="-20.0";
		}
		
		ArrayList<ArrayList<Type>> origWRITable = wriTable(types);
		
		for(int i=0;i!=pokedex.size();i++)
		{
			for(int j=0;j!=quantity;j++)
			{
				double typeScore = typeScore(Type.toType(pokedex.get(i)[8]),Type.toType(pokedex.get(i)[9]),types,origWRITable);
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
	
	public static double[] minusWeakness(double[] typeArray,ArrayList<ArrayList<Type>> newWRITable)
	{
		for(int i=0;i!=newWRITable.get(0).size();i++)
		{
			String name = newWRITable.get(0).get(i).getName();
			if(name.equals("Bug"))
			{
				typeArray[0]--;
			}
			else if(name.equals("Dark"))
			{
				typeArray[1]--;
			}
			else if(name.equals("Dragon"))
			{
				typeArray[2]--;
			}
			else if(name.equals("Electric"))
			{
				typeArray[3]--;
			}
			else if(name.equals("Fairy"))
			{
				typeArray[4]--;
			}
			else if(name.equals("Fighting"))
			{
				typeArray[5]--;
			}
			else if(name.equals("Fire"))
			{
				typeArray[6]--;			
			}
			else if(name.equals("Flying"))
			{
				typeArray[7]--;
			}
			else if(name.equals("Ghost"))
			{
				typeArray[8]--;
			}
			else if(name.equals("Grass"))
			{
				typeArray[9]--;
			}
			else if(name.equals("Ground"))
			{
				typeArray[10]--;
			}
			else if(name.equals("Ice"))
			{
				typeArray[11]--;
			}
			else if(name.equals("Normal"))
			{
				typeArray[12]--;
			}
			else if(name.equals("Null"))
			{
				typeArray[13]--;
			}
			else if(name.equals("Poison"))
			{
				typeArray[14]--;
			}
			else if(name.equals("Psychic"))
			{
				typeArray[15]--;
			}
			else if(name.equals("Rock"))
			{
				typeArray[16]--;
			}
			else if(name.equals("Steel"))
			{
				typeArray[17]--;
			}
			else if(name.equals("Water"))
			{
				typeArray[18]--;
			}
		}
		return typeArray;
	}
	
	public static double[] plusResistance(double[] typeArray, ArrayList<ArrayList<Type>> newWRITable)
	{
		for(int i=0;i!=newWRITable.get(1).size();i++)
		{
			String name = newWRITable.get(1).get(i).getName();
			if(name.equals("Bug"))
			{
				typeArray[0]++;
			}
			else if(name.equals("Dark"))
			{
				typeArray[1]++;
			}
			else if(name.equals("Dragon"))
			{
				typeArray[2]++;
			}
			else if(name.equals("Electric"))
			{
				typeArray[3]++;
			}
			else if(name.equals("Fairy"))
			{
				typeArray[4]++;
			}
			else if(name.equals("Fighting"))
			{
				typeArray[5]++;
			}
			else if(name.equals("Fire"))
			{
				typeArray[6]++;			
			}
			else if(name.equals("Flying"))
			{
				typeArray[7]++;
			}
			else if(name.equals("Ghost"))
			{
				typeArray[8]++;
			}
			else if(name.equals("Grass"))
			{
				typeArray[9]++;
			}
			else if(name.equals("Ground"))
			{
				typeArray[10]++;
			}
			else if(name.equals("Ice"))
			{
				typeArray[11]++;
			}
			else if(name.equals("Normal"))
			{
				typeArray[12]++;
			}
			else if(name.equals("Null"))
			{
				typeArray[13]++;
			}
			else if(name.equals("Poison"))
			{
				typeArray[14]++;
			}
			else if(name.equals("Psychic"))
			{
				typeArray[15]++;
			}
			else if(name.equals("Rock"))
			{
				typeArray[16]++;
			}
			else if(name.equals("Steel"))
			{
				typeArray[17]++;
			}
			else if(name.equals("Water"))
			{
				typeArray[18]++;
			}
		}
		return typeArray;
	}
	
	public static double[] biasChanger(double[] bias,ArrayList<ArrayList<Type>> origWRITable)
	{
		double factor = 5.0;
		for(int i=0;i!=origWRITable.get(1).size();i++)
		{
			String name = origWRITable.get(1).get(i).getName();
			if(name.equals("Bug"))
			{
				bias[0]=bias[0]/factor;
			}
			else if(name.equals("Dark"))
			{
				bias[1]=bias[1]/factor;
			}
			else if(name.equals("Dragon"))
			{
				bias[2]=bias[2]/factor;
			}
			else if(name.equals("Electric"))
			{
				bias[3]=bias[3]/factor;
			}
			else if(name.equals("Fairy"))
			{
				bias[4]=bias[4]/factor;
			}
			else if(name.equals("Fighting"))
			{
				bias[5]=bias[5]/factor;
			}
			else if(name.equals("Fire"))
			{
				bias[6]=bias[6]/factor;		
			}
			else if(name.equals("Flying"))
			{
				bias[7]=bias[7]/factor;
			}
			else if(name.equals("Ghost"))
			{
				bias[8]=bias[8]/factor;
			}
			else if(name.equals("Grass"))
			{
				bias[9]=bias[9]/factor;
			}
			else if(name.equals("Ground"))
			{
				bias[10]=bias[10]/factor;
			}
			else if(name.equals("Ice"))
			{
				bias[11]=bias[11]/factor;
			}
			else if(name.equals("Normal"))
			{
				bias[12]=bias[12]/factor;
			}
			else if(name.equals("Null"))
			{
				bias[13]=bias[13]/factor;
			}
			else if(name.equals("Poison"))
			{
				bias[14]=bias[14]/factor;
			}
			else if(name.equals("Psychic"))
			{
				bias[15]=bias[15]/factor;
			}
			else if(name.equals("Rock"))
			{
				bias[16]=bias[16]/factor;
			}
			else if(name.equals("Steel"))
			{
				bias[17]=bias[17]/factor;
			}
			else if(name.equals("Water"))
			{
				bias[18]=bias[18]/factor;
			}
		}
		
		for(int i=0;i!=origWRITable.get(0).size();i++)
		{
			String name = origWRITable.get(0).get(i).getName();
			if(name.equals("Bug"))
			{
				bias[0]=bias[0]*factor;
			}
			else if(name.equals("Dark"))
			{
				bias[1]=bias[1]*factor;
			}
			else if(name.equals("Dragon"))
			{
				bias[2]=bias[2]*factor;
			}
			else if(name.equals("Electric"))
			{
				bias[3]=bias[3]*factor;
			}
			else if(name.equals("Fairy"))
			{
				bias[4]=bias[4]*factor;
			}
			else if(name.equals("Fighting"))
			{
				bias[5]=bias[5]*factor;
			}
			else if(name.equals("Fire"))
			{
				bias[6]=bias[6]*factor;		
			}
			else if(name.equals("Flying"))
			{
				bias[7]=bias[7]*factor;
			}
			else if(name.equals("Ghost"))
			{
				bias[8]=bias[8]*factor;
			}
			else if(name.equals("Grass"))
			{
				bias[9]=bias[9]*factor;
			}
			else if(name.equals("Ground"))
			{
				bias[10]=bias[10]*factor;
			}
			else if(name.equals("Ice"))
			{
				bias[11]=bias[11]*factor;
			}
			else if(name.equals("Normal"))
			{
				bias[12]=bias[12]*factor;
			}
			else if(name.equals("Null"))
			{
				bias[13]=bias[13]*factor;
			}
			else if(name.equals("Poison"))
			{
				bias[14]=bias[14]*factor;
			}
			else if(name.equals("Psychic"))
			{
				bias[15]=bias[15]*factor;
			}
			else if(name.equals("Rock"))
			{
				bias[16]=bias[16]*factor;
			}
			else if(name.equals("Steel"))
			{
				bias[17]=bias[17]*factor;
			}
			else if(name.equals("Water"))
			{
				bias[18]=bias[18]*factor;
			}
		}
		return bias;
	}
	
	public static void main(String[] args)
	{
		Pokedex pokedex=new AGPokedex();
		ArrayList<Type> types=new ArrayList<Type>();
		types.add(new Water());
		types.add(new Null());
		System.out.println(pokedex.getList().get(385)[1]);
		System.out.println(typeScore(Type.toType(pokedex.getList().get(385)[8]),Type.toType(pokedex.getList().get(385)[9]),types,wriTable(types)));
	}
}