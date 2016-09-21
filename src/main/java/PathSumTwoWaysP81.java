import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by mokarakaya on 9/7/2016.
 */
public class PathSumTwoWaysP81 {
    public static void main(String[] args) throws FileNotFoundException {
        PathSumTwoWaysP81 t = new PathSumTwoWaysP81();
        System.out.println(t.solution());
    }
    private double  solution() throws FileNotFoundException {
        double[][]totalCost= new double[80][80];
        double[][]cost=new double[80][80];
        Scanner input = new Scanner(new File("d:/p081_matrix.txt"));
        int count=0;
        while(input.hasNextLine())
        {
            String[] split = input.nextLine().split(",");
            for(int i=0;i<split.length;i++){
                cost[count][i]=Double.parseDouble(split[i]);
            }
            count++;

        }
        totalCost[0][0]=cost[0][0];
        return findMinPath(cost,totalCost,79,79);
    }

    private double findMinPath(double[][] cost, double[][] totalCost, int i, int j) {
        if(totalCost[i][j]!=0){
            return totalCost[i][j];
        }
        double min=Double.MAX_VALUE;
        if(i!=0){
            min=Math.min(min,findMinPath(cost,totalCost,i-1,j)+cost[i][j]);
        }
        if(j!=0){
            min=Math.min(min,findMinPath(cost,totalCost,i,j-1)+cost[i][j]);
        }
        totalCost[i][j]=min;
        return totalCost[i][j];
    }
}
