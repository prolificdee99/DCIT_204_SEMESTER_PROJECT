package app;

import java.util.ArrayList;
import java.util.HashMap;

public class VogelApproximation {
    private static final ArrayList<Locations> NOT_VISITED = new ArrayList<>();
    private static final HashMap<Locations, Integer> SUPPLY = new HashMap<>();
    private static final HashMap<Locations, Integer> DEMAND = new HashMap<>();
    private static final HashMap<Locations, Locations> ASSIGNMENT = new HashMap<>();

    public static void findOptimalAssignment(Digraph graph, HashMap<Locations, Integer> supply, HashMap<Locations, Integer> demand) {
        // Initialize supply and demand maps
        SUPPLY.putAll(supply);
        DEMAND.putAll(demand);

        for (Locations vertex : graph.getNodes()) {
            NOT_VISITED.add(vertex);
        }

        while (!NOT_VISITED.isEmpty()) {
            Locations supplyLocation = null;
            Locations demandLocation = null;
            int maxDifference = Integer.MIN_VALUE;

            // Find the cell with the maximum difference
            for (Locations supplyNode : SUPPLY.keySet()) {
                if (NOT_VISITED.contains(supplyNode)) {
                    for (Locations demandNode : DEMAND.keySet()) {
                        if (NOT_VISITED.contains(demandNode)) {
                            int difference = Math.abs(SUPPLY.get(supplyNode) - DEMAND.get(demandNode));
                            if (difference > maxDifference) {
                                maxDifference = difference;
                                supplyLocation = supplyNode;
                                demandLocation = demandNode;
                            }
                        }
                    }
                }
            }

            if (maxDifference == 0) {
                // Balanced cell, assign supply and demand
                int quantity = Math.min(SUPPLY.get(supplyLocation), DEMAND.get(demandLocation));
                ASSIGNMENT.put(supplyLocation, demandLocation);
                SUPPLY.put(supplyLocation, SUPPLY.get(supplyLocation) - quantity);
                DEMAND.put(demandLocation, DEMAND.get(demandLocation) - quantity);

                if (SUPPLY.get(supplyLocation) == 0) {
                    NOT_VISITED.remove(supplyLocation);
                }
                if (DEMAND.get(demandLocation) == 0) {
                    NOT_VISITED.remove(demandLocation);
                }
            } else {
                // Unbalanced cell, find the minimum cost edge
                ArrayList<Extent> edges = graph.getDestinationEdges(supplyLocation);
                int minCost = Integer.MAX_VALUE;
                Locations minLocation = null;

                for (Extent edge : edges) {
                    if (edge.getDestination().equals(demandLocation)) {
                        int cost = edge.getDistance();
                        if (cost < minCost) {
                            minCost = cost;
                            minLocation = edge.getDestination();
                        }
                    }
                }

                if (minLocation != null) {
                    // Assign supply and demand
                    ASSIGNMENT.put(supplyLocation, minLocation);
                    SUPPLY.put(supplyLocation, SUPPLY.get(supplyLocation) - 1);
                    DEMAND.put(minLocation, DEMAND.get(minLocation) - 1);

                    if (SUPPLY.get(supplyLocation) == 0) {
                        NOT_VISITED.remove(supplyLocation);
                    }
                    if (DEMAND.get(minLocation) == 0) {
                        NOT_VISITED.remove(minLocation);
                    }
                }
            }
        }

        printOptimalAssignment();
    }

    private static void printOptimalAssignment() {
        System.out.println("Optimal Assignment:");
        for (Locations supplyLocation : ASSIGNMENT.keySet()) {
            Locations demandLocation = ASSIGNMENT.get(supplyLocation);
            System.out.println(supplyLocation.getName() + " -> " + demandLocation.getName());
        }
    }
}
