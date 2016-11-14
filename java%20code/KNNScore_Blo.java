import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;

/**
 * all resource from matrices blosum.txt
 * @author  Yunfei Yang
 * 
 */
public class KNNScore_Blo {
	public static String Knn_Blo(String ProteinSeq1, String ProteinSeq2, int blosumnum) {
		 String KNNDis="0";
		 if ((ProteinSeq1.length())==(ProteinSeq2.length()))
		 {
			 String OSet="ACDEFGHIKLMNPQRSTVWY";
			// a blosum_order function including blosum function
			 //1.read blosum table, table name would be blosum66.txt
			 String file_name="blosum"+Integer.toString(blosumnum)+".txt";
			 
			 //2.sort it by order
			 //int [][]BM= blosum_order(getAminoAcidMatrix(file_name),blosumnum,OSet);
			 
			 //int BM[0][0]=1;
			 int [][]BM=new int[20][20];
			 int[] d0={4,0,-2,-1,-2,0,-2,-1,-1,-1,-1,-2,-1,-1,-1,1,0,0,-3,-2};
			 int[] d1={0,9,-3,-4,-2,-3,-3,-1,-3,-1,-1,-3,-3,-3,-3,-1,-1,-1,-2,-2};
			 int[] d2={-2,-3,6,2,-3,-1,-1,-3,-1,-4,-3,1,-1,0,-2,0,-1,-3,-4,-3};
			 int[] d3={-1,-4,2,5,-3,-2,0,-3,1,-3,-2,0,-1,2,0,0,-1,-2,-3,-2};
			 int[] d4={-2,-2,-3,-3,6,-3,-1,0,-3,0,0,-3,-4,-3,-3,-2,-2,-1,1,3};
			 int[] d5={0,-3,-1,-2,-3,6,-2,-4,-2,-4,-3,0,-2,-2,-2,0,-2,-3,-2,-3};
			 int[] d6={-2,-3,-1,0,-1,-2,8,-3,-1,-3,-2,1,-2,0,0,-1,-2,-3,-2,2};
			 int[] d7={-1,-1,-3,-3,0,-4,-3,4,-3,2,1,-3,-3,-3,-3,-2,-1,3,-3,-1};
			 int[] d8={-1,-3,-1,1,-3,-2,-1,-3,5,-2,-1,0,-1,1,2,0,-1,-2,-3,-2};
			 int[] d9={-1,-1,-4,-3,0,-4,-3,2,-2,4,2,-3,-3,-2,-2,-2,-1,1,-2,-1};
			 int[] d10={-1,-1,-3,-2,0,-3,-2,1,-1,2,5,-2,-2,0,-1,-1,-1,1,-1,-1};
			 int[] d11={-2,-3,1,0,-3,0,1,-3,0,-3,-2,6,-2,0,0,1,0,-3,-4,-2};
			 int[] d12={-1,-3,-1,-1,-4,-2,-2,-3,-1,-3,-2,-2,7,-1,-2,-1,-1,-2,-4,-3};
			 int[] d13={-1,-3,0	,2,-3,-2,0,-3,1,-2,0,0,-1,5,1,0,-1,-2,-2,-1};
			 int[] d14={-1,-3,-2,0,-3,-2,0,-3,2,-2,-1,0,-2,1,5,-1,-1,-3,-3,-2};
			 int[] d15={1,-1,0,0,-2,0,-1,-2,0,-2,-1,1,-1,0,-1,4,1,-2,-3,-2};
			 int[] d16={0,-1,-1,-1,-2,-2,-2,-1,-1,-1,-1,0,-1,-1,-1,1,5,0,-2,-2};
			 int[] d17={0,-1,-3,-2,-1,-3,-3,3,-2,1,1,-3,-2,-2,-3,-2,0,4,-3,-1};
			 int[] d18={-3,-2,-4,-3,1,-2,-2,-3,-3,-2,-1,-4,-4,-2,-3,-3,-2,-3,11,2};
			 int[] d19={-2,-2,-3,-2,3,-3,2,	-1,-2,-1,-1,-2,-3,-1,-2,-2,-2,-1,2,7};
			 
			 
			 BM[0]=d0;	BM[1]=d1;	BM[2]=d2;	BM[3]=d3;
			 BM[4]=d4;	BM[5]=d5;	BM[6]=d6;	BM[7]=d7;
			 BM[8]=d8;	BM[9]=d9;	BM[10]=d10;	BM[11]=d11;
			 BM[12]=d12;	BM[13]=d13;	BM[14]=d14;	BM[15]=d15;
			 BM[16]=d16;	BM[17]=d17;	BM[18]=d18;	BM[19]=d19;
			 
			 //a min and max function!!!!!
			 int MinM=BM[0][0];
			 int MaxM=MinM;
			 for (int r=0;r<20;r++)
			 {
				 for (int c=0;c<20;c++)
				 {
					 int d=BM[r][c];
					 if (d<MinM)
						 MinM=d;
					 else if (d>MaxM)
						 MaxM=d;				 
				 }
			 }
			 int LengM=MaxM-MinM;
			 int Simab=0;
			 int SeqL=ProteinSeq1.length();
			 char []Seq1=ProteinSeq1.toCharArray();
			 char []Seq2=ProteinSeq2.toCharArray();
			 for (int i=0;i<ProteinSeq1.length();i++)
			 {
				 //String OSet_position=;
				 //method 1
				 //int a=OSet.indexOf(Seq1[i])+1;//in java the 1st position is 0
				 //int b=OSet.indexOf(Seq2[i])+1;
				 //ProteinSeq里有几个字母的可能性
				 //method 2
				 int a=getIndex(Seq1[i]);
				 int b=getIndex(Seq2[i]);		 
				 if (a*b==0)
					 SeqL--;
				 else 
					 Simab=Simab+(BM[a][b]-MinM)/LengM;
				 
			 }
			KNNDis= Integer.toString(1-Simab/SeqL);
		 }
		 else 
			 KNNDis="Wrong";	 
	return KNNDis;	
	}
	/*
	private static int [][]blosum_order(int blosumnum, String oSet) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	public ArrayList<Integer> getTxt(String filepath)
	{
		try{
			String temp = null;
			File f = new File (filepath);
			String adn="";
			InputStreamReader read = new InputStreamReader(new FileInputStream(f));
			 ArrayList<Integer> readList = new ArrayList<Integer>();
			 BufferedReader reader = new BufferedReader(read);
			 while ((temp=reader.readLine())!=null &&!"".equals(temp)){ 
				 readList.add(temp);
			 }
			 read.close();
			 return retList;
			 
		}catch (Exception e){
			logger.info("read fail");
			e.printStackTrace();
			return null;
			
		}
	}
	
*/	
	
/*	
 * 
 * 
 * 
 * 
 * 
 * 
 * 
	// reads in an amino acid substitution matrix, if necessary
	private static SubstitutionMatrix<AminoAcidCompound> getAminoAcidMatrix(String file) {
		if (!aminoAcidMatrices.containsKey(file)) {
			aminoAcidMatrices.put(file, new SimpleSubstitutionMatrix<AminoAcidCompound>(
					AminoAcidCompoundSet.getAminoAcidCompoundSet(), getReader(file), file));
		}
		return aminoAcidMatrices.get(file);
	}
*/	
	// quick and dirty equivalent of typesafe enum pattern, can also use HashMap
    // or even better, EnumMap. 
    // This code is for Java 1.4.2, so we will stick to the simple implementation
    private static int getIndex(char a) {
    	// check for upper and lowercase characters
    	switch ((String.valueOf(a)).toUpperCase().charAt(0)) {
	    	case 'A': return 1;
	    	case 'C': return 2;
	    	case 'D': return 3;
	    	case 'E': return 4;
	    	case 'F': return 5;
	    	case 'G': return 6;
	    	case 'H': return 7;
	    	case 'I': return 8;
	    	case 'K': return 9;
	    	case 'L': return 10;
	    	case 'M': return 11;
	    	case 'N': return 12;
	    	case 'O': return 13;
	    	case 'P': return 14;
	    	case 'Q': return 15;
	    	case 'R': return 16;
	    	case 'S': return 17;
	    	case 'T': return 18;
	    	case 'V': return 19;
	    	case 'W': return 20;
	    	case 'Y': return 21;
	    	default: return 0;
    	}
    }
	

}
