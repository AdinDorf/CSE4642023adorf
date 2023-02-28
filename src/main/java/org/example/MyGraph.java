package org.example;

import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.model.*;

import javax.annotation.Nullable;
import java.util.LinkedHashSet;
import java.util.List;

public class MyGraph extends MutableGraph {

    public MyGraph(MutableGraph g)
    {
        super(g.isStrict(), g.isDirected(), g.isCluster(), g.name(), (LinkedHashSet<MutableNode>)g.rootNodes(), (LinkedHashSet<MutableGraph>)g.graphs(), g.links(), g.nodeAttrs(), g.linkAttrs(), g.graphAttrs());
    }
}
