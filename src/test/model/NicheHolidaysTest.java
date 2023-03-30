package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.MonthDay;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NicheHolidaysTest {
    private NicheHoliday towelsDay;
    private NicheHoliday newtonmas;
    private NicheHoliday testHolidayI;


    @BeforeEach
    void runBefore(){
        towelsDay = new NicheHoliday(
            5, 25,
            "World Towel Day",
            "Grab Your Towel and Don't panic");

        newtonmas = new NicheHoliday(12,25,
                "Grav Mass Day or Newtonmas",
                "instead of marry christmas, you should say "
                        + "may the Force be proportional to your acceleration");


        testHolidayI = new NicheHoliday(3, 28, "Something on a Stick Day",
                "There isn’t much that can’t be put on a stick when talking about food. Soup might "
                        + "be that one exception, though if it were flavorful frozen, "
                        + "we might make an exception.");

    }


    @Test
    void testConstructor() {
        assertEquals("World Towel Day", towelsDay.getName());
        assertEquals(5, towelsDay.getMonth());
        assertEquals(25, towelsDay.getDay());
        assertEquals("Grab Your Towel and Don't panic", towelsDay.getNote());
    }

    @Test
    void testIsToday() {
        MonthDay today = MonthDay.from(LocalDate.now());
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        towelsDay.changeDate(month, day);
        newtonmas.changeDate(month, day);
        assertTrue(towelsDay.isToday());
        assertTrue(newtonmas.isToday());
        month++;
        day++;
        towelsDay.changeDate(month, day);
        newtonmas.changeDate(month, day);
        assertFalse(towelsDay.isToday());
        assertFalse(newtonmas.isToday());
    }


    @Test
    void testIsTheGivenDay() {
        assertTrue(towelsDay.isTheGivenDay(5, 25));
        assertTrue(newtonmas.isTheGivenDay(12, 25));
        assertFalse(newtonmas.isTheGivenDay(12, 26));
        assertFalse(towelsDay.isTheGivenDay(5, 26));
        assertFalse(towelsDay.isTheGivenDay(12, 25));
        assertFalse(testHolidayI.isTheGivenDay(2,23));
    }


    @Test
    void testChangeDate() {
        towelsDay.changeDate(6,25);
        assertFalse(towelsDay.isTheGivenDay(5, 25));
        assertTrue(towelsDay.isTheGivenDay(6, 25));
        towelsDay.changeDate(5,28);
        assertFalse(towelsDay.isTheGivenDay(6, 25));
        assertTrue(towelsDay.isTheGivenDay(5, 28));
    }


    @Test
    void testChangeName() {
        towelsDay.changeName("A better World Towel Day");
        assertEquals("A better World Towel Day", towelsDay.getName());
    }


    @Test
    void testAddNote() {
        towelsDay.addNote(". Remember! the answer is always 42!");
        assertEquals("Grab Your Towel and Don't panic. Remember! the answer is always 42!",
                towelsDay.getNote());
        newtonmas.addNote(". May the Force be with you");
        assertEquals("instead of marry christmas, you should say "
                + "may the Force be proportional to your acceleration. May the Force be with you",
                newtonmas.getNote());
    }

    @Test
    void testDeleteNote() {
        towelsDay.deleteNote();
        assertEquals("", towelsDay.getNote());
        newtonmas.deleteNote();
        assertEquals("", newtonmas.getNote());
    }


}
