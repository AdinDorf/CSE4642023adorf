package org.example;

import guru.nidi.graphviz.model.MutableNode;

import java.util.ArrayList;
import java.util.LinkedList;

public class Path {

    private ArrayList<Node> p = new ArrayList<>();

    public Path()
    {    }

    public void add(Node n)
    {
        p.add(n);
    }

    @Override
    public String toString() {
        String s = new String();
        for (Node l : p) {
            s += l.label;
            s+= " -> ";
        }
        s = s.substring(0, s.length()-3);
        return s;
    }
}
