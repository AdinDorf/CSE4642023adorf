package org.example;
import guru.nidi.graphviz.model.*;

import java.awt.*;
import java.util.LinkedHashSet;
import java.util.List;

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

        var i = source.links().indexOf(dest.asLinkTarget());
        //source.links().get(i).add(Color.red);
        System.out.print(source.links());
        return this;
    }

    public void removeEdge(LinkSource source, LinkTarget dest)
    {

    }



}
