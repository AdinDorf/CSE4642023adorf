package org.example;
import guru.nidi.graphviz.model.*;
import java.util.LinkedHashSet;

public class MyGraph extends MutableGraph {

    public MyGraph(MutableGraph g)
    {
        super(g.isStrict(), g.isDirected(), g.isCluster(), g.name(), (LinkedHashSet<MutableNode>)g.rootNodes(), (LinkedHashSet<MutableGraph>)g.graphs(), g.links(), g.nodeAttrs(), g.linkAttrs(), g.graphAttrs());
    }

    public MyGraph remove(LinkSource source){
        nodes.remove(source);
        return this;
    }


}
