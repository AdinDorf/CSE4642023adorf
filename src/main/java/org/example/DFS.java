package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFS extends Search{

    //Init an empty queue for DFS
    Stack<Node> nodeStack;
    DFSRemoveNode removeNodeBehavior = new DFSRemoveNode();
    DFSWalk walkBehavior = new DFSWalk();
    DFSAddNode addBehavior = new DFSAddNode();

    DFS(Node src, Node dst, ArrayList<Node> nodes, ArrayList<Edge> edges) {
        super(src, dst, nodes, edges);
        nodeStack = new Stack<>();
    }

    @Override
    Node removeNode() {
        return removeNodeBehavior.removeNode(nodeStack);
    }

    @Override
    void inputNode(Node n) {
        addBehavior.addNode(nodeStack,n);
    }

    @Override
    void walk() {
        walkBehavior.walk(nodeStack, visited, currentNode, addBehavior);
    }
}