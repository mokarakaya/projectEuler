import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mokarakaya on 13.09.2016.
 */
public class PathSumFourWaysP83 {
    public static void main(String[] args) throws FileNotFoundException {
        PathSumFourWaysP83 t = new PathSumFourWaysP83();
        System.out.println(t.solution());
    }

    private int solution() {
        List<Node> openList=new ArrayList<Node>();
        List<Node> closedList=new ArrayList<Node>();
        Node endNode=runAStar(openList,closedList,new Node(0,0));

        return -1;
    }

    private Node runAStar(List<Node> openList, List<Node> closedList, Node node) {
        
        return null;
    }
}

class Node {
    public int i;
    public int j;
    public Node parent;
    public int gCost;
    Node(int i, int j){
        this.i=i;
        this.j=j;
    }
}
