package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.Main.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    public MyGraph g = null;
    @BeforeEach
    public void setUp(){
        g = parseGraph("src/test/java/org/example/ex.dot");
    }

    /*
    @Test
    @DisplayName("Ensure ToString works correctly")
    public void testGraphToString() {
        assertDoesNotThrow();graphToString(g);
    }
*/
    @Test
    public void testExportToPNG() {
        exportToPNG(g, "testPNG");
    }

/*
    @Test
    @DisplayName("Test addNode")
    public void testAddNode() {
        g.addNode("new");
        assertNotNull(g.findNode("new"));
    }
*/
    @Test
    public void testAddExistingNode() {
       assertThrows(RuntimeException.class, () -> g.addNode("A"));
    }

    @Test
    public void removeNode() {
        g.removeNode("A");
        assertNull(g.findNode("A"));
    }

    @Test
    public void removeNodeFail()
    {
        assertThrows(RuntimeException.class, () -> g.removeNode("DNE"));
    }
}