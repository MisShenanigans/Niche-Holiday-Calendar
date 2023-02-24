package ui;

import model.NicheHoliday;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.YearMonth;

import static ui.NicheGlossary.NICHE_GLOSSARY;


public class NicheCalendar {


    public static void main(String[] args) {
        DateTimeFormatter usedFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner scanner = new Scanner(System.in);
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        initialization(year, month, date, args);


        while (true) {
            // Getting input
            System.out.print("Enter a date (yyyy-MM-dd), or type 'tom' for tomorrow and 'yest' "
                    + "for yesterday, type ’today‘ to go back to today, type 'quit' to end program: ");
            String input = scanner.nextLine();

            // Exit Option
            if (input.equals("exit")) {
                break;
            } else {
                handleInput(input, date, usedFormat);
            }

        }
    }


    /*
     *EFFECTS: Encapsulate several ways to handle input together because of line number restriction
     */
    public static void handleInput(String input, LocalDate date, DateTimeFormatter usedFormat) {
        // Tomorrow
        if (input.equals("tom")) {
            date = date.plusDays(1);
            goToDesiredDate(date);
        } // Yesterday
        if (input.equals("yest")) {
            date = date.plusDays(-1);
            goToDesiredDate(date);
        } // Go back to today
        if (input.equals("today")) {
            date = LocalDate.now();
            goToDesiredDate(date);
        } // Go to the given date
        if (isItADate(input)) {
            date = LocalDate.parse(input, usedFormat);
            goToDesiredDate(date);
        } else {
            System.out.println("Time for even more Niche~~ ");
        }

    }



    /*
     *EFFECTS: Produce the calendar view and niche info about today
     */
    public static void initialization(int year, int month, LocalDate date, String[] args) {
        System.out.println("Today is " + date);
        System.out.println("Here is the Calendar");
        printCalendar(year, month, date);
        IsTodayNiche todayNiche = new IsTodayNiche();
        todayNiche.main(args);
    }


    /*
     *EFFECTS: Decide if the given Boolean is an date we want
     */
    public static boolean isItADate(String date) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


    /*
     *REQUIRES: date must be a LocalDate
     *EFFECTS: Print out the month and niche information about that date
     */
    private static void goToDesiredDate(LocalDate date) {

        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        // Print a calendar and niche info
        printCalendar(year, month, date);
        isThatDayNiche(year, month, day);
    }




     /*
     *REQUIRES: year month and day being an actual date
     *EFFECTS: Print out relevant info about the checked niche holiday
     */
    private static void isThatDayNiche(int year, int month, int day) {

        // Set up date
        LocalDate date = LocalDate.of(year, month, day);

        // Check if the input date is a holiday
        for (NicheHoliday nicheholiday : NICHE_GLOSSARY) {
            if (nicheholiday.isTheGivenDay(month, day)) {
                System.out.println(date + " is " + nicheholiday.getName());
                System.out.println("Something about that day: " + nicheholiday.getNote());
                return;
            }
        }
        System.out.println("what a terrible luck are you having? " + date + " is not niche");
    }



    /*
     *REQUIRES: year, month are consistent with the date.
     *EFFECTS: Print a date in their respective month in a calendar view.
     */
    private static void printCalendar(int year, int month, LocalDate date) {
        YearMonth yearMonth = YearMonth.of(year, month);

        // Print the month and year on the toop
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

