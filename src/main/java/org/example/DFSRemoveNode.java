package org.example;

import java.util.Collection;
import java.util.Queue;
import java.util.Stack;

public class DFSRemoveNode implements RemoveBehavior{

    @Override
    public Node removeNode(Collection<Node> collection) {
        if(collection instanceof Stack<Node>)
        {
            if(collection.isEmpty())
            {
                return null;
            }
            else{
                return ((Stack<Node>)collection).pop();
            }
        }
        throw new IllegalArgumentException();
    }
}
