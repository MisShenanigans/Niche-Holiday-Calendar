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
    private static final String JSON_STORE = "./data/SavedNicheGlossary3.json";

    private Scanner input;
    private NicheGlossary loadedGlossary;
    private NicheDate nicheDate;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Boolean homeKeepGoing = true;
    private Boolean checkKeepGoing = false;


    private JFrame mainFrame = new JFrame();

    //Calendar view set up
    private JLabel monthYear = new JLabel();
    private String monthYearInfo = new String();
    private JLabel dayOfWeekView = new JLabel();
    private String dayOfWeekName = new String();
    private String line1 = new String();
    private JLabel lineOne = new JLabel();
    private String line2 = new String();
    private JLabel lineTwo = new JLabel();
    private String line3 = new String();
    private JLabel lineThree = new JLabel();
    private String line4 = new String();
    private JLabel lineFour = new JLabel();
    private String line5 = new String();
    private JLabel lineFive = new JLabel();


    private final JLabel calendarView = new JLabel();
    private final JLabel whatDatItIs = new JLabel();
    private final JLabel additionalInfo = new JLabel();

    // Panel set up
    private JPanel calenderViewHolder = new JPanel();
    private final JPanel goToDayHolder = new JPanel();
    private final JPanel addDayHolder = new JPanel();
    private final JPanel saveLoadHolder = new JPanel();
    private final JPanel topInfoHolder = new JPanel();

    private final JButton goToDay = new JButton();
    private final JButton goToToday = new JButton();

    private final JButton saveButton = new JButton();
    private final JButton loadButton = new JButton();
    private final JButton addButton = new JButton();
    private final JButton tomorrow = new JButton();
    private final JButton yesterday = new JButton();

    private final JTextField desiredDate = new JTextField();
    private final JTextField recordedMonth = new JTextField();
    private final JTextField recordedDay = new JTextField();
    private final JTextField nicheName = new JTextField();
    private final JTextArea nicheDescription = new JTextArea();



    private final ImageIcon calendarIcon = new ImageIcon("Icon.jpg");
    private final ImageIcon calendarViewExample  = new ImageIcon("CalendarView Example.jpg");

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

        mainFrame.setVisible(true);
    }

    public void goToDayPanelSetUp() {

        // set up panel
        goToDayHolder.setBackground(Color.LIGHT_GRAY);
        goToDayHolder.setBounds(190, 200, 140, 195);
        goToDayHolder.setLayout(null);

        // set up goToToday button
        goToToday.setBounds(20, 10, 100, 40);
        goToToday.setText("Today");
        goToToday.setFocusable(false);
        goToToday.addActionListener(this::returnToToday);

        // set up desiredDate textField
        JLabel instruction = new JLabel("<html>Enter a Date <br> in (yyyy-MM-dd) </html>");
        instruction.setBounds(10, 50, 120, 50);
        instruction.setVerticalTextPosition(JLabel.CENTER);
        instruction.setHorizontalTextPosition(JLabel.CENTER);
        instruction.setVerticalAlignment(JLabel.CENTER);
        instruction.setHorizontalAlignment(JLabel.CENTER);
        desiredDate.setBounds(10, 95, 120, 35);

        // set up goToDay button
        goToDay.setBounds(20, 135, 100, 50);
        goToDay.setText("Let's Go");
        goToDay.setFocusable(false);
        goToDay.addActionListener(this::letsGoToDay);



        // adding stuff to panel
        goToDayHolder.add(goToDay);
        goToDayHolder.add(goToToday);
        goToDayHolder.add(desiredDate);
        goToDayHolder.add(instruction);

        // adding panel to mainFrame
        mainFrame.add(goToDayHolder);

    }

    public void saveLoadHolderSetUp() {

        saveLoadHolder.setBackground(Color.LIGHT_GRAY);
        saveLoadHolder.setBounds(360, 200, 140, 105);
        saveLoadHolder.setLayout(null);

        saveButton.setBounds(20, 10, 100, 40);
        saveButton.setText("SAVE");
        saveButton.setFocusable(false);
        saveButton.addActionListener(this::doSave);

        loadButton.setBounds(20, 55, 100, 40);
        loadButton.setText("LOAD");
        loadButton.setFocusable(false);
        loadButton.addActionListener(this::doLoad);


        saveLoadHolder.add(saveButton);
        saveLoadHolder.add(loadButton);
        mainFrame.add(saveLoadHolder);

    }

    public void doSave(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveNicheGlossary();
        }
    }

    public void doLoad(ActionEvent e) {
        if (e.getSource() == loadButton) {
            loadNicheGlossary();
        }
    }






    @SuppressWarnings("methodlength")
    public void addDayHolderPanelSetUp() {

        addDayHolder.setBackground(Color.LIGHT_GRAY);
        addDayHolder.setBounds(20, 200, 140, 355);
        addDayHolder.setLayout(null);

        addButton.setBounds(20, 10, 100, 30);
        addButton.setText("Add Date");
        addButton.setFocusable(false);
        addButton.addActionListener(this::addDate);


        JLabel monthInstruction = new JLabel("Enter Month Number");
        monthInstruction.setBounds(10, 45, 120, 25);
        recordedMonth.setBounds(10, 70, 120, 35);

        JLabel dayInstruction = new JLabel("Enter Day Number");
        dayInstruction.setBounds(10, 110, 120, 25);
        recordedDay.setBounds(10, 135, 120, 35);

        JLabel nameInstruction = new JLabel("Enter Holiday Name");
        nameInstruction.setBounds(10, 180, 120, 25);
        nicheName.setBounds(10, 200, 120, 35);

        JLabel noteInstruction = new JLabel("Add a note");
        noteInstruction.setBounds(10, 240, 120, 25);
        nicheDescription.setBounds(10, 265, 120, 80);


        addDayHolder.add(addButton);
        addDayHolder.add(monthInstruction);
        addDayHolder.add(recordedMonth);
        addDayHolder.add(dayInstruction);
        addDayHolder.add(recordedDay);
        addDayHolder.add(nameInstruction);
        addDayHolder.add(nicheName);
        addDayHolder.add(noteInstruction);
        addDayHolder.add(nicheDescription);
        mainFrame.add(addDayHolder);
        isThatDayNiche(nicheDate.getNicheDay());
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
        calendarView.setBounds(520,200,420,350);


        mainFrame.add(calendarView);

    }



    public void topInfBarSetUp() {
        topInfoHolder.setBackground(new Color(0, 102, 204));
        topInfoHolder.setBounds(20, 20, 940, 150);
        topInfoHolder.setLayout(null);

        isTodayNiche();
        whatDatItIs.setBounds(100,10, 740, 60);
        whatDatItIs.setHorizontalTextPosition(JLabel.CENTER);
        whatDatItIs.setVerticalTextPosition(JLabel.CENTER);
        whatDatItIs.setHorizontalAlignment(JLabel.CENTER);
        whatDatItIs.setVerticalAlignment(JLabel.TOP);
        whatDatItIs.setFont(new Font("Verdana", Font.PLAIN, 20));

        additionalInfo.setBounds(100, 80, 740, 70);
        additionalInfo.setHorizontalTextPosition(JLabel.CENTER);
        additionalInfo.setVerticalTextPosition(JLabel.CENTER);
        additionalInfo.setHorizontalAlignment(JLabel.CENTER);
        additionalInfo.setVerticalAlignment(JLabel.BOTTOM);
        additionalInfo.setFont(new Font("Verdana", Font.PLAIN, 15));

        arrowButtonSetUp();

        topInfoHolder.add(whatDatItIs);
        topInfoHolder.add(additionalInfo);
        topInfoHolder.add(yesterday);
        topInfoHolder.add(tomorrow);
        mainFrame.add(topInfoHolder);
    }

    public void arrowButtonSetUp() {
        yesterday.setBounds(0,0, 120, 40);
        yesterday.setText("<-Yesterday");
        yesterday.setFocusable(false);
        yesterday.addActionListener(this::goYesterday);

        tomorrow.setBounds(820,0, 120, 40);
        tomorrow.setText("Tomorrow->");
        tomorrow.setFocusable(false);
        tomorrow.addActionListener(this::goTomorrow);
    }

    public void goYesterday(ActionEvent e) {
        if (e.getSource() == yesterday) {
            nicheDate.modifyNicheDay("yest");
            isThatDayNiche(nicheDate.getNicheDay());
        }
    }

    public void goTomorrow(ActionEvent e) {
        if (e.getSource() == tomorrow) {
            nicheDate.modifyNicheDay("tom");
            isThatDayNiche(nicheDate.getNicheDay());
        }
    }





    public void addDate(ActionEvent e) {
        if (e.getSource() == addButton) {
            String month = recordedMonth.getText();
            int monthNum = Integer.parseInt(month);
            String day = recordedDay.getText();
            int dayNum = Integer.parseInt(day);
            String name = nicheName.getText();
            String note = nicheDescription.getText();
            NicheHoliday holiday = new NicheHoliday(monthNum, dayNum, name, note);
            loadedGlossary.addToGlossary(holiday);
            recordedMonth.setText(null);
            recordedDay.setText(null);
            nicheName.setText(null);
            nicheDescription.setText(null);
            isThatDayNiche(nicheDate.getNicheDay());
        }
    }




    public void returnToToday(ActionEvent e) {
        if (e.getSource() == goToToday) {
            System.out.println("You are back");
            isTodayNiche();
            nicheDate.modifyNicheDay("today");
        }

    }


    public void letsGoToDay(ActionEvent e) {
        if (e.getSource() == goToDay) {
            System.out.println("here we go");
            String command = desiredDate.getText();
            nicheDate.modifyNicheDay(command);
            isThatDayNiche(nicheDate.getNicheDay());
        }

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
                whatDatItIs.setText("<html>" + date + " is <br>" + nicheholiday.getName() + "</html>");
                additionalInfo.setText("<html> Something about that day: <br>" + nicheholiday.getNote() + "</html>");
                return;
            }
        }
        whatDatItIs.setText("what a terrible luck are you having? ");
        additionalInfo.setText(date + " is not niche");
    }

    public void isTodayNiche() {
        for (NicheHoliday nicheholiday : loadedGlossary.getHolidays()) {
            if (nicheholiday.isToday()) {
                whatDatItIs.setText("<html> Wow, today is " + LocalDate.now()
                        + " <br>" + nicheholiday.getName() + "</html>");
                additionalInfo.setText("<html> Please note that <br>" + nicheholiday.getNote() + "</html>");
                return;
            }
        }
        whatDatItIs.setText("Sadly, today is boring, no niche holiday for today.");
        additionalInfo.setText("Maybe come back to check after a week?");
    }



    /*
     *REQUIRES: year, month are consistent with the date.
     *EFFECTS: Print a date in their respective month in a calendar view.
     */
    private void printCalendar(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        YearMonth yearMonth = YearMonth.of(year, month);

        // Print the month and year on the top
        monthYearInfo = yearMonth.getMonth() + " " + year;

        // Print the first row that represent week
        dayOfWeekName = " Su Mo Tu We Th Fr Sa";

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
    private void handleInteger(int i, LocalDate date, int startDay) {
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
