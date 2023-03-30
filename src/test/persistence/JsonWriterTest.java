package persistence;

import model.NicheHoliday;
import model.NicheGlossary;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest {


    @Test
    void testWriterInvalidFile() {
        try {
            NicheGlossary ng = new NicheGlossary();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            NicheGlossary ng = new NicheGlossary();
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty2Workroom.json");
            writer.open();
            writer.write(ng);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmpty2Workroom.json");
            ng = reader.read();
            assertEquals(0, ng.getHolidays().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            NicheGlossary ng = new NicheGlossary();
            ng.addToGlossary(new NicheHoliday(1, 1, "Polar Bear Plunge Day",
                    "today people jump into a cold water body just like polar bears"));
            ng.addToGlossary(new NicheHoliday(12,25,"Grav Mass Day or Newtonmas",
                            "instead of marry christmas, you should say "
                                    + "may the Force be proportional to your acceleration"));
            ng.addToGlossary(new NicheHoliday(5, 25, "World Towel Day",
                    "Grab Your Towel and Don't panic"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneral2Workroom.json");
            writer.open();
            writer.write(ng);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneral2Workroom.json");
            ng = reader.read();
            assertEquals(3, ng.getHolidays().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }





}
