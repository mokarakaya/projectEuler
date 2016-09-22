
import util.Edge;
import util.InputReader;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mokarakaya on 19.09.2016.
 */
public class MinimalNetworkP107 {
    public static void main(String[] args) throws FileNotFoundException {

        MinimalNetworkP107 t = new MinimalNetworkP107();
        InputReader reader=new InputReader();
        List<Edge> list = reader.readList("107");
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
            }else if(firstGraphId ==null ){
                nodes.put(edge.nodes[0], secondGraphId);
                graphs.get(secondGraphId).add(edge);
            }else if(secondGraphId ==null){
                nodes.put(edge.nodes[1], firstGraphId);
                graphs.get(firstGraphId).add(edge);
            }else if(!firstGraphId.equals(secondGraphId)){
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
                .filter(map -> map.getValue().equals(secondGraphId))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        for(int key: collect){
            nodes.put(key, firstGraphId);
        }

    }



}
