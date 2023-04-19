package org.example;

import java.util.ArrayList;
import java.util.Stack;

public class DFS extends Search{

    //Init an empty queue for BFS
    Stack<Node> s;
    DFS(Node src, Node dst, ArrayList<Node> nodes, ArrayList<Edge> edges) {
        super(src, dst, nodes, edges);
        s = new Stack<>();
    }

    @Override
    Node removeNode() {
        if (s.isEmpty())
        {
            return null;
        }
        else {
            return s.pop();
        }
    }

    @Override
    void inputNode(Node n) {
        s.add(n);
    }

    @Override
    void inputDescendants()
    {
        for (Edge edge : currentNode.descendants)
        {
            Node newNode = edge.to;
            if (!visited.get(newNode)) {
                visited.put(newNode, true);
                inputNode(newNode);
            }
        }
    }
}
