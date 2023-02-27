package org.example;

import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import guru.nidi.graphviz.model.Node;
import guru.nidi.graphviz.parse.Parser;

import javax.swing.plaf.multi.MultiTabbedPaneUI;
import java.io.*;
import java.util.Collection;
import java.util.Scanner;


import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.*;

public class Main {
    public static MutableGraph g = null;
    public static void main(String[] args)
    {

        Scanner scan = new Scanner(System.in);
        printMenu();
        String input = input = scan.nextLine();

        while (input != "exit")
        {
            if (input == "exit")
            {
                return;
            }

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
                "\n**********************************************************\n" +
                        "Please enter one of the following commands\n" +
                        "dot: parses a dot graph given a path\n" +
                        "tostring: Outputs a dot graph if there is one currently in memory\n" +
                        "toPNG: Exports the current DOT graph to a PNG\n" +
                        "addNode: adds a node \n"
        );
    }

    public static void graphToString(MutableGraph g)
    {
        //TODO: Output the number of nodes, the label of the nodes, the number of edges, the
        // nodes and the edge direction of edges (e.g., a -> b)
        // API for printing a graph: toString()
        String s = "";

        s += "Number of Nodes: "+ g.nodes().size() + "\n";
        s += "Node Labels: "+ g.nodes().toString() + "\n";
        s += "Number of Edges: "+ g.edges().size() + "\n";
        for (var i : g.edges())
        {
            s += i.name() + "\n";
        }

        System.out.println("Graph: \n" + s);
    }
    public static MutableGraph parseGraph(String inputPath)
    {
        //TODO: Feature 1: Parse a DOT graph file to create a graph (20 points)
        // Accept a DOT graph file and create a directed graph object (define your own
        // graph class or use the graph class in your chosen libraries)
        // API: parseGraph(String filepath)

        try {
            InputStream dot = new FileInputStream(inputPath);
            MutableGraph g = new Parser().read(dot);
            return g;
        }
        catch (Exception e)
        {
            System.out.println("Failed to read input graph");
            e.getStackTrace();
            e.printStackTrace();
            return null;
        }
    }

    public static void exportToPNG(MutableGraph g, String name) {
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

    public static void addNode(MutableGraph g, String name)
    {
        for (var i : g.nodes())
        {
            if(name.equals(i.name().toString()))
            {
                System.out.println("Node " + name + " already exists!");
                return;
            }
        }
        System.out.println("Successfully added node: " + name);
        g = g.add(mutNode(name));


    }

    public static void removeNode(MutableGraph g, String name)
    {
        boolean found = false;
        for (var i : g.nodes())
        {
            if(name.equals(i.name().toString())) {
                System.out.println("Before: " + g.nodes().toString());
                mutGraph(g.name().toString()).nodes().remove(i);

                System.out.println("After: " + g.nodes().toString());
                found = true;
                System.out.println("Successfully removed node: " + name);
            }
        }
        if(found == false)
        {
            System.out.println("Node " + name + " doesn't exist!");
        }
    }

}