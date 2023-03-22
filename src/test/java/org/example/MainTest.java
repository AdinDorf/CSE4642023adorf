package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.Main.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class MainTest {
    private MyGraph g = null;
    @BeforeEach
    void setUp(){
        g = parseGraph("src/test/java/org/example/ex.dot");
    }

    @Test
    @DisplayName("Ensure ToString works correctly")
    void testGraphToString() {
        graphToString(g);
    }

    @Test
    void testExportToPNG() {
    }

    @Test
    @DisplayName("Test addNode")
    void testAddNode() {
        g.addNode("new");
        assertNotNull(g.findNode("new"));
    }



    @Test
    void removeNode() {
        g.removeNode("A");
        assertNull(g.findNode("A"));
    }
}