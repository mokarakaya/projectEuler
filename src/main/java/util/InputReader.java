package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by mokarakaya on 13.09.2016.
 */
public class InputReader {

    public double[][] readMatrix(int size,String fileName) throws FileNotFoundException {
        double[][]cost=new double[size][size];
        Scanner input = new Scanner(getFile(fileName));
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
    public Map<Integer,Integer>  readInput(String fileName) throws FileNotFoundException {
        Map<Integer,Integer> cost=new HashMap<>();
        File file = getFile(fileName);
        Scanner input = new Scanner(file);
        int count=0;
        while(input.hasNextLine())
        {
            String[] split = input.nextLine().split(",");
            int size=split.length;
            for(int i=0;i<split.length;i++){
                cost.put(size*count+i,Integer.parseInt(split[i]));
            }
            count++;
        }

        return cost;
    }
    public List<Edge> readList(String fileName) throws FileNotFoundException {
        Scanner input = new Scanner(getFile(fileName));
        int count=0;
        List<Edge> list= new ArrayList<>();
        while(input.hasNextLine())
        {
            String[] split = input.nextLine().split(",");
            for(int i=count;i<split.length;i++){
                String splitted = split[i];
                if(!"-".equals(splitted)){
                    list.add(new Edge(count,i,Integer.parseInt(split[i])));
                }
            }
            count++;

        }
        list.sort((edge1,edge2)->edge1.cost-edge2.cost);
        return list;
    }
    private File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName+".txt").getFile());
    }
}
