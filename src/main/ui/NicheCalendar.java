package ui;

import model.NicheDate;
import model.NicheGlossary;
import model.NicheHoliday;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.YearMonth;



/**
 Primary user interface that handles user command, and result printing
 */
public class NicheCalendar {
    private static final String JSON_STORE = "./data/SavedNicheGlossary.json";
    private Scanner input;
    private NicheGlossary loadedGlossary;
    private NicheDate nicheDate;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Boolean homeKeepGoing = true;
    private Boolean checkKeepGoing = false;

    /*
     *EFFECTS: Construct an object and initialize the entire class
     */
    public NicheCalendar() {
        this.loadedGlossary = new NicheGlossary();
        this.nicheDate = new NicheDate();
        this.input = new Scanner(System.in);
        LocalDate date = nicheDate.getNicheDay();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initialization(date);

        while (true) {
            if (homeKeepGoing) {
                introAnnouncement();
                handleHomePage(input);
            } else if (checkKeepGoing) {
                handleCalendarView(input);
            } else {
                break;
            }
        }

    }


    /*
     *EFFECTS: Handle different commands, print calendar and produce niche info
     */
    public void handleHomePage(Scanner newCommand) {

        while (homeKeepGoing) {
            String input = newCommand.nextLine();


            if (input.equals("add")) {
                addNicheDay();
                introAnnouncement();
            } else if (input.equals("save")) {
                saveNicheGlossary();
            } else if (input.equals("load")) {
                loadNicheGlossary();
            } else if (input.equals("check")) {
                homeKeepGoing = false;
                checkKeepGoing = true;
            } else if (input.equals("exit")) {
                homeKeepGoing = false;
                checkKeepGoing = false;
                break;
            }
        }
    }


    /*
     *EFFECTS: Handle different commands, print calendar and produce niche info
     *MODIFY: nicheDate
     */
    public void handleCalendarView(Scanner newCommand) {


        while (checkKeepGoing) {
            checkAnnouncement();
            // Getting input
            String input = newCommand.nextLine();
            // Exit Option
            if (input.equals("back")) {
                checkKeepGoing = false;
                homeKeepGoing = true;
            } else if (input.equals("exit")) {
                checkKeepGoing = false;
                homeKeepGoing = false;
                break;
            } else {
                nicheDate.modifyNicheDay(input);
            }
            LocalDate date = nicheDate.getNicheDay();
            goToDesiredDate(date);
        }
    }




    /*
     *REQUIRE: The first two inputs must consist a meaningful MonthDay
     *EFFECTS: take inputs from the user and make a niche holiday put it into glossary
     *MODIFY: loadedGlossary
     */
    public void addNicheDay() {
        System.out.print("Enter month number: ");
        int month = input.nextInt();
        System.out.print("Enter day number: ");
        int day = input.nextInt();

        input.nextLine();
        System.out.print("Enter the name of the holiday: ");
        String name = input.nextLine();

        input.nextLine();
        System.out.print("Enter a short description of your holiday: ");
        String description = input.nextLine();

        NicheHoliday toAdd = new NicheHoliday(month, day, name, description);
        loadedGlossary.addToGlossary(toAdd);
        System.out.print("It is done");
    }

    /*
     *EFFECTS: Give basic introAnnouncement.
     */
    public static void introAnnouncement() {
        System.out.println("Enter one of the given options");
        System.out.println("\nSelect from:");
        System.out.println("\tadd -> add your own NicheDay");
        System.out.println("\tsave -> save your Niche Glossary");
        System.out.println("\tload -> load your Niche Glossary");
        System.out.println("\tcheck -> look at NicheHoliday Calender");
        System.out.println("\texit -> end program");
        System.out.print("Enters:");
    }


    /*
     *EFFECTS: Give basic instructions on checking dates.
     */
    public static void checkAnnouncement() {
        System.out.println("Time for even more niches~~ ");
        System.out.println("Enter one of the given options");
        System.out.println("\nSelect from:");
        System.out.println("\tdate -> in (yyyy-MM-dd) to go to your entered date");
        System.out.println("\ttom -> go to tomorrow");
        System.out.println("\tyest -> go to yesterday");
        System.out.println("\ttoday -> go back to today");
        System.out.println("\tback -> go back to previous menu");
        System.out.println("\texit -> end program");
        System.out.print("Enters:");
    }


    // EFFECTS: saves the loadedGlossary to file
    private void saveNicheGlossary() {
        try {
            jsonWriter.open();
            jsonWriter.write(loadedGlossary);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // MODIFIES: this
    // EFFECTS: loads loadedGlossary from file
    private void loadNicheGlossary() {
        try {
            loadedGlossary = jsonReader.read();
            System.out.println("Loaded " +  " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    /*
     *EFFECTS: Produce the calendar view and niche info about today
     */
    public void initialization(LocalDate date) {
        System.out.println("Today is " + date);
        System.out.println("Here is the Calendar");
        printCalendar(date);
        isTodayNiche();
    }




    /*
     *REQUIRES: date must be a LocalDate
     *EFFECTS: Print out the month and niche information about that date
     */
    private void goToDesiredDate(LocalDate date) {

        // Print a calendar and niche info
        printCalendar(date);
        isThatDayNiche(date);
    }


     /*
     *REQUIRES: year month and day being an actual date
     *EFFECTS: Print out relevant info about the checked niche holiday
     */
    private void isThatDayNiche(LocalDate dateInQuestion) {

        // Set up date
        LocalDate date = dateInQuestion;
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        // Check if the input date is a holiday
        for (NicheHoliday nicheholiday : loadedGlossary.getHolidays()) {
            if (nicheholiday.isTheGivenDay(month, day)) {
                System.out.println(date + " is " + nicheholiday.getName());
                System.out.println("Something about that day: " + nicheholiday.getNote());
                return;
            }
        }
        System.out.println("what a terrible luck are you having? " + date + " is not niche");

    }

    /*
     *EFFECTS: Return info regarding to if today is niche
     */
    public void isTodayNiche() {
        for (NicheHoliday nicheholiday : loadedGlossary.getHolidays()) {
            if (nicheholiday.isToday()) {
                System.out.println("Wow, today is " + nicheholiday.getName());
                System.out.println("Please note that " + nicheholiday.getNote());
                return;
            }
        }
        System.out.println("Sadly, today is boring, no niche holiday for today");
    }



    /*
     *REQUIRES: year, month are consistent with the date.
     *EFFECTS: Print a date in their respective month in a calendar view.
     */
    private static void printCalendar(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        YearMonth yearMonth = YearMonth.of(year, month);

        // Print the month and year on the top
        System.out.println(yearMonth.getMonth() + " " + year);

        // Print the first row that represent week
        System.out.println(" Su Mo Tu We Th Fr Sa");

        // Find where to start
        int startDay = yearMonth.atDay(1).getDayOfWeek().getValue();

        // Adjust startDay to account for months that don't start on a Sunday
        startDay %= 7;

        // Print the calendar rows
        for (int i = 0; i < startDay; i++) {
            System.out.print("   ");
        }
        for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
            handleInteger(i, date, startDay);
        }
        System.out.println("\n");
    }


    /*
     *EFFECTS: Additional helper method due to line number restriction
     */
    private static void handleInteger(int i, LocalDate date, int startDay) {
        if (i == date.getDayOfMonth()) {
            System.out.print("\u001B[34m");  // Note: change the color here
        }
        if (i < 10) {
            System.out.print("  " + i);
        } else {
            System.out.print(" " + i);
        }
        if (i > date.getDayOfMonth() - 1) {
            System.out.print("\u001B[0m");   // return to colorless
        }
        if ((startDay + i - 1) % 7 == 6) {
            System.out.println();
        }
    }



}

