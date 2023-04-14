package org.example;

import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.LinkSource;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ListIterator;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

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

    //Constructor to create a Graph from a MutableGraph (albeit a bit sloppily)
    public Graph(MutableGraph graphToConvert) {
        //Snag all the Nodes, Edges, etc. from the MutableGraph and then tell it to (politely) fuck off <3

        //Since my edges are reliant on nodes for their existence, parse all nodes first,
        ArrayList<Node> nodeList = new ArrayList<>();
        for (MutableNode n : graphToConvert.nodes())
        {
            nodeList.add(new Node(n.name().toString()));
        }

        this.nodes = nodeList;

        //Transform n.links() into an arraylist of Edges
        ArrayList<Edge> edgeList = new ArrayList<>();

        initEdges(graphToConvert, edgeList);

        //I think this should work?

        this.edges = edgeList;
    }

    public MutableGraph toMutableGraph()
    {
        MutableGraph g = mutGraph("example1").setDirected(true);

        for (Node n : nodes) {
            g.add(mutNode(n.label));
        }

        System.out.println(g.toString());
        for (MutableNode mnode : g.nodes()) {
            for (Edge e : edges) {
                if (mnode.name().toString().equals(e.from.label)) {
                    mnode.addLink(e.to.label);
                }
            }
        }
        System.out.println(g.toString());

        return g;
    }
    private void initEdges(MutableGraph convertGraph, ArrayList<Edge> edgeList)
    {
        for (Link link : convertGraph.links())
        {
            Node from = findNode(link.from().name().toString());
            Node to = findNode(link.to().name().toString());

            Edge newEdge = new Edge(from, to);
            edgeList.add(newEdge);
            from.addEdge(newEdge);
            to.parent = newEdge;
        }

        for (MutableNode node : convertGraph.nodes())
        {
            initEdges(node, edgeList);
        }
    }

    private void initEdges(MutableNode mutableNode, ArrayList<Edge> edgeList)
    {
        for (Link link: mutableNode.links()) {
            Node from = findNode(link.from().name().toString());
            Node to = findNode(link.to().name().toString());

            Edge newEdge = new Edge(from, to);
            edgeList.add(newEdge);
            from.addEdge(newEdge);
            to.parent = newEdge;
        }
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
        try {
            ListIterator<Node> listIterator = nodes.listIterator();
            while (listIterator.hasNext()) {
                var next = listIterator.next();
                //System.out.println(next.label);
                if (next.label.equals(label)) {
                    return next;
                }
            }
        }
        catch(Exception e){

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
