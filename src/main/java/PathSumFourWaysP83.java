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
        List<Node> openList=new ArrayList<Node>();
        List<Node> closedList=new ArrayList<Node>();
        //int[][] cost = InputReader.readIntMatrix(80, "c:/Temp/p083_matrix.txt");
        //Node[][] parents=new Node[80][80];
        //int totalCost=cost[79][79];
        int[][]cost={{1,2,3},{1,2,3},{1,2,3}};
        Node[][] parents=new Node[3][3];
        int totalCost=cost[2][2];

        Node edgeNode = runAStar(openList, closedList, new Node(0, 0), cost,parents );

        while(edgeNode!=null){
            totalCost+=cost[edgeNode.i][edgeNode.j];
            edgeNode=parents[edgeNode.i][edgeNode.j];
        }
        return totalCost;
    }

    private Node runAStar(List<Node> openList, List<Node> closedList, Node node, int[][] cost, Node[][] parents) {
        closedList.add(node);
        if(!setParentForNeighbors(openList,closedList,node,cost,parents)){
            Node next=getNext(openList);
            return runAStar(openList,closedList,next,cost,parents);
        }else{
            return node;
        }

    }
    private boolean setParentForNeighbors(List<Node> openList,List<Node> closedList, Node node, int[][] cost, Node[][] parents) {

        boolean edgeNode=false;
        if(node.i!=0){
            edgeNode= edgeNode || setParentForNeighbor(openList,closedList,node,cost,parents,node.i-1,node.j);
        }
        if(node.j!=0){
            edgeNode= edgeNode || setParentForNeighbor(openList,closedList,node,cost,parents,node.i,node.j-1);
        }
        if(node.i!=cost.length-1){
            edgeNode= edgeNode || setParentForNeighbor(openList,closedList,node,cost,parents,node.i+1,node.j);
        }
        if(node.j!=cost[0].length-1){
            edgeNode= edgeNode || setParentForNeighbor(openList,closedList,node,cost,parents,node.i,node.j+1);
        }
        return edgeNode;
    }

    private boolean setParentForNeighbor(List<Node> openList, List<Node> closedList, Node node, int[][] cost,
                                         Node[][] parents, int i, int j) {
        Node newNode = new Node(i, j);
        if(!closedList.contains(newNode)) {
            parents[i][j] = node;
            newNode.calculateGCost(cost);
            openList.add(newNode);
        }
        return i == cost.length - 1 && j == cost[0].length - 1;
    }
    
    private Node getNext(List<Node> openList) {
        openList.sort((o1,o2)->o1.gCost-o2.gCost);
        Node first = openList.iterator().next();
        openList.remove(first);
        return first;
    }


}

class Node {
    public int i;
    public int j;
    public int gCost;
    Node(int i, int j){
        this.i=i;
        this.j=j;
    }

    public void calculateGCost(int[][] cost) {
        gCost=(cost.length-1 -i + cost[0].length-1-j)+cost[i][j];
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof Node){
            Node obj= (Node) o;
            return i==obj.i&& j==obj.j;
        }
        return false;
    }
    @Override
    public int hashCode(){
        int result=1;
        result+=31*i;
        result+=23*j;
        return result;
    }
}
