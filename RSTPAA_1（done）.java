import java.util.ArrayList;

import Jama.Matrix;


public class test 
{
	public static void main(String[] args)
	{
		// just example.
		c1("ABCDEFGHIJKLMNO",3,1);
	}
	
	public static double[] c1(String Seri,int k, int w)
	{
		// Seri,k,w arguments.
		// matrix dataHHP.
		
		double[][] dataHHP=GetdataHHP();
		
		//Show(dataHHP);
		
		
		double[][] d1=GetColumn(dataHHP,0,20);
		double[][] d2=GetColumn(dataHHP,1,20);
		double[][] d3=GetColumn(dataHHP,2,20);
		
		//Show(d1);	Show(d2);	Show(d3);
		
		Statistics s=new Statistics(Inv(d1,20));
		double y1=s.getStdDev();
		
		s=new Statistics(Inv(d2,20));
		double y2=s.getStdDev();
		
		s=new Statistics(Inv(d3,20));
		double y3=s.getStdDev();
		
		double[] stdm=new double[3];
	    stdm[0]=y1;	stdm[1]=y2;	stdm[2]=y3;
	    
	    System.out.println("stdm");
	    Show(stdm);
	    
		 /** lengh (seri) */
		int Len=Seri.length(); 
		String OSet="ACDEFGHIKLMNPQRSTVWY";
		
		// B0 :
		double[] P=new double[20];
		for(int i=0;i<20;i++)	
			P[i]=0;
		
		
		// B1 :
		for(int i=0;i<Len;i++)
			for(int j=0;j<20;j++)
				if(Seri.substring(i, i+1).equals(OSet.substring(j, j+1)))
					P[j]=P[j]+1;
		
		// B2 :
		//System.out.println("aP : ");
		//Show(P);
		
		for(int i=0;i<P.length;i++)
			P[i]=(P[i]/(double)(Len));
		
		//System.out.println("P : ");
		//Show(P);
		
		// B3 :
		double[] f = null;
		double[] tao = new double[k];
		
		if (k==0) f=P;
		else
		{
			for(int i=0;i<k;i++)
			{
				tao[i]=0;
				for(int j=0;j<(Len-i-1);j++)
				{
					/**
					System.out.println(OSet+"\t , "+Seri.substring(j, j+1)+" / "+j +"/ "+(j+1));
					System.out.println(OSet+"\t , "+Seri.substring(j+i+1, j+i+2)+" / "+(j+i+1) 
											+"/ "+(j+i+2)+" s");
					*/
					
					int[] num=find(OSet,Seri.substring(j, j+1));
					int[] numk=find(OSet,Seri.substring(j+i+1, j+i+2));
					
					//System.out.println("numk : ");
					//Show(numk);
					
					double tranJ=0;
					if((num.length!= 0) && (numk.length != 0) )
					{
						
						double[][] H1=new double[3][num.length];
						double[][] H1k=new double[3][num.length];
						
						//Show(num);
						//Show(numk);
						
			            H1[0]=G(dataHHP,num,0);
			            H1[1]=G(dataHHP,num,1);
			            H1[2]=G(dataHHP,num,2);
			            						
						H1k[0]=G(dataHHP,numk,0);
			            H1k[1]=G(dataHHP,numk,1);
			            H1k[2]=G(dataHHP,numk,2);
			            
			            //System.out.println("h1");
			            //Show(H1);
			            
			            Matrix h1=new Matrix(H1);
			            Matrix h1k=new Matrix(H1k);
			            
			            //System.out.println("h1k");
			            //Show(H1k);
			            
			            //System.out.println("-");
			            //Show(h1k.getArray());
			            
			            h1=h1.transpose();
			            h1k=h1k.transpose();
			            
			            //Show(h1.getArray());
			            
			            //System.out.println("h1k transposé.");
			            //Show(h1k.getArray());
			            
			            double[][] mstdm=new double[1][3];
			            mstdm[0]=stdm;
			            		
			            Matrix Mstdm=new Matrix(mstdm);
			           
			            //Show(Mstdm.getArray());
			            
			            h1=h1.arrayRightDivide(Mstdm);
			            h1k=h1k.arrayRightDivide(Mstdm);
			            
			            Matrix hMinus=h1k.minus(h1);
			            
			            double[][] hD=hMinus.getArray();
			            int dimR=hMinus.getRowDimension();
			            int dimC=hMinus.getColumnDimension();
			            
			            tranJ=Sum(Pow(hD,2,dimR,dimC), dimR,dimC)/3.0;
			            //System.out.println(tranJ);
					}
					else
					{
						tranJ=0;
					}
					
					tao[i]=tao[i]+tranJ;
				}
				
				//System.out.println(tao[i]+" , "+(Len-i-1) );
				
				tao[i]=tao[i]/(double)(Len-i-1);
				//System.out.println(tao[i]);
			}
		}
			
		// B4 :
		double[] tempaa=new double[20+k];
		for(int i=0;i<20+k;i++)
			tempaa[i]=0;
		  
		double sumtao=sum(tao)*w;
		//Show(tao);
		//System.out.println(sumtao);
		
		for(int i=0;i<20+k;i++)
	    {
	    	if(i<20)	tempaa[i]=P[i]/(1+sumtao);
	    	else		tempaa[i]=(w*tao[i-20])/(1+sumtao);	
	    }
	        
	    f=tempaa;
	    Show(f);
		
	    return f;
				
	}
	
	public static void Show(double[][] dataHHP) 
	{
		Matrix m=new Matrix(dataHHP);
		for(int i=0;i<m.getRowDimension();i++)
		{
			String s="";
			for(int j=0;j<m.getColumnDimension();j++)
			{
				s+=m.get(i, j)+"\t";
			}
			System.out.println(s);
		}
		
		System.out.println();
	}

	public static double[] Inv(double[][] d1,int k) 
	{
		Show(d1);
		
		double[] T=new double[k];
		for(int i=0;i<k;i++)
		{
			T[i]=d1[i][0];
		}
		
		Show(T);
		return T;
	}

	public static void Show(Object Ob) 
	{
		String s="";
		
		if(Ob instanceof double[])
		{
			double[] T=(double[]) Ob;
			for(int i=0;i<T.length;i++)
			{
				s+=T[i]+"\t";
			}
		}
		
		if(Ob instanceof int[])
		{
			int[] T=(int[]) Ob;
			for(int i=0;i<T.length;i++)
			{
				s+=T[i]+"\t";
			}
		}
		
		System.out.println(s);
	}

	public static double[][] GetColumn(double[][] dataHHP, int i,int l) 
	{
		
		double [][] Column=new double[l][1];
		for(int j=0;j<l;j++)
		{
			Column[j][0]=dataHHP[j][i];
		}
		
		return Column;
	}

	private static double [][] GetdataHHP() 
	{
		double [][]dataHHP=new double[20][6];
		
		double[] d0={0.6200,   -0.5000,   15.0000,    2.3500,    9.8700,    6.1100};
		double[] d1={0.2900,   -1.0000,   47.0000,    1.7100,   10.7800,    5.0200};
		double[] d2={-0.9000,    3.0000,   59.0000,    1.8800,   9.6000,    2.9800};
		double[] d3={-0.7400,    3.0000,   73.0000,    2.1900,    9.6700,    3.0800};
		double[] d4={1.1900,   -2.5000,   91.0000,    2.5800,    9.2400,    5.9100};
		
		double[] d5={0.4800,         0,    1.0000,    2.3400,    9.6000,    6.0600};
		double[] d6={-0.4000,   -0.5000,   82.0000,    1.7800,    8.9700,    7.6400};
		double[] d7={1.3800,   -1.8000,   57.0000,    2.3200,    9.7600,    6.0400};
		double[] d8={-1.5000,    3.0000,   73.0000,    2.2000,    8.9000,    9.4700};
		double[] d9={1.0600,   -1.8000,   57.0000,    2.3600,    9.6000,    6.0400};
		   
		double[] d10={0.6400,   -1.3000,   75.0000,    2.2800,    9.2100,    5.7400};
		double[] d11={-0.7800,    0.2000,   58.0000,    2.1800,    9.0900,   10.7600};
		double[] d12={0.1200,         0,  42.0000,    1.9900,   10.6000,    6.3000};
		double[] d13={-0.8500,    0.2000,   72.0000,    2.1700,    9.1300,    5.6500};
		double[] d14={-2.5300,    3.0000,  101.0000,    2.1800,    9.0900,   10.7600};
		
		   
		double[] d15={-0.1800,    0.3000,   31.0000,    2.2100,    9.1500,    5.6800};
		double[] d16={-0.0500,   -0.4000,   45.0000,    2.1500,    9.1200,    5.6000};
		double[] d17={1.0800,   -1.5000,   43.0000,    2.2900,    9.7400,   6.0200};
		double[] d18={0.8100,   -3.4000,  130.0000,    2.3800,    9.3900,    5.8800};
		double[] d19={0.2600,   -2.3000,  107.0000,    2.2000,    9.1100,    5.6300};
		
		dataHHP[0]=d0;	dataHHP[1]=d1;	dataHHP[2]=d2;	dataHHP[3]=d3;
		dataHHP[4]=d4;	dataHHP[5]=d5;	dataHHP[6]=d6;	dataHHP[7]=d7;
		dataHHP[8]=d8;	dataHHP[9]=d9;	dataHHP[10]=d10;	dataHHP[11]=d11;
		dataHHP[12]=d12;	dataHHP[13]=d13;	dataHHP[14]=d14;	dataHHP[15]=d15;
		dataHHP[16]=d16;	dataHHP[17]=d17;	dataHHP[18]=d18;	dataHHP[19]=d19;
		
		return dataHHP;
	}
		
	private static double[][] Pow(double[][] hD, int p,int l1, int l2) 
	{
		for(int i=0;i<l1;i++)
			for(int j=0;j<l2;j++)
				hD[i][j]=Math.pow(hD[i][j], p);
		
		return hD;
	}

	private static double Sum(double[][] hD, int l1, int l2) 
	{
		double sum=0;
		for(int i=0;i<l1;i++)
			for(int j=0;j<l2;j++)
				sum+=(hD[i][j]);
		
		return sum;
	}

	public static double[] G(double[][] dataHHP, int[] num, int i) 
	{
		double[] T=new double[num.length];
		for(int j=0;j<num.length;j++)
		{
			int val=num[j];
			T[j]=dataHHP[val][i];
		}
		
		return T;
	}

	private static double sum(Object num) 
	{
		double sum=0.0;
		
		if(num instanceof int[])
		{
			int[] T=(int[]) num;
			for(int i=0;i<T.length;i++)
				sum+=T[i];
		}
		
		if(num instanceof double[])
		{
			double[] T=(double[]) num;
			for(int i=0;i<T.length;i++)
				sum+=T[i];
		}
		
		return sum;
	}

	/**
	 * return first index.
	 * @param oSet
	 * @param st
	 * @return
	 */
	public static int[] find(String oSet, String st) 
	{
		ArrayList<Integer> L=new ArrayList<Integer>();
		
		int l=oSet.length();
		int lx=st.length();
		for(int i=0;i<(l-lx+1);i++)
		{
			if(oSet.substring(i, i+lx).equalsIgnoreCase(st))
				L.add(i);
		}
		
		int[] num=new int[L.size()];
		for(int i=0;i<L.size();i++)
			num[i]=L.get(i);
			
		return num;
	}
	
}
