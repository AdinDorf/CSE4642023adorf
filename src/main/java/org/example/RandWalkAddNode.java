package org.example;

import java.util.Collection;
import java.util.Queue;

public class RandWalkAddNode implements AddBehavior{

    @Override
    public void addNode(Collection<Node> collection, Node node) {
        if (collection instanceof Queue<Node>){

            ((Queue<Node>)collection).add(node);
        }
        else {
            throw new IllegalArgumentException("Queue not passed into BFS. This should not happen");
        }
    }
}
