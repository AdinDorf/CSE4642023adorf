package org.example;

public class Edge {
    public String label;
    public Node from;
    public Node to;

    public Edge(Node from, Node to)
    {
        this.from = from;
        this.to = to;
        this.label = from.toString() + " -> " + to.toString();
    }


}
