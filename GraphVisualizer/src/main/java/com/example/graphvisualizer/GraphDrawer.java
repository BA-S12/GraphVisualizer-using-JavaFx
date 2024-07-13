package com.example.graphvisualizer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class GraphDrawer<T> {
    private final Graph<T> graph;

    public GraphDrawer(Graph<T> graph) {
        this.graph = graph;
    }

    public void drawVertex(GraphicsContext graphicsContext, double x, double y, T label) {
        graphicsContext.setFill(Color.PURPLE);
        graphicsContext.fillOval(x - 20, y - 20, 40, 40);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.strokeOval(x - 20, y - 20, 40, 40);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillText(label.toString(), x - 5, y + 5);
    }

    public void drawEdge(GraphicsContext graphicsContext, double x1, double y1, double x2, double y2) {
        if (x1 == x2 && y1 == y2) {
            // Draw self-loop as a circle
            graphicsContext.setStroke(Color.RED);
            graphicsContext.strokeOval(x1 - 15, y1 - 15, 30, 30);
        } else {
            // Draw normal edge
            graphicsContext.setStroke(Color.BLACK);
            graphicsContext.strokeLine(x1, y1, x2, y2);
        }
    }

    public void drawGraph(GraphicsContext graphicsContext) {
        Map<T, double[]> positions = new HashMap<>();
        int vertexCount = graph.getVertices().size();
        double centerX = 400;
        double centerY = 300;
        double radius = 200;
        int i = 0;

        // Assign positions in a circular layout
        for (T vertex : graph.getVertices()) {
            double angle = 2 * Math.PI * i / vertexCount;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);
            positions.put(vertex, new double[]{x, y});
            drawVertex(graphicsContext, x, y, vertex);
            i++;
        }

        // Draw edges
        for (Graph.Pair<T, T> edge : graph.getEdges()) {
            double[] pos1 = positions.get(edge.first);
            double[] pos2 = positions.get(edge.second);
            drawEdge(graphicsContext, pos1[0], pos1[1], pos2[0], pos2[1]);
        }
    }
}
