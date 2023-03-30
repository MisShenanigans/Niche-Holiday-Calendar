package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NicheGlossaryTest {

    private NicheGlossary testGlossary;
    private NicheHoliday testHolidayI;
    private NicheHoliday testHolidayII;
    private NicheHoliday testHolidayIII;
    private int defaultNum;

    @BeforeEach
    void runBefore() {
        testGlossary = new NicheGlossary();
        defaultNum = testGlossary.getHolidays().size();
        testHolidayI = new NicheHoliday(7, 9, "World Towel Day",
                "Grab Your Towel and Don't panic");
        testHolidayII = new NicheHoliday(8,9,"Grav Mass Day or Newtonmas",
                "instead of marry christmas, you should say "
                        + "may the Force be proportional to your acceleration");
        testHolidayIII = new NicheHoliday(9, 9, "Something on a Stick Day",
                "There isn’t much that can’t be put on a stick when talking about food. Soup might "
                        + "be that one exception, though if it were flavorful frozen, "
                        + "we might make an exception.");
    }

    @Test
    void testConstructor() {
        assertEquals(defaultNum, testGlossary.getHolidays().size());
    }


    @Test
    void testAdder() {
        testGlossary.addToGlossary(testHolidayI);
        assertEquals(defaultNum+1, testGlossary.getHolidays().size());
        testGlossary.addToGlossary(testHolidayII);
        testGlossary.addToGlossary(testHolidayIII);
        assertEquals(defaultNum+3, testGlossary.getHolidays().size());
    }


    @Test
    void testRemover() {
        testGlossary.addToGlossary(testHolidayI);
        testGlossary.addToGlossary(testHolidayII);
        testGlossary.addToGlossary(testHolidayIII);
        assertEquals(defaultNum+3, testGlossary.getHolidays().size());
        testGlossary.removeFromGlossary(7, 9);
        assertEquals(defaultNum+2, testGlossary.getHolidays().size());
        testGlossary.removeFromGlossary(8, 9);
        assertEquals(defaultNum+1, testGlossary.getHolidays().size());
        testGlossary.removeFromGlossary(5, 26);
        assertEquals(defaultNum+1, testGlossary.getHolidays().size());
        testGlossary.removeFromGlossary(9, 9);
        assertEquals(defaultNum, testGlossary.getHolidays().size());
    }








}
