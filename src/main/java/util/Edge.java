package util;

/**
 * Created by mokarakaya on 23.09.2016.
 */
public class Edge {
    public int[] nodes;
    public int cost;
    public Edge(int i, int j, int cost){
        nodes= new int[2];
        nodes[0]=i;
        nodes[1]=j;
        this.cost=cost;

    }

}
