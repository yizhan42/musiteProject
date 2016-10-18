import java.util.*;
import java.io.*;
import java.lang.*;
import static java.lang.Math.exp;
import static java.lang.StrictMath.abs;
import java.math.BigDecimal;
import java.text.*;


public class blastpassm_seq
{
	public static void blastpssm_seq(String seq, String db) throws IOException
	{
		//what is db? package?
		String heads = "LaLa";

		String seqs = seq;

		//path to db file

		//cmd is a windows computer command
		//I think it is to start db package

		int seqLength = seqs.length();

		double[][] p = new double[seqLength][1];
        for(int i = 0; i < seqLength; i++)
        {
            Arrays.fill(p[i], 0.0);
        }

        File fid_in = new File("inputtmp.fasta");
      
		// creates the file
		fid_in.createNewFile();

		// creates a FileWriter Object
		FileWriter writer = new FileWriter(fid_in); 

		// Writes the content to the file
		writer.write(seqs); 
		writer.flush();
		writer.close();

		FileInputStream fid_out = new FileInputStream("pssmresult");

		//Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fid_out));

		String line = null;
		ArrayList<String> tline = new ArrayList<String>();

		while ((line = br.readLine()) != null) 
		{
			//System.out.println(line);
			tline.add(line);
		}

		//while()

		br.close();
   
	}

}