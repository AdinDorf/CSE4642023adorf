package org.example;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;

public interface WalkBehavior {
    abstract void walk(Collection<Node> collection, Dictionary<Node, Boolean> visited, Node currentNode, AddBehavior addBehavior);
}
