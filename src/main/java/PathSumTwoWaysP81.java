import util.InputReader;

import java.io.FileNotFoundException;

/**
 * Created by mokarakaya on 9/7/2016.
 */
public class PathSumTwoWaysP81 {

    public static final int SIZE = 80;

    public static void main(String[] args) throws FileNotFoundException {
        PathSumTwoWaysP81 t = new PathSumTwoWaysP81();
        System.out.println(t.solution());
    }
    private double  solution() throws FileNotFoundException {
        double[][]totalCost= new double[SIZE][SIZE];
        InputReader reader=new InputReader();
        double[][]cost= reader.readMatrix(SIZE,"81");
        totalCost[0][0]=cost[0][0];
        return findMinPath(cost,totalCost,SIZE-1,SIZE-1);
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
