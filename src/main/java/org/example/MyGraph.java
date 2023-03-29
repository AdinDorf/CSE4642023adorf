package org.example;
import guru.nidi.graphviz.model.*;

import java.awt.*;
import java.util.*;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.mutNode;

public class MyGraph extends MutableGraph {


    //Factory method pattern for converting subgraphs within MyGraph into MyGraphs
    //This is unnecessary since I'm ignoring subgraphs, but I'm keeping it here anyway because I spent a whole night figuring it out
    //Credit to Chris Feger for the suggestion to make it a factory method pattern to do constructor preprocessing
    private MyGraph(MutableGraph g, LinkedHashSet<MutableGraph> newSubgraphs)
    {
        super(g.isStrict(), g.isDirected(), g.isCluster(), g.name(), (LinkedHashSet<MutableNode>)g.rootNodes(), newSubgraphs, g.links(), g.nodeAttrs(), g.linkAttrs(), g.graphAttrs());
    }

    //
    public static MyGraph buildGraph(MutableGraph g)
    {
        LinkedHashSet<MutableGraph> convertedSubs = new LinkedHashSet<MutableGraph>();
        for (MutableGraph subg : g.graphs()) {
            convertedSubs.add(buildGraph(subg));
        }

        return new MyGraph(g, convertedSubs);
    }


    public void addNode(String label)
    {
        //Check if a node already exists
        if(findNode(label)!= null)
        {
            throw new RuntimeException("Node " + label + " doesn't exist!");
        }

        add(mutNode(label));
    }
    public void addNode(String[] label)
    {
        for (String l : label)
        {
            addNode(l);
        }
    }

    public void removeNode(String label)
    {
        MutableNode n = findNode(label);

        if (n != null)
        {
            nodes.remove(n);
            System.out.println("Successfully removed node: " + n.name());
        }
        else
        {
            //System.out.println("");
            throw new RuntimeException("Node \" + label + \" doesn't exist!");
        }

    }

    public void removeNode(String[] label)
    {
        for (String l : label)
        {
            removeNode(l);
        }
    }

/*
    public MyGraph removeNode(LinkSource source){
        nodes.remove(source);
        return this;
    }

    public MutableGraph removeNode(List<? extends LinkSource> sources) {
        for (final LinkSource source : sources) {
            removeNode(source);
        }
        return this;
    }
*/
    public void addEdge(String srcLabel, String dstLabel){

        MutableNode source = findNode(srcLabel);
        MutableNode dest = findNode(dstLabel);

        if (source == null || dest == null)
        {
            System.out.println("One or both of the input nodes were not found");
            return;
        }

        source.addLink(dest);
       // System.out.println("Edge added between "+ source + " and " + dest);

    }

    public void removeEdge(String srcLabel, String dstLabel)
    {

        MutableNode source = findNode(srcLabel);
        MutableNode dest = findNode(dstLabel);

        if (source == null || dest == null)
        {
            System.out.println("Couldn't find " + srcLabel + " or " + dstLabel);
            return;
        }

        var index = -1;

        for(Link l : source.links()) {
            ///need to get the correct info link to search
            System.out.print(l.from().name() + " to " + l.to().name());
            System.out.print(source.name() + " to " + dest.name());

            if (l.to().name() == dest.name() && l.from().name() == source.name()) {
                index = source.links().indexOf(l);
               // System.out.print("did the thing");
            }

        }

        source.links().remove(index);
        //System.out.println("Edge removed between "+ srcLabel+ " and " + dstLabel);
    }

    public MutableNode findNode(String name)
    {
        boolean found = false;
        for (var i : nodes())
        {
            if(name.equals(i.name().toString())) {
                return i;
            }
        }
        return null;
    }

    public Path GraphSearch(MutableNode src, MutableNode dst)
    {

        //set the current node to source
        MutableNode currentNode = src;

        //Init an empty stack for dfs
        Stack<MutableNode> s =  new Stack<>();

        //Init Dictionary to keep track of visited nodes
        Dictionary<MutableNode, Boolean> visited = new Hashtable<MutableNode, Boolean>();
        Dictionary<MutableNode, MutableNode> parents = new Hashtable<MutableNode, MutableNode>();

        for (var i : nodes())
        {
            visited.put(i, false);
            parents.put(i, currentNode);
        }

        //Now start the BFS algorithm

        //set src to visited
        visited.put(currentNode, true);
        s.add(currentNode);

        while (!s.isEmpty())
        {
            currentNode = s.pop();

            //Check for the destination node
            if (currentNode == dst) {
                //traverse through the parent nodes
                Path p = new Path();
                p.add(currentNode);
                MutableNode parent = parents.get(currentNode);

                while (parent != src) {
                    p.add(parent);
                    parent = parents.get(parent);
                }
                p.add(src);
                return p;
            }

            //for each edge attached to
            for(Link l : currentNode.links())
            {
                //Create a temp node for readability
                LinkTarget lTarget = l.to();
                MutableNode newNode = findNode(lTarget.name().toString());
            /*       if (lTarget instanceof MyGraph)
                {
                    Exception e = new RuntimeException("GraphSearch doesn't support subgraphs, sorrynotsorry");
                    //GraphSearch();
                }*/

                if (newNode instanceof MutableNode)
                {
                    if (!visited.get(newNode)) {
                        visited.put(newNode, true);
                        parents.put(newNode,currentNode);
                        s.add(newNode);
                    }
                }
                else
                {
                    Exception e = new RuntimeException("Node is of an unexpected type");
                }

            }

        }

        return null;
    }


}
