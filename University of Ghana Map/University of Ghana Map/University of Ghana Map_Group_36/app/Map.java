package app;

public class Map extends Digraph {
    Extent dg;
    @Override
    public void addEdge(Extent edge){

        this.dg = edge;
        if(ghu.contains(edge)) return;

        this.ghu.add(edge);
        this.ghu.add(new Extent(edge.getDestination(), edge.getSource(), edge.getDistance()));
        for(Locations vertex : map.keySet()){
            if (vertex == edge.getSource()){
                map.get(vertex).add(edge.getDestination());
            }
        }


        for(Locations vertex : map.keySet()){
            if (vertex == edge.getDestination()){
                map.get(vertex).add(edge.getSource());
            }
        }
    }

    public int getDistance(Locations locations, Locations locations1){
        return dg.getDistance();
    }
}
