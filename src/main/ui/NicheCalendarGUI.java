package ui;

import model.NicheDate;
import model.NicheHoliday;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

import javax.swing.border.Border;


/**
 Primary user interface that handles user command, and result printing
 */
public class NicheCalendarGUI {
    private static final String JSON_STORE = "./data/SavedNicheGlossary.json";

    private Scanner input;
    private NicheGlossary loadedGlossary;
    private NicheDate nicheDate;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Boolean homeKeepGoing = true;
    private Boolean checkKeepGoing = false;


    private JFrame mainFrame = new JFrame();

    private JLabel calendarView = new JLabel();
    private JPanel goToDayHolder = new JPanel();
    private JPanel addDayHolder = new JPanel();
    private JPanel saveLoadHolder = new JPanel();
    private JPanel topInfoHolder = new JPanel();

    private JButton goToDay = new JButton();
    private JButton goToToday = new JButton();
    private JButton saveButton = new JButton();
    private JButton loadButton = new JButton();
    private JButton tomorrow = new JButton();
    private JButton yesterday = new JButton();

    private JTextField desiredDate = new JTextField();
    private JTextField recordedDate = new JTextField();
    private JTextField nicheName = new JTextField();
    private JTextArea nicheDescription = new JTextArea();



    private ImageIcon calendarIcon = new ImageIcon("Icon.jpg");
    private ImageIcon calendarViewExample  = new ImageIcon("CalendarView Example.jpg");

    /*
     *EFFECTS: Construct an object and initialize the entire class
     */
    public NicheCalendarGUI() {
        this.loadedGlossary = new NicheGlossary();
        this.nicheDate = new NicheDate();
        this.input = new Scanner(System.in);
        LocalDate date = nicheDate.getNicheDay();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        mainFrame.setTitle("Niche Calendar"); //rename it
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 620);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);
        mainFrame.setIconImage(calendarIcon.getImage());
        mainFrame.getContentPane().setBackground(new Color(170, 51, 106));

        calendarViewSetUp();
        goToDayPanelSetUp();
        addDayHolderPanelSetUp();
        saveLoadHolderSetUp();
        topInfBarSetUp();

        //mainFrame.pack();


    }


    public void topInfBarSetUp() {
        topInfoHolder.setBackground(new Color(0, 102, 204));
        topInfoHolder.setBounds(20, 20, 940, 150);

        mainFrame.add(topInfoHolder);

    }


    public void goToDayPanelSetUp() {

        // set up panel
        goToDayHolder.setBackground(Color.LIGHT_GRAY);
        goToDayHolder.setBounds(20, 200, 140, 250);
        goToDayHolder.setLayout(null);

        // set up goToDay button
        goToDay.setBounds(20, 175, 100, 50);
        goToDay.setText("Let's Go");
        goToDay.setFocusable(false);
        goToDay.addActionListener(this::letsGoToDay);

        // set up goToToday button
        goToToday.setBounds(20, 25, 100, 50);
        goToToday.setText("Today");
        goToToday.setFocusable(false);
        goToToday.addActionListener(this::returnToToday);

        // set up desiredDate textField
        JLabel instruction = new JLabel("<html>Enter a Date <br> in (yyyy-MM-dd) </html>");
        instruction.setBounds(10, 75, 120, 70);
        desiredDate.setBounds(10, 130, 120, 35);


        // adding stuff to panel
        goToDayHolder.add(goToDay);
        goToDayHolder.add(goToToday);
        goToDayHolder.add(desiredDate);
        goToDayHolder.add(instruction);

        // adding panel to mainFrame
        mainFrame.add(goToDayHolder);

    }

    public void returnToToday(ActionEvent e) {
        if (e.getSource() == goToToday) {
            System.out.println("You are back");
            nicheDate.modifyNicheDay("today");
        }

    }


    public void letsGoToDay(ActionEvent e) {
        if (e.getSource() == goToDay) {
            System.out.println("here we go");
            String command = desiredDate.getText();
            nicheDate.modifyNicheDay(command);
        }

    }




    public void addDayHolderPanelSetUp() {

        addDayHolder.setBackground(Color.LIGHT_GRAY);
        addDayHolder.setBounds(190, 200, 140, 350);



        mainFrame.add(addDayHolder);

    }

    public void saveLoadHolderSetUp() {

        saveLoadHolder.setBackground(Color.LIGHT_GRAY);
        saveLoadHolder.setBounds(360, 200, 140, 350);



        mainFrame.add(saveLoadHolder);

    }




    public void calendarViewSetUp() {

        //String calendarContent = printCalendar(date);
        calendarView.setText("Reserved for Calendar View");
        calendarView.setIcon(calendarViewExample);
        calendarView.setHorizontalTextPosition(JLabel.CENTER);
        calendarView.setVerticalTextPosition(JLabel.TOP);
        calendarView.setForeground(Color.white);
        calendarView.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        calendarView.setIconTextGap(-2);
        calendarView.setBackground(Color.black);
        calendarView.setOpaque(true);
        Border usedBorder = BorderFactory.createLineBorder(Color.green);
        calendarView.setBorder(usedBorder);
        calendarView.setVerticalAlignment(JLabel.CENTER);
        calendarView.setHorizontalAlignment(JLabel.CENTER);
        calendarView.setBounds(700,350,260,220);


        mainFrame.add(calendarView);

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

