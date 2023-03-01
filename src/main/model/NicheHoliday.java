package model;

import java.time.LocalDate;
import java.time.MonthDay;


public class NicheHoliday {

    private MonthDay date;                 // Month and Date of the niche holiday
    private String name;                   // name of the niche holiday
    private String note;                  // info about the niche holiday


    /*
     * REQUIRES: holidayMonth is an integer between 1 and 12
     *           holidayDay is an integer between 1 and 29/30/31(depending on holidayMonth)
                 holidayName must be a string with length greater than 0
     * EFFECTS: construct an niche holiday in the class
     */
    public NicheHoliday(int holidayMonth,int holidayDay, String holidayName, String holidayNote) {
        this.name = holidayName;
        this.note = holidayNote;
        this.date = MonthDay.of(holidayMonth, holidayDay);
    }

    /*
     * EFFECTS: tell if today is the designated Niche Holiday
     */
    public boolean isToday() {
        MonthDay today = MonthDay.from(LocalDate.now());
        return date.equals(today);
    }

    /*
     * REQUIRES: holidayMonth is an integer between 1 and 12
              holidayDay is an integer between 1 and 29/30/31(depending on holidayMonth)
     * EFFECTS: tell if the given date is the designated Niche Holiday
     */
    public boolean isTheGivenDay(int holidayMonth, int holidayDay) {
        return ((date.getMonthValue() == holidayMonth) && (date.getDayOfMonth() == holidayDay));
    }



    /*
    *REQUIRES: holidayMonth is an integer between 1 and 12
               holidayDay is an integer between 1 and 29/30/31(depending on holidayMonth)
    *MODIFIERS: this
    *EFFECTS: change the niche holiday's date
    */
    public void changeDate(int holidayMonth, int holidayDay) {
        this.date =  MonthDay.of(holidayMonth, holidayDay);
    }

    /*
    *REQUIRES: holidayName must be a string with length greater than 0
    *MODIFIERS: this
    *EFFECTS: change the niche holiday's name
    */
    public void changeName(String holidayName) {
        this.name = holidayName;
    }

    /*
     *MODIFIERS: this
     *EFFECTS: add the description to the niche holiday's description
     */
    public void addNote(String holidayNote) {
        this.note = note + holidayNote;
    }


    /*
     *MODIFIERS: this
     *EFFECTS: delete the description of the niche holiday
     */
    public void deleteNote() {
        this.note = "";
    }

    public MonthDay getDate() {
        return date;
    }

    public int getMonth() {
        return date.getMonthValue();
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }


}
