package org.example;

import java.util.ArrayList;

public class Node {
    public String label;
    public ArrayList<Edge> descendents;
    public Edge parent;


    public Node(String label)
    {
        this(label, new ArrayList<Edge>(), null );
    }


    public Node(String label, ArrayList<Edge> edges, Edge parent) {
        this.label = label;
        this.descendents = edges;
        this.parent = parent;
    }

    @Override
    public String toString()
    {
        return label;
    }



}
