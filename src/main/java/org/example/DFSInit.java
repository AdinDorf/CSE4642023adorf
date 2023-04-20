package org.example;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFSInit implements InitBehavior{
    @Override
    public Collection<Node> initData() {
        Stack<Node> s = new Stack<>();
        return s;
    }
}
