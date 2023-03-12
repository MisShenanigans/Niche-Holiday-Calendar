package persistence;


import ui.NicheGlossary;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            NicheGlossary nr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyNicheGlossary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            NicheGlossary nr = reader.read();
            assertEquals(0, nr.getHolidays().length);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            NicheGlossary nr = reader.read();
            assertEquals(44, nr.getHolidays().length);


        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }







}
