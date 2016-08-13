package type;

import java.util.ArrayList;

public class TypeAnalyzer {

	public synchronized static ArrayList<ArrayList<Type>> wriTable(ArrayList<Type> types){
		ArrayList<ArrayList<Type>> wriTable = new ArrayList<ArrayList<Type>>(2);
		wriTable.add(new ArrayList<Type>());
		wriTable.add(new ArrayList<Type>());
		
		ArrayList<Type[]> sections = new ArrayList<Type[]>();
		for(int j=0;j!=types.size();j++)
		{
			if(j%2!=0)
			{
				Type[] s = new Type[2];
				s[0] = types.get(j-1);
				s[1] = types.get(j);
				sections.add(s);
			}
		}
		
		for(int j=0;j!=sections.size();j++)
		{
			ArrayList<ArrayList<Type>> tempWRI = new ArrayList<ArrayList<Type>>(2);
			tempWRI.add(new ArrayList<Type>());
			tempWRI.add(new ArrayList<Type>());
			
			for(int k=0;k!=2;k++)
			{
				ArrayList<String> w = sections.get(j)[k].getWeakness();
				ArrayList<String> r = sections.get(j)[k].getResistance();
				
				for(int m=0;m!=w.size();m++){
					tempWRI.get(0).add(Type.toType(w.get(m)));
				}
				for(int m=0;m!=r.size();m++){
					tempWRI.get(1).add(Type.toType(r.get(m)));
				}
			}
			tempWRI=simplify(tempWRI);
			
			for(int k=0;k!=2;k++)
			{
				ArrayList<String> i = sections.get(j)[k].getImmunity();
				if(i!=null)
				{
					for(int m=0;m!=i.size();m++)
					{
						for(int n=tempWRI.get(0).size()-1;n!=-1;n--)
						{
							if(tempWRI.get(0).get(n)!=null&&tempWRI.get(0).get(n).getName().equals(i.get(m)))
							{
								tempWRI.get(0).remove(tempWRI.get(0).get(n));
							}
						}
						for(int n=tempWRI.get(1).size()-1;n!=-1;n--)
						{
							if(tempWRI.get(1).get(n)!=null&&tempWRI.get(1).get(n).getName().equals(i.get(m)))
							{
								tempWRI.get(1).remove(tempWRI.get(1).get(n));
							}
						}
						tempWRI.get(1).add(Type.toType(i.get(m)));
						tempWRI.get(1).add(Type.toType(i.get(m)));
					}
				}
			}
			
			wriTable.get(0).addAll(tempWRI.get(0));
			wriTable.get(1).addAll(tempWRI.get(1));
		}
		return simplify(wriTable);
	}
	
	private static ArrayList<ArrayList<Type>> simplify(ArrayList<ArrayList<Type>> wriTable){
		for(int k=0;k!=2;k++)
		{
			for(int m=wriTable.get(k).size()-1;m!=-1;m--)
			{
				if(wriTable.get(k).get(m)==null)
				{
					wriTable.get(k).remove(wriTable.get(k).get(m));
				}
			}
		}
		
		for(int k=wriTable.get(0).size()-1;k!=-1;k--)
		{
			for(int l=wriTable.get(1).size()-1;l!=-1;l--)
			{
				if(wriTable.get(0).get(k).getName().equals(wriTable.get(1).get(l).getName()))
				{
					wriTable.get(1).remove(wriTable.get(1).get(l));
					wriTable.get(0).remove(wriTable.get(0).get(k));
					break;
				}
			}
		}
		return wriTable;
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
	
	private static double[] biasChanger(double[] bias,ArrayList<ArrayList<Type>> origWRITable)
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
}