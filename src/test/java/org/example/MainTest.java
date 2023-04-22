package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.Main.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    public Graph g = null;
    @BeforeEach
    public void setUp(){
        g = parseGraph("src/test/java/org/example/test3.dot");
    }


    @Test
    @DisplayName("Ensure toString outputs correctly")
    public void testGraphToString() {
        g.toString();
    }

    @Test
    public void testExportToPNG() {
        assertDoesNotThrow(() -> exportToPNG(g, "testPNG"));
    }


    @Test
    @DisplayName("Test addNode")
    public void testAddNode() {
        g.addNode("new");
        assertDoesNotThrow(() -> g.findNode("new"));
    }
    @Test
    public void testAddExistingNode() {
       assertDoesNotThrow(() -> g.addNode("a"));
    }

    @Test
    public void removeNode() {
       assertDoesNotThrow(() -> g.removeNode("a"));
    }

    @Test
    public void removeNodeFail()
    {
        assertDoesNotThrow(() -> g.removeNode("DNE"));
    }
}