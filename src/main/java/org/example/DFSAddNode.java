package org.example;

import java.util.Collection;
import java.util.Stack;

public class DFSAddNode implements AddBehavior{

    @Override
    public void addNode(Collection<Node> collection, Node node) {
        if (collection instanceof Stack<Node>){
            ((Stack<Node>)collection).add(node);
        }
        else {
            throw new IllegalArgumentException("Stack not passed into DFS. This should not happen");
        }
    }
}
