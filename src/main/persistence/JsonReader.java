package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.NicheHoliday;
import org.json.*;
import model.NicheGlossary;

// Represents a reader that reads NicheGlossary from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads NicheGlossary from file and returns it;
    // throws IOException if an error occurs reading data from file
    public NicheGlossary read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseNicheGlossary(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses NicheGlossary from JSON object and returns it
    private NicheGlossary parseNicheGlossary(JSONObject jsonObject) {
        NicheGlossary ng = new NicheGlossary();
        addNicheHolidays(ng, jsonObject);
        return ng;
    }

    // MODIFIES: ng
    // EFFECTS: parses NicheGlossary from JSON object and adds them to workroom
    private void addNicheHolidays(NicheGlossary ng, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("holidays");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addNicheHoliday(ng, nextThingy);
        }
    }


    // MODIFIES: ng
    // EFFECTS: parses NicheHoliday from JSON object and adds it to workroom
    private void addNicheHoliday(NicheGlossary ng, JSONObject jsonObject) {
        int month = jsonObject.getInt("month");
        int day = jsonObject.getInt("day");
        String name = jsonObject.getString("name");
        String note = jsonObject.getString("note");
        NicheHoliday nicheHoliday = new NicheHoliday(month, day, name, note);
        ng.addToGlossary(nicheHoliday);
    }





}
