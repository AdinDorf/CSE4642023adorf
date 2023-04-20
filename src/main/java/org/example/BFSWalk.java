package org.example;

import java.util.Collection;
import java.util.Dictionary;

public class BFSWalk implements WalkBehavior{
    @Override
    public void walk(Collection<Node> collection, Dictionary<Node, Boolean> visited, Node currentNode, AddBehavior addBehavior) {
        for (Edge edge : currentNode.descendants)
        {
            Node newNode = edge.to;
            if (!visited.get(newNode)) {
                visited.put(newNode, true);
                addBehavior.addNode(collection, newNode);
            }
        }
    }
}
