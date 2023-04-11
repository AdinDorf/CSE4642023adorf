package org.example;

import java.util.ArrayList;
import java.util.ListIterator;

public class Graph {
    public ArrayList<Node> nodes;
    public ArrayList<Edge> edges;
    private int size;


    public Graph()
    {
        this(new ArrayList<Node>(), new ArrayList<Edge>());
    }

    public Graph(ArrayList<Node> nodes, ArrayList<Edge> edges)
    {
        this.nodes = nodes;
        this.edges = edges;
    }

    public void addNode(String name)
    {
        nodes.add(new Node(name));
    }

    public void removeNode(String name)
    {
        ListIterator<Node> listIterator = nodes.listIterator();

        while(listIterator.hasNext())
        {
            if(listIterator.next().label == name)
            {
                listIterator.remove();
            }
        }
    }

    public void addEdge(String source, String dest)  {
        edges.add(new Edge(findNode(source), findNode((dest))));
    }

    public void removeEdge(String source, String dest) {
        for (Edge e : edges) {
            //Check if there is a matching edge
            if (e.from.label.equals(source) && e.to.label.equals(dest))
            {
                System.out.println("Found a matching edge!");
                edges.remove(e);
                return;
            }
        }
        throw new EdgeNotFoundException("Edge from " + source + " to " + dest + " not found!");
    }


    private Node findNode(String label)
    {
        ListIterator<Node> listIterator = nodes.listIterator();
        while(listIterator.hasNext())
        {
            var next = listIterator.next();
            System.out.println(next.label);
            if(next.label.equals(label))
            {
                return next;
            }
        }
        throw new NodeNotFoundException("Node " + label + " not found!");
    }


    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();

        s.append("Number of Nodes: ").append(nodes.size()).append("\n");
        for (Node n : nodes)
        {
            s.append(n.label).append("\n");
        }
        s.append("Number of Edges: ").append(edges.size()).append("\n");
        for (Edge e : edges)
        {
            s.append(e.label).append("\n");
        }
        System.out.println("Graph: \n" + s);
        return s.toString();
    }





}
