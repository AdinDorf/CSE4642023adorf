package org.example;
import java.util.ArrayList;

public class Node {
    public String label;
    public ArrayList<Edge> descendants;
    public Edge parent;


    public Node(String label)
    {
        this(label, new ArrayList<Edge>(), null );
    }


    public Node(String label, ArrayList<Edge> edges, Edge parent) {
        this.label = label;
        this.descendants = edges;
        this.parent = parent;
    }

    public void addEdge(Edge e)
    {
        descendants.add(e);
    }


    @Override
    public String toString()
    {
        return label;
    }



}
