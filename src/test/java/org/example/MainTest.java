package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.Main.*;

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
    void exportToPNG() {
    }

    @Test
    void addNode() {

    }

    @Test
    void removeNode() {
    }
}