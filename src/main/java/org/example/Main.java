package org.example;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import java.io.*;
import java.util.Scanner;
public class Main {
    public static Graph g = null;
    public static void main(String[] args)  {

        Graph g = new Graph();
        Scanner scan = new Scanner(System.in);
        printMenu();
        String input = scan.nextLine();

        while (!input.equals("exit"))
        {

            switch(input) {
                case "dot":
                    // API for output to file: outputGraph(String filepath)
                    System.out.println("Enter a path: ");
                    input = scan.nextLine();
                    g = parseGraph(input);
                    break;

                case "tostring":
                    g.toString();
                    break;

                case "outputGraph":
                    //outputGraph(file);
                    break;
                case "toPNG":
                    System.out.println("Enter a name for the PNG. It will be saved to ./example/[name].png: ");
                    input = scan.nextLine();
                    exportToPNG(g,input);
                    break;
                case "addNode":
                    System.out.println("Enter a name for the new node");
                    input = scan.nextLine();
                    g.addNode(input);
                    break;
                case "removeNode":
                    System.out.println("Enter which node should be removed");
                    input = scan.nextLine();
                    g.removeNode(input);
                    break;

                case "addEdge":
                    System.out.println("Enter which nodes to connect");
                    input = scan.nextLine();
                    var tempInput = input;
                    input = scan.nextLine();
                    g.addEdge(tempInput, input);
                    System.out.println("Successfully added node: " + input);
                    break;

                case "removeEdge":
                    System.out.println("Enter which nodes to disconnect");
                    input = scan.nextLine();
                    var t = input;
                    input = scan.nextLine();
                    g.removeEdge(t, input);
                    break;

                  case "graphSearch":
                    System.out.println("Enter the source node name");
                    input = scan.nextLine();
                    var temp = input;
                    System.out.println("Enter the destination node name");
                    input = scan.nextLine();
                    System.out.println("Enter the search algorithm to use (bfs, dfs, rand)");
                    var alg = scan.nextLine();

                    GraphSearch(temp, input, alg);
                    break;


            }

            printMenu();
            input = scan.nextLine();
        }
    }
    public static void printMenu()
    {

        System.out.println(
                """
                        \n**********************************************************
                        Please enter one of the following commands
                        dot: parses a dot graph given a path
                        tostring: Outputs a dot graph if there is one currently in memory
                        toPNG: Exports the current DOT graph to a PNG
                        addNode: add a node by name
                        addNodes: add a list of nodes by name, delimited by commas
                        removeNode: remove a node by name
                        removeNodes: remove a list of nodes by name, delimited by commas
                        addEdge: add an edge between two nodes
                        removeEdge: remove an edge between two nodes
                        graphSearch: return a path between two nodes
                        """
        );
    }

    public static Graph parseGraph(String inputPath)
    {
        // Feature 1: Parse a DOT graph file to create a graph (20 points)
        // Accept a DOT graph file and create a directed graph object (define your own
        // graph class or use the graph class in your chosen libraries)
        // API: parseGraph(String filepath)

        //TODO: Get rid of MyGraph entirely and use MutableGraph. This will be refactor 2 or 3 (however I decide to break this up)
        MyGraph graphToConvert;
        try {
            InputStream dot = new FileInputStream(inputPath);
            graphToConvert = MyGraph.buildGraph(new Parser().read(dot));

            //Thanks Java garbage man!
            g = new Graph(graphToConvert);

        }
        catch (Exception e)
        {
            System.out.println("Failed to read input graph");
            e.getStackTrace();
            e.printStackTrace();
            return null;
        }

        return g;
    }


    public static void exportToPNG(Graph graph, String name) {
        try {
            MutableGraph g = graph.toMutableGraph();
            System.out.println("Exported graph " + name + " to png: ");
            Graphviz.fromGraph(g).width(700).render(Format.PNG).toFile(new File("example/"+name+".png"));
            g = null;
        }
        catch (Exception e)
        {
            System.out.println("Failed to convert graph object to PNG");
           // e.printStackTrace();
        }
    }




    public static void GraphSearch(String src, String dst, String alg)
    {
        Path p = new Path();
        Search search;
        if (alg.equals("bfs")) {
            search = new Search(g.findNode(src), g.findNode(dst), g.nodes, g.edges, new BFSAddNode(), new BFSRemoveNode(), new BFSWalk(), new BFSInit());
        } else if (alg.equals("dfs")) {
            search = new Search(g.findNode(src), g.findNode(dst), g.nodes, g.edges, new DFSAddNode(), new DFSRemoveNode(), new DFSWalk(), new DFSInit());
        } else if (alg.equals("rand")){
            search = new Search(g.findNode(src), g.findNode(dst), g.nodes, g.edges, new RandWalkAddNode(), new RandWalkRemoveNode(), new RandWalkWalk(), new RandWalkInit());
        }
        else{
            System.out.println("Please enter a valid algorithm (bfs or dfs)");
            return;
        }

        p = g.GraphSearch(search);
        System.out.println("Path determined by " + alg + ": " + p.toString());
        System.out.println("Order of traversal: " + p.returnTraversal());
    }
}