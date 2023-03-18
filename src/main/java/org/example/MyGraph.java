package org.example;
import guru.nidi.graphviz.model.*;
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

    public MyGraph addEdge(MutableNode source, LinkTarget dest){
        //source = source.linkTo(dest);
        source = source.addLink(dest);
        return this;
    }

    public void removeEdge(LinkSource source, LinkTarget dest)
    {

    }



}
