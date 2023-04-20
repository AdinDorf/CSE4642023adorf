package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;

public class Search {
    Node currentNode;
    final Node src;
    final Node dst;
    final Path p;

    //Init Dictionary to keep track of visited nodes
    final Dictionary<Node, Boolean> visited;

    //create the nodes and edges arraylists to pass in

    final ArrayList<Node> nodes;
    final ArrayList<Edge> edges;

    //For the strategy design pattern,
    AddBehavior addBehavior;
    RemoveBehavior removeBehavior;
    WalkBehavior walkBehavior;
    InitBehavior initBehavior;
    Collection<Node> nodeCollection;

    Search(Node src, Node dst, ArrayList<Node> nodes, ArrayList<Edge> edges, AddBehavior addBehavior, RemoveBehavior  removeBehavior, WalkBehavior walkBehavior, InitBehavior initBehavior) {
        this.src = src;
        this.dst = dst;
        this.nodes = nodes;
        this.edges = edges;
        p = new Path();
        visited = new Hashtable<>();
        this.addBehavior = addBehavior;
        this.removeBehavior = removeBehavior;
        this.walkBehavior = walkBehavior;
        this.initBehavior = initBehavior;
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
        initData();
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
            walk();

        } while (currentNode != null);
        throw new NodeNotFoundException("Node " + dst.label + " not found!");


    }

    void initData()
    {
        nodeCollection = initBehavior.initData();
    }

    void walk()
    {
        walkBehavior.walk(nodeCollection, visited, currentNode, addBehavior);
    }

    Node removeNode()
    {
        return removeBehavior.removeNode(nodeCollection);
    }
    void inputNode(Node n)
    {
        addBehavior.addNode(nodeCollection, n);
    }
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