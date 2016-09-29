package musitepractice;

import java.util.*;
import java.io.*;
import java.lang.*;
import static java.lang.Math.exp;
import static java.lang.StrictMath.abs;
import java.math.BigDecimal;
import java.text.*;


public class greyPsePssm_seq
{
    
    public static double[] greyPsePssm_seq(double[][] pssm20, int n)
    {
        int len = pssm20.length;
        double[] psepssm = null;
        double[][] pssm = new double[len][20];
        double[] pssm_column = new double[len];
        double[] p;
            
        if(n == 1)
        { 
            psepssm = new double[60];
            Arrays.fill(psepssm, 0.0);
            for(int i = 0; i < len; i++)
            {
                for(int j = 0; j < 20; j++)
                {
                    if((1 + exp(-pssm20[i][j])) == 0) pssm[i][j] = 0;
                    else
                        pssm[i][j] = 1.0 / (1 + exp(-pssm20[i][j]));
                }
            }           
            for(int k = 0; k < 20; k++)
            {
                for(int m = 0; m < len; m++)
                {
                    pssm_column[m] = pssm[m][k];
                }
                psepssm[k] = mean(pssm_column);
                p = GM21Param(pssm_column);
                psepssm[20+2*k-2] = abs(p[0]);
                psepssm[20+2*k-1] = abs(p[1]);     
            }
            
            System.out.println("================psepssm================");
            for(int i = 0; i < 60; i++)
            {  
                System.out.println(i+". "+psepssm[i]);
            }
        }

        else if(n == 2)
        {
            psepssm = new double[80];
            Arrays.fill(psepssm, 0.0);
            for(int i = 0; i < len; i++)
            {
                for(int j = 0; j < 20; j++)
                {
                    if((1 + exp(-pssm20[i][j])) == 0) pssm[i][j] = 0;
                    else
                        pssm[i][j] = 1.0 / (1 + exp(-pssm20[i][j]));
                }
            }           
            for(int k = 0; k < 20; k++)
            {
                for(int m = 0; m < len; m++)
                {
                    pssm_column[m] = pssm[m][k];
                }
                psepssm[k] = mean(pssm_column);
                p = GM21Param(pssm_column);
                psepssm[20+3*k-3] = abs(p[0]);
                psepssm[20+3*k-2] = abs(p[1]);    
                psepssm[20+3*k-1] = abs(p[2]);  
            }
            
            System.out.println("================psepssm================");
            for(int i = 0; i < 80; i++)
            {
                System.out.println(i+". "+psepssm[i]);
            }
        }
        
        return psepssm;
    }
    

    public static double mean(double[] m) 
    {
        double sum = 0;
        for (int i = 0; i < m.length; i++) 
        {
            sum += m[i];
        }
        return sum / m.length;
    }

    public static double[] GM21Param(double[] x)
    {
        int n = x.length;
        
        double[] x1 = new double[n];
        Arrays.fill(x1, 0.0);

        x1[0] = x[0];

        for(int i = 1; i < n; i++)
        {
            x1[i] = x1[i - 1] + x[i];
        }

        double[] z = new double[n];
        Arrays.fill(z, 0.0);

        for(int i = 1; i < n; i++)
        {
            z[i] = 0.5 * (x1[i] + x1[i - 1]);
        }

        double[][] b = new double[n - 1][3];
        for(int i = 0; i < n-1; i++)
        {
            Arrays.fill(b[i], 0.0);
        }
        
        for(int k = 0; k < n - 1; k++)
        {
            b[k][0] = -x[k + 1];
            b[k][1] = -z[k + 1];
            b[k][2] = 1;
        }

        double[][] y = new double[n - 1][1];
        for(int i = 0; i < n-1; i++)
        {
            Arrays.fill(y[i], 0.0);
        }
        

        for(int k = 0; k < n - 1; k++)
        {
            y[k][0] = x[k + 1] - x[k];
        }

       
        //a = inv(B'*B)*B'*Y
        double[][] a;
        a = MyMatrix.multiply(MyMatrix.multiply(MyMatrix.invert(MyMatrix.multiply(MyMatrix.transpose(b), b)),MyMatrix.transpose(b)),y);
        
        double c = 0.0, d = 0.0, e = 0.0, f = 0.0, g = 0.0, h = 0.0, i = 0.0, l = 0.0;

        for(int k = 1; k < n; k++)
        {
            c += x[k];
            d += x[k] * x[k];
            e += z[k];
            f += z[k] * z[k];
            g += x[k] * z[k];
            h += x[k - 1] * x[k];
            i += x[k - 1] * z[k];
            l = x[n-1] - x[1];
        }

        double[] p = new double[11];
        p[0] = a[0][0]; 
        p[1] = a[1][0]; 
        p[2] = a[2][0];
        p[3] = c; 
        p[4] = d; 
        p[5] = e;
        p[6] = f; 
        p[7] = g; 
        p[8] = h;
        p[9] = i; 
        p[10] = l;

        //double aa = isnan means Array elements that are NaN;
        //p[aa] = 0;在前面求pssm时已写到
        return p;

    }

}
