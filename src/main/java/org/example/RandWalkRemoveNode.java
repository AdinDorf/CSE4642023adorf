package org.example;

import java.util.Collection;
import java.util.Queue;

public class RandWalkRemoveNode implements RemoveBehavior{

    @Override
    public Node removeNode(Collection<Node> collection) {
        if(collection instanceof Queue<Node>)
        {
            if(collection.isEmpty())
            {
                return null;
            }
            else{
                return ((Queue<Node>)collection).remove();
            }
        }
        throw new IllegalArgumentException();
    }
}
