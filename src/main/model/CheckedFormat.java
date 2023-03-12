package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;


/**
This class is create to check if a certain string is written in the given dateFormat
 */
public class CheckedFormat {
    private String dateFormat;

    /*
     * EFFECTS: construct an Format to be checked
     */
    public CheckedFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }


    /*
     * EFFECTS: return the CheckedFormat as a DateFormat
     */
    public DateTimeFormatter getFormat() {
        DateTimeFormatter toReturn = DateTimeFormatter.ofPattern(dateFormat);
        return toReturn;
    }


    /*
     * EFFECTS: return the CheckedFormat as a String
     */
    public String getFormatString() {
        return dateFormat;
    }

    /*
     * EFFECTS: decide if the given boolean is in the given format
     */
    public boolean isItInFormat(String date) {
        try {
            DateFormat df = new SimpleDateFormat(dateFormat);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }





}





