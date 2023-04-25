package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RWS extends Search{

    //Init an empty queue for BFS
    Queue<Node> q;
    RandWalkRemoveNode removeNodeBehavior = new RandWalkRemoveNode();
    RandWalkWalk walkBehavior = new RandWalkWalk();
    RandWalkAddNode addBehavior = new RandWalkAddNode();

    RWS(Node src, Node dst, ArrayList<Node> nodes, ArrayList<Edge> edges) {
        super(src, dst, nodes, edges);
        q = new LinkedList<>();
    }

    @Override
    Node removeNode() {
        return removeNodeBehavior.removeNode(q);
    }

    @Override
    void inputNode(Node n) {
        addBehavior.addNode(q,n);
    }

    @Override
    void walk() {
        walkBehavior.walk(q, visited, currentNode, addBehavior);
    }
}