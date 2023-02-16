package org.example;

import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;

public class Main {
    public static void main(String[] args)
    {
        System.out.print("The first arg is: " + args[0]);
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
                String path = args[1];
                parseGraph(path);
                break;

        }

        System.out.println("Hello world!");
    }


    public static void parseGraph(String inputPath)
    {

        try {
            InputStream dot = new FileInputStream(inputPath);

            MutableGraph g = new Parser().read(dot);

            Graphviz.fromGraph(g).width(700).render(Format.PNG).toFile(new File("example/ex2.png"));
        }
        catch (Exception e)
        {
            System.out.println("Failed to read input graph");
            e.getStackTrace();
        }

    }

}