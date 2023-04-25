package org.example;
import java.util.ArrayList;

public class Path {

    private final ArrayList<Node> shortestPath = new ArrayList<>();
    private final ArrayList<Node> pathTraversed = new ArrayList<>();

    public Path()
    {

    }

    public void add(Node n)
    {
        shortestPath.add(0,n);
    }

    public void logPath(Node n)
    {
        pathTraversed.add(n);
    }


    public String returnTraversal()
    {
        String s = "";
        for (Node l : pathTraversed) {
            s += l.label;
            s+= " -> ";
        }
        s = s.substring(0, s.length()-3);
        return s;
    }


    @Override
    public String toString() {
        String s = "";
        for (Node l : shortestPath) {
            s += l.label;
            s+= " -> ";
        }
        s = s.substring(0, s.length()-3);
        return s;
    }
}
