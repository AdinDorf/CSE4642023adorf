package org.example;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

abstract class Search {
    Node currentNode;
    final Node src;
    final Node dst;
    final Path p;

    //Init Dictionary to keep track of visited nodes
    final Dictionary<Node, Boolean> visited;

    //create the nodes and edges arraylists to pass in

    final ArrayList<Node> nodes;
    final ArrayList<Edge> edges;

    Search(Node src, Node dst, ArrayList<Node> nodes, ArrayList<Edge> edges) {
        this.src = src;
        this.dst = dst;
        this.nodes = nodes;
        this.edges = edges;
        p = new Path();
        visited = new Hashtable<>();
    }

    void initVisited()
    {
        currentNode = src;
        for (Node node : nodes)
        {
            visited.put(node, false);
        }
        visited.put(currentNode, true);
    }

    Path runSearch()
    {
        //Set all nodes to unvisited
        initVisited();
        //Put the first node in the sata structure
        inputNode(currentNode);
        do
        {
            //Take out the top node of the data structure
            currentNode = removeNode();
            if (currentNode == null)
            {
                currentNode = src;
            }
            //Add the node to the path log
            p.logPath(currentNode);
            //Check for the destination node

            if (currentNode.equals(dst)) {
                return checkPath();
            }
            //Input nodes attached to the current node
            inputDescendants();

        } while (currentNode != null);
        throw new NodeNotFoundException("Node " + dst.label + " not found!");


    }

    abstract void inputDescendants();

    abstract Node removeNode();
    abstract void inputNode(Node n);
    private Path checkPath()
    {
        //traverse through the parent nodes
        p.add(currentNode);
        Node parentNode = currentNode.parent.from;
        while (!parentNode.equals(src)) {
            p.add(parentNode);
            parentNode = parentNode.parent.from;
        }
        p.add(src);
        return p;
    }


}
