package org.example;
import guru.nidi.graphviz.model.*;

import java.awt.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;

import static guru.nidi.graphviz.model.Factory.mutNode;

public class MyGraph extends MutableGraph {

    public MyGraph(MutableGraph g)
    {
        super(g.isStrict(), g.isDirected(), g.isCluster(), g.name(), (LinkedHashSet<MutableNode>)g.rootNodes(), (LinkedHashSet<MutableGraph>)g.graphs(), g.links(), g.nodeAttrs(), g.linkAttrs(), g.graphAttrs());
    }


    public void addNode(String label)
    {
        //Check if a node already exists
        if(findNode(label)!= null)
        {
            System.out.println("Node " + label + " doesn't exist!");
            return;
        }

        add(mutNode(label));
    }


    public void removeNode(String label)
    {
        MutableNode n = findNode(label);
        if (n != null)
        {
            nodes.remove(n);
            System.out.println("Successfully removed node: " + name);
        }
        else
        {
            System.out.println("Node " + name + " doesn't exist!");
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

        source = source.addLink(dest);
        System.out.println("Edge added between "+ source + " and " + dest);

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
                System.out.print("did the thing");
            }



        }

        source.links().remove(index);
        System.out.println("Edge removed between "+ srcLabel+ " and " + dstLabel);
    }

    private MutableNode findNode(String name)
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



}
