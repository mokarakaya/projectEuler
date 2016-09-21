package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
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
    public static Map<Integer,Integer>  readInput(int size,String fileName) throws FileNotFoundException {
        Map<Integer,Integer> cost=new HashMap<>();
        Scanner input = new Scanner(new File(fileName));
        int count=0;
        while(input.hasNextLine())
        {
            String[] split = input.nextLine().split(",");
            for(int i=0;i<split.length;i++){
                cost.put(size*count+i,Integer.parseInt(split[i]));
            }
            count++;
        }

        return cost;
    }
}
