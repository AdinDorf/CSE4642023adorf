package org.example;
import guru.nidi.graphviz.model.*;

import java.awt.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
public class MyGraph extends MutableGraph {

    public MyGraph(MutableGraph g)
    {
        super(g.isStrict(), g.isDirected(), g.isCluster(), g.name(), (LinkedHashSet<MutableNode>)g.rootNodes(), (LinkedHashSet<MutableGraph>)g.graphs(), g.links(), g.nodeAttrs(), g.linkAttrs(), g.graphAttrs());
    }

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

    public MyGraph addEdge(MutableNode source, MutableNode dest){
        System.out.print(source.links());
        source = source.addLink(dest);

        //var i = source.links().indexOf(dest.asLinkTarget());
        //source.links().get(i).add(Color.red);
        //System.out.print(source.links());
        return this;
    }

    public void removeEdge(MutableNode source, MutableNode dest)
    {


        var index = -1;

        for(Link l : source.links())
        {
            ///need to get the correct info link to search
            System.out.print(l.from().name() + " to " + l.to().name());
            System.out.print(source.name() + " to " + dest.name());

            if (l.to().name() == dest.name() && l.from().name() == source.name())
            {
                index = source.links().indexOf(l);
                System.out.print("did the thing");
            }

        }


        source.links().remove(index );

    }



}
