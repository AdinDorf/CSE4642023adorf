package org.example;

import guru.nidi.graphviz.model.MutableNode;

import java.util.ArrayList;
import java.util.LinkedList;

public class Path {

    private ArrayList<MutableNode> p = new ArrayList<MutableNode>();

    public Path()
    {

    }

    public void add(MutableNode n)
    {
        p.add(n);
    }

    @Override
    public String toString() {
        String s = new String();
        for (MutableNode l : p) {
            s += l.name().toString();
            s+= " -> ";
        }
        s = s.substring(0, s.length()-3);
        return s;
    }
}
