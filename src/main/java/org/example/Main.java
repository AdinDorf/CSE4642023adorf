package org.example;

import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;

import javax.swing.plaf.multi.MultiTabbedPaneUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;


import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;

public class Main {
    public static void main(String[] args)
    {
        MutableGraph g = null;
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

                    //TODO: Output the number of nodes, the label of the nodes, the number of edges, the
                    // nodes and the edge direction of edges (e.g., a -> b)
                    // API for printing a graph: toString88()
                    // API for output to file: outputGraph(String filepath)
                    System.out.println("Enter a path: \n");
                    String path = scan.nextLine();
                    g = parseGraph(path);
                    break;


                case "tostring":
                    System.out.println("Graph ToString: \n" + g.toString());
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
                        "tostring: Outputs a dot graph if there is one currently in memory\n"
        );
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

            return null;
        }
    }

    public static void printToPNG(Graph g, String name) {
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



}