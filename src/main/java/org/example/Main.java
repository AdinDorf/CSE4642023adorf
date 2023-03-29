package org.example;


import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;

import guru.nidi.graphviz.model.MutableNode;
import guru.nidi.graphviz.parse.Parser;
import java.io.*;
import java.util.Scanner;
import static guru.nidi.graphviz.model.Factory.*;

public class Main {
    public static MyGraph g = null;
    public static void main(String[] args)
    {

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
                    graphToString(g);
                    break;

                case "tostring":
                    graphToString(g);
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
                case "bfs":
                    System.out.println("Enter the source and dest node names");
                    input = scan.nextLine();
                    var temp = input;
                    input = scan.nextLine();
                    bfs(temp, input);
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
                        """
        );
    }

    public static void graphToString(MyGraph g)
    {
        //TODO: Output the number of nodes, the label of the nodes, the number of edges, the
        // nodes and the edge direction of edges (e.g., a -> b)
        // API for printing a graph: toString()
        StringBuilder s = new StringBuilder();

        s.append("Number of Nodes: ").append(g.nodes().size()).append("\n");
        s.append("Node Labels: ").append(g.nodes()).append("\n");
        s.append("Number of Edges: ").append(g.edges().size()).append("\n");
        for (var i : g.edges())
        {
            s.append(i.name()).append("\n");
        }
        System.out.println("Graph: \n" + s);
    }
    public static MyGraph parseGraph(String inputPath)
    {
        //TODO: Feature 1: Parse a DOT graph file to create a graph (20 points)
        // Accept a DOT graph file and create a directed graph object (define your own
        // graph class or use the graph class in your chosen libraries)
        // API: parseGraph(String filepath)

        try {
            InputStream dot = new FileInputStream(inputPath);
            return MyGraph.buildGraph(new Parser().read(dot));
        }
        catch (Exception e)
        {
            System.out.println("Failed to read input graph");
            e.getStackTrace();
            e.printStackTrace();
            return null;
        }
    }

    public static void exportToPNG(MyGraph g, String name) {
        try {
            System.out.println("Exported graph " + name + " to png: ");
            Graphviz.fromGraph(g).width(700).render(Format.PNG).toFile(new File("example/"+name+".png"));
        }
        catch (Exception e)
        {
            System.out.println("Failed to convert graph object to PNG");
           // e.printStackTrace();
        }
    }

    public static void bfs(String src, String dst)
    {
        Path p = new Path();
        p = g.GraphSearch(g.findNode(src), g.findNode(dst));
        System.out.print(p.toString());
    }


    /*
        public static void outputGraph(String filepath)
        {
            //output the contents of g into a text file
            try {
                FileWriter fw = new FileWriter(filepath);
                fw.write("hello");
                fw.close();
                System.out.println("Printed toString to file at: " + filepath);
            }
            catch (IOException e) {
                System.out.println("Error writing to file");
                e.printStackTrace();
            }
        }
    */




}