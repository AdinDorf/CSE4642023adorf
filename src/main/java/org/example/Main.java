package org.example;


import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
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
                    addNode(g,input);
                    break;
                case "removeNode":
                    System.out.println("Enter which node should be removed");
                    input = scan.nextLine();
                    removeNode(g,input);
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
                        **********************************************************
                        Please enter one of the following commands
                        dot: parses a dot graph given a path
                        tostring: Outputs a dot graph if there is one currently in memory
                        toPNG: Exports the current DOT graph to a PNG
                        addNode: add a node by name
                        addNodes: add a list of nodes by name, delimited by commas
                        removeNode: remove a node by name
                        removeNodes: remove a list of nodes by name, delimited by commas
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
            return new MyGraph(new Parser().read(dot));
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
            e.printStackTrace();
        }
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
    public static void addNode(MyGraph g, String name)
    {

        String[] nameList;
        nameList = name.split(",");

        for (var st : nameList)
        {
            st = st.strip();

            //Check if a node already exists
            for (var i : g.nodes())
            {
                if(name.equals(i.name().toString()))
                {
                    System.out.println("Node " + name + " already exists!");

                }
            }
        }


        System.out.println("Successfully added node: " + name);
        g.add(mutNode(name));
    }

    public static void removeNode(MyGraph g, String name)
    {
        MutableNode n = findNode(name);
        if (n != null)
        {
            g.remove(n);
            System.out.println("Successfully removed node: " + name);
        }
        else
        {
            System.out.println("Node " + name + " doesn't exist!");
        }
    }

    /*Add an edge and check of duplicate edges: addEdge(String srcLabel,
String dstLabel)
• Remove an edge: removeEdge(String srcLabel, String dstLabel)*/
    public static void addEdge(String srcLabel, String dstLabel)
    {
        MutableNode srcNode = findNode(srcLabel);
        MutableNode dstNode = findNode(dstLabel);

        if (srcNode == null || dstNode == null)
        {
            System.out.println("One or both of the input nodes were not found");
            return;
        }

        g.addLink(srcNode, dstNode);
        System.out.println("Edge added between "+ srcLabel+ " and " + dstLabel);
    }


    private static MutableNode findNode(String name)
    {
        boolean found = false;
        for (var i : g.nodes())
        {
            if(name.equals(i.name().toString())) {
                return i;
            }
        }
        if(!found)
        {
            System.out.println("Node " + name + " doesn't exist!");
        }
        return null;
    }

}