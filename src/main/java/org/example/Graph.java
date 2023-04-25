package org.example;
import guru.nidi.graphviz.model.*;
import java.util.*;
import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

public class Graph {
    public ArrayList<Node> nodes;
    public ArrayList<Edge> edges;

    public Graph()
    {
        this(new ArrayList<>(), new ArrayList<>());
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
        for (MutableNode mnode : g.nodes()) {
            for (Edge e : edges) {
                if (mnode.name().toString().equals(e.from.label)) {
                    mnode.addLink(e.to.label);
                }
            }
        }

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

    public void addNodes(String[] names)
    {
        for (String name: names) {
            addNode(name);
        }
    }

    public void removeNode(String name)
    {
        edges.removeIf(edge -> edge.from.label.equals(name));
        edges.removeIf(edge -> edge.to.label.equals(name));
        nodes.removeIf(node -> node.label.equals(name));
    }

    public void removeNodes(String[] names)
    {
        for (String name : names) {
            removeNode(name);
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

    public Path GraphSearch(String src, String dst, Main.Algorithm alg)
    {
        Search search;
        if (alg == Main.Algorithm.BFS) {
            search = new BFS(findNode(src), findNode(dst), nodes, edges);
        } else if ((alg == Main.Algorithm.DFS)) {
            search = new DFS(findNode(src), findNode(dst), nodes, edges);
        } else if ((alg == Main.Algorithm.RAND)){
            search = new RWS(findNode(src), findNode(dst), nodes, edges);
        }
        else{
            System.out.println("Something went horribly wrong, this should never be reached.");
            return null;
        }
        return search.runSearch();
    }

    Node findNode(String label)
    {
        for (Node next : nodes) {
            //System.out.println(next.label);
            if (next.label.equals(label)) {
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
