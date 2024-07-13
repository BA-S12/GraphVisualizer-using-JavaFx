package com.example.graphvisualizer;
import java.util.*;

public class Graph<T> {
    private final Map<T, List<T>> adjVertices;

    public Graph() {
        adjVertices = new HashMap<>();
    }

    public void addVertex(T label) {
        adjVertices.putIfAbsent(label, new ArrayList<>());
    }

    public void removeVertex(T label) {
        adjVertices.values().forEach(e -> e.remove(label));
        adjVertices.remove(label);
    }

    public void addEdge(T label1, T label2) {
        adjVertices.get(label1).add(label2);
        adjVertices.get(label2).add(label1); // For an undirected graph
    }

    // Remove an edge between two vertices
    public void removeEdge(T label1, T label2) {
        List<T> eV1 = adjVertices.get(label1);
        List<T> eV2 = adjVertices.get(label2);
        if (eV1 != null) eV1.remove(label2);
        if (eV2 != null) eV2.remove(label1);
    }

    // Get adjacent vertices
    public List<T> getAdjVertices(T label) {
        return adjVertices.get(label);
    }

    public int degree(T vertex) {
        if (!adjVertices.containsKey(vertex))
            return -1;
        return adjVertices.get(vertex).size();
    }

    public int getMaxDegree() {
        int maxDegree = 0;
        for (T vertex : adjVertices.keySet()) {
            int degree = adjVertices.get(vertex).size();
            if (degree > maxDegree) {
                maxDegree = degree;
            }
        }
        return maxDegree;
    }

    public int countSelfLoops() {
        int count = 0;
        for (T vertex : adjVertices.keySet()) {
            for (T adjVertex : adjVertices.get(vertex)) {
                if (vertex.equals(adjVertex)) {
                    count++;
                }
            }
        }
        return count;
    }

    // Get all vertices
    public Set<T> getVertices() {
        return adjVertices.keySet();
    }

    // Get all edges
    public List<Pair<T, T>> getEdges() {
        List<Pair<T, T>> edges = new ArrayList<>();
        for (T vertex : adjVertices.keySet()) {
            for (T adjVertex : adjVertices.get(vertex)) {
                edges.add(new Pair<>(vertex, adjVertex));
            }
        }
        return edges;
    }

    public void printGraph() {
        for (T vertex : adjVertices.keySet()) {
            System.out.print(vertex + " -> ");
            for (T adjVertex : adjVertices.get(vertex)) {
                System.out.print(adjVertex + " ");
            }
            System.out.println();
        }
    }
    public static class Pair<U, V> {
        public final U first;
        public final V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }
}