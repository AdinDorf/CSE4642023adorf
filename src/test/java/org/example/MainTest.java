package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.Main.*;
import static org.junit.jupiter.api.Assertions.*;

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
        exportToPNG(g, "testPNG");
    }

    @Test
    void testExportToPNGFail()
    {
        /*
        Exception e = assertThrows(Exception.class, () -> {
            exportToPNG(null, "testFail");
        });*/
        exportToPNG(null, "testFail");
    }

    @Test
    @DisplayName("Test addNode")
    void testAddNode() {
        g.addNode("new");
        assertNotNull(g.findNode("new"));
    }

    @Test
    void testAddExistingNode() {
        Exception e = assertThrows(RuntimeException.class, () ->  {
            g.addNode("A");
        });
    }

    @Test
    void removeNode() {
        g.removeNode("A");
        assertNull(g.findNode("A"));
    }

    @Test
    void removeNodeFail()
    {
        Exception e = assertThrows(RuntimeException.class, () ->  {
            g.removeNode("DNE");
        });
    }
}