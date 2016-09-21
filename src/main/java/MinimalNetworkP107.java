
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mokarakaya on 19.09.2016.
 */
public class MinimalNetworkP107 {
    public static void main(String[] args) throws FileNotFoundException {

        MinimalNetworkP107 t = new MinimalNetworkP107();
        List<Edge> list = t.readList("c:/Temp/p107_network.txt");
        System.out.println(t.solution(list));
    }

    private int solution(List<Edge> list) {
        //keeps nodeId,graphId -> so, finding graph of the node is O(1)
        Map<Integer, Integer> nodes=new HashMap<>();
        //keeps graphId, graph
        Map<Integer, List<Edge>>graphs=new HashMap<>();
        for (Edge edge : list) {
            Integer firstGraphId = nodes.get(edge.nodes[0]);
            Integer secondGraphId = nodes.get(edge.nodes[1]);
            if(firstGraphId ==null && secondGraphId ==null){
                nodes.put(edge.nodes[0],edge.nodes[0]);
                nodes.put(edge.nodes[1],edge.nodes[0]);
                List<Edge> graphList=new ArrayList<>();
                graphList.add(edge);
                graphs.put(edge.nodes[0],graphList);
            }else if(firstGraphId ==null && secondGraphId !=null){
                nodes.put(edge.nodes[0], secondGraphId);
                graphs.get(secondGraphId).add(edge);
            }else if(firstGraphId !=null && secondGraphId ==null){
                nodes.put(edge.nodes[1], firstGraphId);
                graphs.get(firstGraphId).add(edge);
            }else if(firstGraphId !=null && secondGraphId !=null
                    && firstGraphId.intValue()!= secondGraphId){
                merge(nodes,edge,graphs);
            }

        }

        //we have only one graph which is minimum spanning tree.
        int sum = graphs.get(graphs.keySet().iterator().next())
                .stream().mapToInt(edge -> edge.cost).sum();
        int total=list.stream().mapToInt(edge -> edge.cost).sum();
        return total-sum;
    }

    private void merge(Map<Integer, Integer> nodes, Edge edge, Map<Integer, List<Edge>> graphs) {
        final Integer firstGraphId = nodes.get(edge.nodes[0]);
        final Integer secondGraphId = nodes.get(edge.nodes[1]);
        graphs.get(firstGraphId).addAll(graphs.get(secondGraphId));
        graphs.get(firstGraphId).add(edge);
        graphs.remove(secondGraphId);
        List<Integer> collect = nodes.entrySet().stream()
                .filter(map -> map.getValue()== secondGraphId)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        for(int key: collect){
            nodes.put(key, firstGraphId);
        }

    }

    public List<Edge> readList(String fileName) throws FileNotFoundException {
        Scanner input = new Scanner(new File(fileName));
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

}
class Edge {
    public int[] nodes;
    public int cost;
    public Edge(int i, int j, int cost){
        nodes= new int[2];
        nodes[0]=i;
        nodes[1]=j;
        this.cost=cost;

    }

}
