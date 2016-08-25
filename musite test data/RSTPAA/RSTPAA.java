import java.io.File;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.BigDecimal;
import java.util.stream.IntStream;
import java.text.*;


public class RSTPAA
{
   
	static double[] getStdDev(double[][] dataHHP)
	{
		double res = 0.0;
		double temp = 0.0;
		double avg = 0.0;
		//double variance = 0.0;
		double[] SD =  new double[3];

		//System.out.println("length is " + dataHHP.length);


		for(int j = 0; j < 3; j++)
		{
			for(int i = 0; i < (dataHHP.length); i++) 
			{
				res += dataHHP[i][j];
			}

			avg = res / (dataHHP.length);
			//System.out.println("Average is " + avg);

			for(int k = 0; k < (dataHHP.length); k++) 
			{
				temp += (dataHHP[k][j] - avg) * (dataHHP[k][j] - avg);
			}

			SD[j] = Math.sqrt(temp / dataHHP.length);

			res = 0.0;
			temp = 0.0;
		}

		return SD;

	}

	public static void main(String[] args)
    {
    	double [][]dataHHP = 
	    {{0.6200, -0.5000, 15.0000, 2.3500, 9.8700, 6.1100},
	    {0.2900, -1.0000, 47.0000, 1.7100, 10.7800, 5.0200},
		{-0.9000, 3.0000, 59.0000, 1.8800,   9.6000, 2.9800},
		{-0.7400, 3.0000, 73.0000, 2.1900, 9.6700, 3.0800},
		{1.1900, -2.5000, 91.0000, 2.5800, 9.2400, 5.9100},
		
		{0.4800, 0, 1.0000, 2.3400, 9.6000, 6.0600},
		{-0.4000, -0.5000, 82.0000, 1.7800, 8.9700, 7.6400},
		{1.3800, -1.8000, 57.0000, 2.3200, 9.7600, 6.0400},
		{-1.5000, 3.0000, 73.0000, 2.2000, 8.9000, 9.4700},
		{1.0600, -1.8000, 57.0000, 2.3600, 9.6000, 6.0400},
		   
		{0.6400, -1.3000, 75.0000, 2.2800, 9.2100, 5.7400},
		{-0.7800, 0.2000, 58.0000, 2.1800, 9.0900,   10.7600},
		{0.1200, 0, 42.0000, 1.9900,   10.6000, 6.3000},
		{-0.8500, 0.2000, 72.0000, 2.1700, 9.1300, 5.6500},
		{-2.5300, 3.0000, 101.0000, 2.1800, 9.0900,   10.7600},
		
		   
		{-0.1800, 0.3000, 31.0000, 2.2100, 9.1500, 5.6800},
		{-0.0500, -0.4000, 45.0000, 2.1500, 9.1200, 5.6000},
		{1.0800, -1.5000, 43.0000, 2.2900, 9.7400,   6.0200},
		{0.8100, -3.4000, 130.0000, 2.3800, 9.3900, 5.8800},
		{0.2600, -2.3000, 107.0000, 2.2000, 9.1100, 5.6300},};

    	double[] SD = new double[3];
    	final String oSet = "ACDEFGHIKLMNPQRSTVWY";
    	char[] tmpArray = oSet.toCharArray();

    	String seri = "ABCDEFG";//temp for testing
    	char[] tempSeri = seri.toCharArray();

    	int len = seri.length();

    	int k = 5;// temp, for testing
    	int w = 3;// temp, for testing

    	double[] p = new double[20];
    	Arrays.fill(p, 0);


    	SD = getStdDev(dataHHP);

    	//System.out.println(SD[0] + ", " + SD[1] + ", " + SD[2]);

    	for(int i = 0; i < tempSeri.length; i++)
    	{
    		for(int j = 0; j < 20; j++)
    		{
    			if(tempSeri[i] == tmpArray[j])
    			{
    				p[j] += 1;
    			}
    		}
    	}

    	for(int i = 0; i < p.length; i++)
    	{
    		p[i] /= tempSeri.length;
    		//System.out.println(p[i]);
    	}

    	ArrayList<Double> tao = new ArrayList<Double>();
    	int num, numk;
    	double []H1 = new double[3];
    	double []H1K = new double[3];
    	double sum = 0.0;
    	double tranJ = 0.0;
        int t=0;

    	if(k != 0)
    	{
	    	for(int i = 1; i <= k; i++)
	    	{
	    		tao.add(0.0);

	    		for(int j = 1; j <= len - i; j++)
	    		{
	    			num = oSet.indexOf(tempSeri[j-1]);
	    			numk = oSet.indexOf(tempSeri[j+i-1]);

	    			if(num >= 0 && numk >= 0)
	    			{
	    				//System.out.println("dataHHP[num][1] is " + dataHHP[num][1]);
	    				H1[0] = dataHHP[num][0] / SD[0];
	    				H1[1] = dataHHP[num][1] / SD[1];
	    				H1[2] = dataHHP[num][2] / SD[2];

	    				H1K[0] = dataHHP[numk][0] / SD[0];
	    				H1K[1] = dataHHP[numk][1] / SD[1];
	    				H1K[2] = dataHHP[numk][2] / SD[2];
               			//H1K = {{dataHHP[numk][1]},{dataHHP[numk][2]}, {dataHHP[numk][3]}};

	    				for(int z = 0; z < 3; z++)
	    				{
	    					sum += Math.pow(H1K[z] - H1[z], 2);
                            
	    				}
                        	    				tranJ = sum / 3;
                       
	    				//System.out.println("tranJ is " + tranJ);
	    			}
	    			else
	    			{
	    				tranJ = 0;
	    			}
	    			tao.set(i-1, tao.get(i-1) + tranJ);
	    		}
                sum = 0;
	    		tao.set(i-1, tao.get(i-1) / (len - i));
	    	}
    	}
        

    	double sumtao = 0.0;
        //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");

    	for(int i = 0; i < tao.size(); i++) 
    	{
            
		    //System.out.println(tao.get(i));
		    sumtao += tao.get(i);
		}  

        //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");

		sumtao *= w;

		//System.out.println("sumtao is " + sumtao);

		double[] tempaa = new double[20 + k];
    	Arrays.fill(tempaa, 0);

    	for (int i = 0; i < 20 + k; i++) 
    	{
    		if (i < 20)
    		{
    			tempaa[i] = p[i] / (1 + sumtao);
    		}
    		else
    		{
                //System.out.println("i = "+i);
               // System.out.println("tao.get(i - 20) = "+tao.get(i - 20));
    			tempaa[i] = w * tao.get(i - 20) / (1 + sumtao);
    		}	
    	}

    	//DecimalFormat df = new DecimalFormat("#.##");

    	for(int i = 0; i < 20 + k; i++) 
    	{   
		    //System.out.println(df.format(tempaa[i]));
		    System.out.println("Colum " + i + " is " + tempaa[i]);

		}  

    }


}
