/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musitepractice;

import java.util.ArrayList;

/**
 *
 * @author qiaoyang
 */
public class AAindex {

    static void GetSeqAAindex(String Seqinput)
    {
        int length_of_Seqinput = Seqinput.length();
        ArrayList<ArrayList<double[]>> AAindexAll = new ArrayList<>();
        ArrayList<ArrayList<double[]>> AAinsum = new ArrayList<>();
        double[][] AAindex_20 = new double[21][5];
        double[][] AAindex_20_std = new double[21][5];
        double[][] AAindex = new double[length_of_Seqinput][5];
        char[] tempSeri = Seqinput.toCharArray();
        int aaloc = 0;
        ArrayList<double[]> emptyList = new ArrayList<>();
        double[] AAindexset_add = new double[5];
        
        for (int i = 0; i < 21 ; i ++) AAinsum.add(emptyList);
        
        double[][] AAindexset = {
                                    {-0.591, -1.302, -0.733, 1.570, -0.146},
                                    {-1.343, 0.465, -0.862, -1.020, -0.255},
                                    {1.050, 0.302, -3.656, -0.259, -3.242},
                                    {1.357, -1.453, 1.477, 0.113, -0.837},
                                    {-1.006, -0.590, 1.891, -0.397, 0.412},
                                    {-0.384, 1.652, 1.330, 1.045, 2.064},
                                    {0.336, -0.417, -1.673, -1.474, -0.078},
                                    {-1.239, -0.547, 2.131, 0.393, 0.816},
                                    {1.831, -0.561, 0.533, -0.277, 1.648},
                                    {-1.019, -0.987, -1.505, 1.266, -0.912},
                                    {-0.663, -1.524, 2.219, -1.005, 1.212},
                                    {0.945, 0.828, 1.299, -0.169, 0.933},
                                    {0.189, 2.081, -1.628, 0.421, -1.392},
                                    {0.931, -0.179, -3.005, -0.503, -1.853},
                                    {1.538, -0.055, 1.502, 0.440, 2.897},
                                    {-0.228, 1.399, -4.760, 0.670, -2.647},
                                    {-0.032, 0.326, 2.213, 0.908, 1.313},
                                    {-1.337, -0.279, -0.544, 1.242, -1.262},
                                    {-0.595, 0.009, 0.672, -2.128, -0.184},
                                    {0.260, 0.830, 3.097, -0.838, 1.512},
                                };
        
        for(int i = 0; i < length_of_Seqinput; i++)
        {
            aaloc = Amino2Index(tempSeri[i]);
            if(aaloc == 21)
            {
                for(int j = 0; j < 5; j++)
                {
                    AAindex[i][j] = 0;
                }
            }
            else
            {
                for(int k = 0; k < 5; k++)
                {
                    AAindex[i][k] = AAindexset[aaloc][k];
                    for(int m = 0; m < 5; m++)
                    {
                        AAindexset_add[m] = AAindexset[aaloc][m];                  
                    }
                    
                    (AAinsum.get(aaloc-1)).add(AAindexset_add);
                }
            }
        }
        
        for(int n = 0; n < 5; n++)
        {
            
        }
    }
       
    static int Amino2Index(char Seqinput_char)
    {
        int i = 0;
        if(Seqinput_char=='a' || Seqinput_char=='A')
        {
            i = 1;
        }
        else if(('c'<=Seqinput_char)&&(Seqinput_char<='i'))
        {
            i = Seqinput_char - 'c' + 2;
        }
        else if(('C'<=Seqinput_char)&&(Seqinput_char<='I'))
        {
            i = Seqinput_char - 'C' + 2;
        }
        else if(('k'<=Seqinput_char)&&(Seqinput_char<='n'))
        {
            i = Seqinput_char - 'k' + 9;
        }
        else if(('K'<=Seqinput_char)&&(Seqinput_char<='N'))
        {
            i = Seqinput_char - 'K' + 9;
        }
        else if(('p'<=Seqinput_char)&&(Seqinput_char<='t'))
        {
            i = Seqinput_char - 'p' + 13;
        }
        else if(('P'<=Seqinput_char)&&(Seqinput_char<='T'))
        {
            i = Seqinput_char - 'P' + 13;
        }
        else if(('v'<=Seqinput_char)&&(Seqinput_char<='w'))
        {
            i = Seqinput_char - 'v' + 18;
        }
        else if(('V'<=Seqinput_char)&&(Seqinput_char<='W'))
        {
            i = Seqinput_char - 'V' + 18;
        }
        else if(Seqinput_char=='y' || Seqinput_char=='Y')
        {
            i = 20;
        }
        else i = 21;
        
        return i;
    }
    
}
