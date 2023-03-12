package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckNicheDate {
    private NicheDate testNicheDate;

    @BeforeEach
    void runBefore(){
        testNicheDate = new NicheDate();
    }

    @Test
    void testConstructor() {
        assertEquals(LocalDate.now(), testNicheDate.getNicheDay());
    }


    @Test
    void testModifyNicheDay() {
        testNicheDate.modifyNicheDay("tom");
        LocalDate tom = LocalDate.now().plusDays(1);
        assertEquals(tom, testNicheDate.getNicheDay());
        testNicheDate.modifyNicheDay("yest");
        testNicheDate.modifyNicheDay("yest");
        LocalDate yest = LocalDate.now().minusDays(1);
        assertEquals(yest, testNicheDate.getNicheDay());
        testNicheDate.modifyNicheDay("today");
        assertEquals(LocalDate.now(), testNicheDate.getNicheDay());
        testNicheDate.modifyNicheDay("2023-09-06");
        LocalDate sepSixth = LocalDate.of(2023, 9, 06);
        assertEquals(sepSixth, testNicheDate.getNicheDay());

    }




}
