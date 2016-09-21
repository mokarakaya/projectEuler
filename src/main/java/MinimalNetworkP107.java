
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mokarakaya on 19.09.2016.
 */
public class MinimalNetworkP107 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Edge> list = readList("c:/Temp/p107_network.txt");
        MinimalNetworkP107 t = new MinimalNetworkP107();
        System.out.println(t.solution(list));
    }

    private int solution(List<Edge> list) {
        Map<Integer, Integer> nodes=new HashMap<>();
        Map<Integer, List<Edge>>graphs=new HashMap<>();
        for (Edge edge : list) {
            if(nodes.get(edge.nodes[0])==null && nodes.get(edge.nodes[1])==null){
                nodes.put(edge.nodes[0],edge.nodes[0]);
                nodes.put(edge.nodes[1],edge.nodes[0]);
                graphs.putIfAbsent(edge.nodes[0],new ArrayList<>());
                graphs.get(edge.nodes[0]).add(edge);
            }else if(nodes.get(edge.nodes[0])==null && nodes.get(edge.nodes[1])!=null){
                nodes.put(edge.nodes[0],nodes.get(edge.nodes[1]));
                graphs.putIfAbsent(nodes.get(edge.nodes[1]),new ArrayList<>());
                graphs.get(nodes.get(edge.nodes[1])).add(edge);
            }else if(nodes.get(edge.nodes[0])!=null && nodes.get(edge.nodes[1])==null){
                nodes.put(edge.nodes[1],nodes.get(edge.nodes[0]));
                graphs.putIfAbsent(nodes.get(edge.nodes[0]),new ArrayList<>());
                graphs.get(nodes.get(edge.nodes[0])).add(edge);
            }else if(nodes.get(edge.nodes[0])!=null && nodes.get(edge.nodes[1])!=null
                    &&nodes.get(edge.nodes[0]).intValue()!=nodes.get(edge.nodes[1])){
                merge(nodes,edge,graphs);
            }

        }

        int sum = graphs.get(graphs.keySet().iterator().next())
                .stream().mapToInt(edge -> edge.cost).sum();
        int total=list.stream().mapToInt(edge -> edge.cost).sum();
        return total-sum;
    }

    private void merge(Map<Integer, Integer> nodes, Edge edge, Map<Integer, List<Edge>> graphs) {
        graphs.get(nodes.get(edge.nodes[0])).addAll(graphs.get(nodes.get(edge.nodes[1])));
        graphs.get(nodes.get(edge.nodes[0])).add(edge);
        graphs.remove(nodes.get(edge.nodes[1]));
        List<Integer> collect = nodes.entrySet().stream()
                .filter(map -> map.getValue().intValue() == nodes.get(edge.nodes[1]).intValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        for(int key: collect){
            nodes.put(key,nodes.get(edge.nodes[0]));
        }

    }

    public static List<Edge> readList(String fileName) throws FileNotFoundException {
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
