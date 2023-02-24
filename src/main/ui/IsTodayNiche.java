package ui;

import model.NicheHoliday;
import static ui.NicheGlossary.NICHE_GLOSSARY;


public class IsTodayNiche {

    // Check today's nicheness and report.
    public static void main(String[] args) {
        for (NicheHoliday nicheholiday : NICHE_GLOSSARY) {
            if (nicheholiday.isToday()) {
                System.out.println("Wow, today is " + nicheholiday.getName());
                System.out.println("Please note that " + nicheholiday.getNote());
                return;
            }
        }
        System.out.println("Sadly, today is boring, no niche holiday for today");
    }
}
