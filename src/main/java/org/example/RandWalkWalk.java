package org.example;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Random;

public class RandWalkWalk implements WalkBehavior{
    Random rand = new Random();
    @Override
    public void walk(Collection<Node> collection, Dictionary<Node, Boolean> visited, Node currentNode, AddBehavior addBehavior) {
        if (!currentNode.descendants.isEmpty()) {
            int randInt = rand.nextInt(currentNode.descendants.size());
            Node newNode = currentNode.descendants.get(randInt).to;

            visited.put(newNode, true);
            addBehavior.addNode(collection,newNode);
        }
        else {
            currentNode = null;
        }
    }
}
