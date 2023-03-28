package org.example;
import guru.nidi.graphviz.model.*;

import java.awt.*;
import java.util.*;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.mutNode;

public class MyGraph extends MutableGraph {


    //Factory method pattern for converting subgraphs within MyGraph into MyGraphs
    //Credit to Chris Feger for this monstrosity
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

    public void GraphSearch(MutableNode src, MutableNode dst)
    {

        //set the current node to source
        MutableNode current = src;

        //Init an empty queue for BFS
        Queue<MutableNode> q =  new LinkedList<>();

        //Init Dictionary to keep track of visited nodes
        Dictionary<MutableNode, Boolean> visited = new Hashtable<MutableNode, Boolean>();
        for (var i : nodes())
        {
            visited.put(i, false);
        }

        //Now start the BFS algorithm

        //set src to visited
        visited.put(current, true);
        q.add(current);

        while (!q.isEmpty())
        {
            current = q.remove();

            //Check for the destination node
            if (current == dst) {
                //traverse through the parent nodes

            }

            //for each edge attached to
            for(Link l : current.links())
            {
                //Create a temp node for readability
                var w = l.to();


                if (w instanceof MutableNode)
                {
                    System.out.println("Node!");
                    visited.put((MutableNode)w, true);
                    q.add((MutableNode) w);
                }
                else if (w instanceof MyGraph)
                {

                    //GraphSearch();
                }
                /*
                if (visited.get(w) != true)
                {
                    visited.put((MutableNode) w, true);
                    //set the parent of w
                    q.add((MutableNode)w );
                }*/

            }

        }

    }
/*
    private void NormalizeGraph (MutableGraph graph)
    {
        for (Link l : graph.links())
        {
            if (l.to() instanceof MutableGraph)
            {
                NormalizeGraph((MutableGraph)l.to());
            }
            else
            {

            }
        }


    }*/


}
