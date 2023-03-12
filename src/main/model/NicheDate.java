package model;

import java.time.LocalDate;



/**
 The primary data type for check date functionality to operates on, it contains a LocalDate
 and a CheckedFormat to check
 */

public class NicheDate {

    private LocalDate nicheDay;
    private CheckedFormat toCheck;


    /*
     * EFFECTS: construct an NicheDay same as today
     */
    public NicheDate() {
        this.nicheDay = LocalDate.now();
        this.toCheck = new CheckedFormat("yyyy-MM-dd");
    }



    /*
     * EFFECTS: change the format used here
     */
    public void changeFormat(String format) {
        this.toCheck = new CheckedFormat(format);
    }


    public void modifyNicheDay(String command) {

        if (command.equals("tom")) {
            nicheDay = nicheDay.plusDays(1);
        } // Yesterday
        if (command.equals("yest")) {
            nicheDay = nicheDay.plusDays(-1);
        } // Go back to today
        if (command.equals("today")) {
            nicheDay = LocalDate.now();
        } // Go to the given date
        if (toCheck.isItInFormat(command)) {
            nicheDay = LocalDate.parse(command, toCheck.getFormat());
        }
    }


    public LocalDate getNicheDay() {
        return nicheDay;
    }

    public CheckedFormat getFormat() {
        return toCheck;
    }


}
