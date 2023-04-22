package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.Main.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    Graph testgraph;
    @BeforeEach
    public void setUp(){
        testgraph = parseGraph("src/test/java/org/example/test3.dot");
    }


    @Test
    @DisplayName("Ensure toString outputs correctly")
    public void testGraphToString() {
      //  g = parseGraph("src/test/java/org/example/test3.dot");
        testgraph.toString();
    }

    @Test
    public void testExportToPNG() {
       // g = parseGraph("src/test/java/org/example/test3.dot");
        assertDoesNotThrow(() -> exportToPNG(testgraph, "testPNG"));
    }


    @Test
    @DisplayName("Test addNode")
    public void testAddNode() {
        testgraph.addNode("new");
        assertDoesNotThrow(() -> testgraph.findNode("new"));
    }
    @Test
    public void testAddExistingNode() {
       assertDoesNotThrow(() -> testgraph.addNode("a"));
    }

    @Test
    public void removeNode() {
       assertDoesNotThrow(() -> testgraph.removeNode("a"));
    }

    @Test
    public void removeNodeFail()
    {
        assertDoesNotThrow(() -> testgraph.removeNode("DNE"));
    }
}