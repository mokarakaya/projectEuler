package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by mokarakaya on 13.09.2016.
 */
public class InputReader {

    public static double[][] readMatrix(int size,String fileName) throws FileNotFoundException {
        double[][]cost=new double[size][size];
        Scanner input = new Scanner(new File(fileName));
        int count=0;
        while(input.hasNextLine())
        {
            String[] split = input.nextLine().split(",");
            for(int i=0;i<split.length;i++){
                cost[count][i]=Double.parseDouble(split[i]);
            }
            count++;

        }
        return cost;
    }
    public static int[][] readIntMatrix(int size,String fileName) throws FileNotFoundException {
        int[][]cost=new int[size][size];
        Scanner input = new Scanner(new File(fileName));
        int count=0;
        int min=Integer.MAX_VALUE;
        while(input.hasNextLine())
        {
            String[] split = input.nextLine().split(",");
            for(int i=0;i<split.length;i++){
                cost[count][i]=Integer.parseInt(split[i]);
                min=Math.min(min,cost[count][i]);
            }
            count++;

        }
        System.out.println(min);
        return cost;
    }
}
