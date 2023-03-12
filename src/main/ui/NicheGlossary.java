package ui;

import model.NicheHoliday;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

/**
 The glossary for all Niche holidays recorded in this program
 */
public class NicheGlossary implements Writable {
    private NicheHoliday[] holidays;

    /*
     * EFFECTS: construct an NicheGlossary with same as NICHE_GLOSSARY
     */
    public NicheGlossary() {
        //holidays = NICHE_GLOSSARY;
        holidays = new NicheHoliday[] {};
    }

    /*
     * REQUIRE: toAdd must be a functioning NicheHoliday
     * MODIFY: This
     * Effects: add the inputted NicheHoliday into the NicheGlossary
     */
    public void addToGlossary(NicheHoliday newHoliday) {
        NicheHoliday[] newHolidays = new NicheHoliday[holidays.length + 1];

        for (int i = 0; i < holidays.length; i++) {
            newHolidays[i] = holidays[i];
        }

        newHolidays[holidays.length] = newHoliday;

        holidays = newHolidays;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("holidays", holidaysToJson());
        return json;
    }

    // EFFECTS: returns Holidays in this Holiday Glossary as a JSON array
    private JSONArray holidaysToJson() {
        JSONArray jsonArray = new JSONArray();

        for (NicheHoliday h : holidays) {
            jsonArray.put(h.toJson());
        }

        return jsonArray;
    }

    /*
     * Effects: return nicheGlossary
     */
    public NicheHoliday[] getHolidays() {
        return holidays;
    }


    public static final NicheHoliday[] NICHE_GLOSSARY =  {
            // January
            new NicheHoliday(1, 1, "Polar Bear Plunge Day",
                    "today people jump into a cold water body just like polar bears"),
            new NicheHoliday(1, 2, "Science Fiction Day",
                    "today we reach out to the future and tomorrow. And also, Space is extra cool"),
            new NicheHoliday(1, 3, "Festival of Sleep Day",
                    "today we shall sleep on a justified course"),
            new NicheHoliday(1, 4, "Trivia Day",
                     "this is the day you learn a bunch of useless stuff and act pedantic, perfect day tp "
                             + "use this program for help"),
            new NicheHoliday(1,5, "Bird Day",
                    "go watch some birds, I guess?"),
            new NicheHoliday(1,8, "Earth's Rotation Day",
                    "today is the day you slap the flat-earthers on their face"),

            // February
            new NicheHoliday(2, 11, "Get Out Your Guitar Day",
                    "Yes please, you are supposed to feel better"),

            new NicheHoliday(2, 22, "Crime Victims Day in Europe",
                    "revenge is often not the right curse of justice"),

            new NicheHoliday(2, 23, "International Dog Biscuit Appreciation Day",
                    "feeding your dog baked dog biscuit is a great idea!"),

           // March
            new NicheHoliday(3, 4, "Marching Music Day",
                    "I don't know, marching and music does not sound like a good combination"),
            new NicheHoliday(3,19, "Let's Laugh Day",
                    "be sure to watch some stupid sitcoms today"),
            new NicheHoliday(3, 20, "Proposal Day",
                    "the proposal is an serious decision to make, please proceed with caution"),
            new NicheHoliday(3,23,"Near Miss Day",
                    "in March 23, 1989, the Asclepius asteroid almost hit earth."
                            + "you should celebrate today by celebrating life and all the second "
                            + "chances you have ever been given."),
            new NicheHoliday(3, 27, "Quirky Country Music Song Titles Day",
                    "Go to your play list and pick your least favourite song"),

            // April
            new NicheHoliday(4, 1, "Fun at Work Day",
                    "work is almost never fun, but I guess you will have to pretend it is fun"),
            new NicheHoliday(4, 3, "World Party Day",
                    "the use of the word, party, to refer to a gathering "
                            + "where people have a good time dates all the way back to 1922?"),
            new NicheHoliday(4, 4, "Tell a Lie Day",
                    "Oh come on, April's Fool just ended 2 days ago. "
                            + "But if you missed April's fool, now is your chance !"),
            new NicheHoliday(4, 11, "Be Kind to Lawyers Day",
                    "you only need to be kind to lawyers while your case is not end"),
            new NicheHoliday(4,17, "Haiku Poetry Day",
                    "go here to learn writing your own Haiku from Grammarly"
                              + " https://www.grammarly.com/blog/how-to-write-haiku/"),
            new NicheHoliday(4,23, "Lover's Day",
                    "We already have Valentine's day, this is entirely redundant"),
            new NicheHoliday(4,27, "Take Our Daughters and Sons to Work Day",
                    "today is made into a holiday to “encourages girls and boys to dream "
                            + "without gender limitations and to think imaginatively about their family, "
                            + "work and community lives”."),

            // May
            new NicheHoliday(5,1,"Batman Day",
                    "World's greatest detective the Batman had his debut on May 1939. he is sometimes "
                            + "known as The Dark Knight and the Caped Crusader. And no one knows his real name is "
                            + "just Bruce Wayne"),
            new NicheHoliday(5,4, "May the Force/Star Wars Day",
                    "may the Force be with you"),
            new NicheHoliday(5, 20, "World Bee Day",
                    "They can really get everywhere and be annoying"),
            new NicheHoliday(5, 25, "World Towel Day",
                    "Grab Your Towel and Don't panic"),
            new NicheHoliday(5, 26, "World Lindy Hop Day",
                    "Lindy Hop originated in Harlem, New York City in the 1920s and 1930s. "
                            + "The day commemorates Frankie Manning, "
                            + "who some consider one of the creators of the dance form."),


            // June
            new NicheHoliday(6,1,"Say Something Nice Day",
                    "if you can’t say something nice, don’t say anything at all."),
            new NicheHoliday(6, 18, "International Panic Day",
                    "that the word panic is derived from the Greek word associated with "
                            + "the Greek shepherd god Pan? According to mythology, Pan amused himself by striking "
                            + "fear among travelers in the woods."),
            new NicheHoliday(6, 22, "Onion Ring Day",
                    "I would rather get fries then onion rings"),

            // July
            new NicheHoliday(7, 2, "I Forgot Day",
                    "I forgot what I am about to put here"),
            new NicheHoliday(7, 12, "Simplicity Day",
                    "For the sake of simplicity, I will not include anything extra here"),

            // August
            new NicheHoliday(8,2, "Ice Cream Sandwich Day",
                    "Tell you are secret, ice cream sandwich is delicious"),
            new NicheHoliday(8,16,"Tell a Joke Day",
                    "what happens if you hit Dwayne Johnson's ass? You hit the ROCK BOTTOM!"),
            new NicheHoliday(8,19, "World Photo Day",
                    "some 12 Hasselblad cameras were left on the Moon by various missions?"
                            + " Some suggest that this was done in order to compensate for the weight of "
                            + "rock and soil samples the astronauts brought back to Earth"),

            // September
            new NicheHoliday(9,17, "International Country Music Day",
                    "Stylistically, country music usually includes harmonies that are "
                            + "guided by instruments like the banjo, guitars and the harmonica."),
            new NicheHoliday(9,25, "Comic Book Day",
                    "nowadays, the very subject of comic book itself can be regarded as an area of niche. "
                            + "do you know the that the Incredible Hulk, was originally drawn as a gray by its "
                            + "creator Stan Lee? However, because of printing issues, Marvel, the publisher of the "
                            + "comic decided to change him to green."),

            // October
            new NicheHoliday(10,1,"International Coffee Day",
                    " Sultan Murad IV of the Ottoman Empire hated coffee so much that he made "
                           + "coffee drinking an offense punishable by death. In England, "
                           + "King Charles II fearing plots hatched over cups of coffee "
                           + "ordered all coffee houses shut."),
            new NicheHoliday(10,16, "Dictionary Day",
                    "No one still uses a dictionary nowadays, so this holiday is dumb"),
            new NicheHoliday(10,31, "Magic Day",
                    "historians believe that the first magic performance "
                            + "was held in 2700 BCE by an Egyptian magician called Dedi?"),


            // November
            new NicheHoliday(11, 9, "Chaos Never Dies Day",
                    " chaos is part of life and that it will never die. So instead of getting hassled by "
                            + "it, just take a deep breath and let go of "
                            + " things that create chaos in your life on this day."),
            new NicheHoliday(11,14, "Pickle Day",
                    "let's all be PICKLE RIIIIIIK for a day"),
            new NicheHoliday(11, 15, "Clean Out Your Refrigerator Day",
                    "got a messy refrigerator? Now is time for some cleanning"),



            // December
            new NicheHoliday(12, 15, "Underdog Day",
                    "the opposite of an underdog is a top dog and in if an"
                              + " underdog wins in a competition, the outcome of the event is known as an upset."),
            new NicheHoliday(12,25,"Grav Mass Day or Newtonmas",
                    "instead of marry christmas, you should say "
                            + "may the Force be proportional to your acceleration")

    };


}
