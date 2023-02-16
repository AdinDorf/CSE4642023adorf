package org.example;

public class Main {
    public static void main(String[] args)
    {
        System.out.print("The first arg is: " + args[1]);
        switch(args[0]) {
            case "dot":

                //TODO: Feature 1: Parse a DOT graph file to create a graph (20 points)
                // Accept a DOT graph file and create a directed graph object (define your own
                // graph class or use the graph class in your chosen libraries)
                // API: parseGraph(String filepath)
                // Output the number of nodes, the label of the nodes, the number of edges, the
                // nodes and the edge direction of edges (e.g., a -> b)
                // API for printing a graph: toString()
                // API for output to file: outputGraph(String filepath)



                break;

        }

        System.out.println("Hello world!");
    }


    public static void parseGraph(String content)
    {
        Graph g = graph("newGraph").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Font.name("arial"))
                .linkAttr().with("class", "link-class");


    }

}