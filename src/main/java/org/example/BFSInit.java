package org.example;

import java.util.Collection;
import java.util.Dictionary;
import java.util.LinkedList;
import java.util.Queue;

public class BFSInit implements InitBehavior{
    @Override
    public Collection<Node> initData() {
        Queue<Node> q = new LinkedList<>();
        return q;
    }
}
