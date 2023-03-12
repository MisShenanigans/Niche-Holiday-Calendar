package ui;

import model.NicheDate;
import model.NicheHoliday;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.YearMonth;



/**
 Primary user interface that handles user command, and result printing
 */
public class NicheCalendar {
    private Scanner input;
    private NicheGlossary loadedGlossary;
    private NicheDate nicheDate;
    private Boolean homeKeepGoing = true;
    private Boolean checkKeepGoing = false;

    public NicheCalendar() {
        this.loadedGlossary = new NicheGlossary();
        this.nicheDate = new NicheDate();
        this.input = new Scanner(System.in);
        LocalDate date = nicheDate.getNicheDay();

        initialization(date);
        introAnnouncement();

        while (true) {
            if (homeKeepGoing) {
                introAnnouncement();
                handleHomePage(input);
            } else if (checkKeepGoing) {
                handleCases(input);
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
            } else if (input.equals("check")) {
                homeKeepGoing = false;
                checkKeepGoing = true;
            } else if (input.equals("exit")) {
                homeKeepGoing = false;
                checkKeepGoing = false;
            }
        }
    }


    /*
     *EFFECTS: Handle different commands, print calendar and produce niche info
     */
    public void handleCases(Scanner newCommand) {
        checkAnnouncement();

        while (checkKeepGoing) {
            // Getting input
            String input = newCommand.nextLine();
            // Exit Option
            if (input.equals("back")) {
                checkKeepGoing = false;
                homeKeepGoing = true;
            } else {
                nicheDate.modifyNicheDay(input);
            }
            LocalDate date = nicheDate.getNicheDay();
            goToDesiredDate(date);
        }
    }




    /*
     *EFFECTS: take inputs from the user and make a niche holiday put it into glossary
     */
    public void addNicheDay() {
        System.out.print("Enter month number");
        int month = input.nextInt();
        System.out.print("Enter day number");
        int day = input.nextInt();
        System.out.print("Enter the name of the holiday");
        String name = input.next();

        System.out.print("Enter a short description of your holiday");
        String description = input.next();

        NicheHoliday toAdd = new NicheHoliday(month, day, name, description);
        loadedGlossary.addToGlossary(toAdd);
        System.out.print("It is done");
    }

    /*
     *EFFECTS: Give basic introAnnouncement.
     */
    public static void introAnnouncement() {
        System.out.println("Enter 'add' to add your own NicheDay, enter 'check' "
                + "to look at NicheDay, enter 'exit' to exit");
        System.out.print("Enters:");
    }


    /*
     *EFFECTS: Give basic instructions on checking dates.
     */
    public static void checkAnnouncement() {
        System.out.println("Time for even more niches~~ ");
        System.out.println("Enter a date (yyyy-MM-dd), or type 'tom' for tomorrow and 'yest' "
                + "for yesterday, type ’today‘ to go back to today, type 'exit' to end program:");
        System.out.print("Enters:");
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

