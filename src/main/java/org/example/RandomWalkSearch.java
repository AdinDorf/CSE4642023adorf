package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class RandomWalkSearch extends Search{

    //Init an empty queue for BFS
    Queue<Node> q;
    static Random rand = new Random();
    RandomWalkSearch(Node src, Node dst, ArrayList<Node> nodes, ArrayList<Edge> edges) {
        super(src, dst, nodes, edges);
        q = new LinkedList<>();
    }

    @Override
    Node removeNode() {
        if (q.isEmpty())
        {
            return null;
        }
        else {
            return q.remove();
        }
    }

    @Override
    void inputNode(Node n) {
        q.add(n);
    }

    @Override
    void walk()
    {
        /*
        for (Edge edge : currentNode.descendants)
        {
            Node newNode = edge.to;
            if (!visited.get(newNode)) {
                visited.put(newNode, true);
                inputNode(newNode);
            }
        }*/

        if (!currentNode.descendants.isEmpty()) {
            int randInt = rand.nextInt(currentNode.descendants.size());
            Node newNode = currentNode.descendants.get(randInt).to;

            visited.put(newNode, true);
            inputNode(newNode);

        }
        else {
            currentNode = src;
        }
    }


}
