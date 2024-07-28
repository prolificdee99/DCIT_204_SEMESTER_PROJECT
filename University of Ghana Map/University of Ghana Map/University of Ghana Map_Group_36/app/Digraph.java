package app;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;



public class Digraph {
    protected final HashMap<Locations, ArrayList<Locations>> map = new HashMap<>();
    protected final ArrayList<Extent> ghu = new ArrayList<>();
    private int vertexSize = 0;

    public void addVertex(Locations vertex){
        if (!map.containsKey(vertex)){
            map.put(vertex, new ArrayList<>());
            vertexSize++;
        }
    }

    public void addEdge(Extent edge){
        if(ghu.contains(edge)) return;

        this.ghu.add(edge);
        for(Locations source : map.keySet()){
            if (source == edge.getSource()){
                map.get(source).add(edge.getDestination());
            }
        }
    }

    public ArrayList<Extent> getDestinationEdges(Locations source){
        ArrayList<Extent> destinations = new ArrayList<>();
        for (Extent edge: this.ghu){
            if (edge.getSource() == source){
                 destinations.add(edge);
            }
        }
        return destinations;
    }

     public Extent getEdge(Locations source, Locations destination) {
       for (Extent edge: this.ghu){
           if (edge.getSource() == source && edge.getDestination() == destination){
               return edge;
           }
       }
       return null;
    }

    public Locations getNodeByName(String name){
        for(Locations vertex : map.keySet()){
            if (vertex.getName().toLowerCase().equals(name.toLowerCase())){
                return vertex;
            }
        }
        return null;
    }

    public Set<Locations> getNodes(){
        return this.map.keySet();
    }

    public int getNodeSize() {
        return vertexSize;
    }

    public void printGraph(){
        System.out.println("\n          GRAPH: ADJACENCY LIST                ");
        System.out.println("              PLACES ON CAMPUS                 \n");
        for (HashMap.Entry<Locations, ArrayList<Locations>> entry : map.entrySet()) {
            Locations vertex = entry.getKey();
            ArrayList<Locations> destinations = entry.getValue();
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            boolean emptyList = true;
            for (Locations destinatnion : destinations){
                if(emptyList)
                builder.append(destinatnion.getName());
                else
                builder.append(", "+destinatnion.getName());
                emptyList = false;
            }
            builder.append("]");
             System.out.println(vertex.getName() + "-->" + builder.toString());
            System.out.println("");
        }
    }

    public void listPlaces(Locations except){
        int index = 1;
        for(Locations vertex : map.keySet()){
            if (vertex != except){
                System.out.println(index + ". " + vertex.getName());
            }
            index++;
        }
    }

}
