import util.InputReader;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by mokarakaya on 13.09.2016.
 */
public class PathSumFourWaysP83 {
    public static void main(String[] args) throws FileNotFoundException {
        PathSumFourWaysP83 t = new PathSumFourWaysP83();
        System.out.println(t.solution());
    }
    private int solution() throws FileNotFoundException {
        int[][] cost = InputReader.readIntMatrix(80, "c:/Temp/p083_matrix.txt");
        Node[][] shortestPaths=new Node[80][80];

        /*int[][]cost={{1,2,3},{1,2,3},{1,2,3}};
        Node[][] shortestPaths=new Node[3][3];*/
        Node startNode= new Node();
        startNode.cost=cost[0][0];
        shortestPaths[0][0]=startNode;
        return runDjkstra(cost,shortestPaths,0,0);
    }

    private int runDjkstra(int[][] cost, Node[][] shortestPaths,int i,int j) {
        while(true) {
            Node node = shortestPaths[i][j];
            if (i != 0) {
                updateCost(cost, shortestPaths, i - 1, j, node);
            }
            if (j != 0) {
                updateCost(cost, shortestPaths, i, j - 1, node);
            }
            if (i != cost.length - 1) {
                updateCost(cost, shortestPaths, i + 1, j, node);
            }
            if (j != cost[0].length - 1) {
                updateCost(cost, shortestPaths, i, j + 1, node);
            }
            node.visited = true;
            Map<String, Integer> map = getShortestUnvisited(shortestPaths);
            if (map.get("i") == null) {
                return shortestPaths[i][j].cost;
            }
            i=map.get("i");
            j=map.get("j");
        }
    }

    private Map<String, Integer> getShortestUnvisited(Node[][] shortestPaths) {
        Map<String,Integer> map=new HashMap<>();
        int shortest=Integer.MAX_VALUE;
        for(int i=0;i<shortestPaths.length;i++){
            for(int j=0;j<shortestPaths[0].length;j++){
                Node node=shortestPaths[i][j];
                if(node!=null && !node.visited && node.cost<shortest){
                    map.put("i",i);
                    map.put("j",j);
                    shortest=node.cost;
                }
            }
        }
        return map;
    }

    private void updateCost(int[][] cost, Node[][] shortestPaths, int i, int j,Node parent) {
        if(shortestPaths[i][j]==null){
            shortestPaths[i][j]=new Node();
        }
        Node node= shortestPaths[i][j];
        int newPath = parent.cost + cost[i][j];
        if(node.cost>newPath){
            node.cost=newPath;
        }
    }


}
 class Node {
    public int cost;
    public boolean visited;
    Node(){
        this.cost=Integer.MAX_VALUE;
    }


}
