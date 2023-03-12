package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckFormatTest {
    private CheckedFormat testFormat;


    @BeforeEach
    void runBefore(){
        testFormat = new CheckedFormat("yyyy-MM-dd");
    }

    @Test
    void testConstructor() {
        assertEquals("yyyy-MM-dd", testFormat.getFormatString());
    }

    @Test
    void testIsItInFormat() {
        assertTrue(testFormat.isItInFormat("2022-01-12"));
        assertTrue(testFormat.isItInFormat("2022-05-17"));
        assertTrue(testFormat.isItInFormat("2023-12-25"));
        assertFalse(testFormat.isItInFormat("2023-2-29"));
        assertFalse(testFormat.isItInFormat("2023-3-33"));
        assertFalse(testFormat.isItInFormat("2023-13-1"));
    }





}
