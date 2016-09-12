import util.InputReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by mokarakaya on 9/7/2016.
 */
public class PathSumThreeWaysP82 {
    public static void main(String[] args) throws FileNotFoundException {
        PathSumThreeWaysP82 t = new PathSumThreeWaysP82();
        System.out.println(t.solution());
    }

    private double  solution() throws FileNotFoundException {
       double[][]totalCost= new double[80][80];


        /*double[][]totalCost= new double[3][3];

        double[][]cost={{1,2,3},{1,2,3},{1,2,3}};*/
        double [][]cost = InputReader.readMatrix(80,"d:/p082_matrix.txt");
        for(int i=0;i<cost.length;i++){
            totalCost[i][cost[0].length-1]=cost[i][cost[0].length-1];

        }

        findMinPath(cost,totalCost);
        double min =totalCost[0][0];
        for(int i=0;i<cost.length;i++){
            min =Math.min(min,totalCost[i][0]);
        }

        return min;
    }

    private void findMinPath(double[][] cost, double[][] totalCost) {
        for(int j=cost.length-2;j>=0;j--){
            for (int i=0;i<cost[0].length;i++){
                double min=totalCost[i][j+1];
                double vertical=0;
                for (int k=i+1;k<cost.length;k++){
                    vertical+=cost[k][j];
                    min=Math.min(min,vertical+totalCost[k][j+1]);
                }
                vertical=0;
                for (int k=i-1;k>0;k--){
                    vertical+=cost[k][j];
                    min=Math.min(min,vertical+totalCost[k][j+1]);
                }
                totalCost[i][j]=min+cost[i][j];
            }
        }

    }
}
