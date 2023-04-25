package org.example;

public class Edge {
    public String label;
    public Node from;
    public Node to;


    //Assumes no convergent nodes
    public Edge(Node from, Node to)
    {
        this.from = from;
        this.to = to;
        this.label = from.toString() + " -> " + to.toString();
        this.to.parent = this;
        this.from.descendants.add(this);
    }


}
