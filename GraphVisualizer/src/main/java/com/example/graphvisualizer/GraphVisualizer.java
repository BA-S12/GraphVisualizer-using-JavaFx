package com.example.graphvisualizer;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class GraphVisualizer extends Application {
    private final Graph<String> graph = new Graph<>();

    @Override
    public void start(Stage primaryStage) {
//
        System.out.println("Example 1");
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("F");
        graph.addVertex("R");

        graph.addEdge("A", "B");
        graph.addEdge("A", "D");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");
        graph.addEdge("A", "F");
        graph.addEdge("D", "R");


//        System.out.println("Example 2");
//        Random random = new Random();
//
//        // Add vertices for all letters from 'A' to 'Z'
//        for (char c = 'A'; c <= 'Z'; c++) {
//            graph.addVertex(String.valueOf(c));
//        }
//
//        // Create a list of all vertices
//        List<String> vertices = new ArrayList<>(graph.getVertices());
//
//        // Add random edges between the vertices
//        int numEdges = 50; // Specify the number of random edges to add
//        for (int i = 0; i < numEdges; i++) {
//            String vertex1 = vertices.get(random.nextInt(vertices.size()));
//            String vertex2 = vertices.get(random.nextInt(vertices.size()));
//            if (!vertex1.equals(vertex2)) { // Ensure no self-loop
//                graph.addEdge(vertex1, vertex2);
//            }
//        }


        // Create the canvas and graphics context
        // Create the canvas and graphics context
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        // Draw the graph using GraphDrawer
        GraphDrawer<String> graphDrawer = new GraphDrawer<>(graph);
        graphDrawer.drawGraph(graphicsContext);

        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 800, 600);

        Image icon = new Image("graph.png");

        primaryStage.setTitle("Graph Visualization");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(icon);
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}

