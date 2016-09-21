import util.InputReader;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * we can run Dijkstra algorithm to find shortest path.
 * Created by mokarakaya on 13.09.2016.
 */
public class PathSumFourWaysP83 {
    private  static final int SIZE=80;
    public static void main(String[] args) throws FileNotFoundException {
        PathSumFourWaysP83 t = new PathSumFourWaysP83();
        System.out.println(t.solution());
    }
    private int solution() throws FileNotFoundException {
        Map<Integer, Integer> cost = InputReader.readInput(SIZE, "c:/Temp/p083_matrix.txt");
        Map<Integer,Node>  shortestPaths=new HashMap<>();
        Node startNode= new Node();
        startNode.cost=cost.get(0);
        shortestPaths.put(0,startNode);
        return runDijkstra(cost,shortestPaths,0);
    }

    private int runDijkstra(Map<Integer, Integer> cost, Map<Integer,Node> shortestPaths,int index) {
        //recursion returns stack over flow error.
        while(true) {
            Node node = shortestPaths.get(index);
            node.visited = true;
            //look up
            if (index >= SIZE) {
                updateCost(cost, shortestPaths, index-SIZE, node);
            }
            //look left
            if (index % SIZE != 0) {
                updateCost(cost, shortestPaths, index-1, node);
            }
            //look down
            if (index < SIZE*(SIZE-1)) {
                updateCost(cost, shortestPaths, index+SIZE, node);
            }
            //look right
            if (index %SIZE!= SIZE-1) {
                updateCost(cost, shortestPaths, index+1, node);
            }
            int shortestUnvisited= getShortestUnvisited(shortestPaths);
            if (shortestUnvisited==-1) {
                return shortestPaths.get(index).cost;
            }
            index=shortestUnvisited;
        }
    }

    private int getShortestUnvisited(Map<Integer,Node> shortestPaths) {
        Optional<Integer> shortestUnvisited = shortestPaths.entrySet().stream()
                .filter(node -> !node.getValue().visited)
                .min((o1, o2) -> o1.getValue().cost - o2.getValue().cost)
                .map(Map.Entry::getKey);
        return shortestUnvisited.isPresent()? shortestUnvisited.get():-1;
    }

    private void updateCost(Map<Integer,Integer> cost, Map<Integer,Node> shortestPaths, int index,Node parent) {
        shortestPaths.putIfAbsent(index,new Node());
        Node node= shortestPaths.get(index);
        node.cost=Math.min(node.cost,parent.cost + cost.get(index));
    }


}
 class Node {
    public int cost;
    public boolean visited;
    Node(){
        this.cost=Integer.MAX_VALUE;
    }


}
