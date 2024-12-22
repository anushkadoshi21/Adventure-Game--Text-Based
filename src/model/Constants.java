package model;

import java.util.HashMap;
import java.util.Map;

//Constants class has all the constants and outputs related to our storyline.
//Idea behind creating this class explicitly is to reduce the coupling of model class with storyline.
//Any future iterations to change storyline or adding levels to games, adding nouns/ verbs will only need
//to change Constants class attributes
public final class Constants {
    private Constants() {};
    public static final String[] commands = {"quit", "help", "hint"};
    public static final String[] introductionScenario = new String[]{"You’ve just arrived outside the airport. \n" +
            "Your taxi pulls up to the curb as you frantically check the time. \n" +
            "You’re running late! You need to grab your luggage, check your ticket and \n" +
            "rush toward the terminal entrance, hurry! ",
            "You locate the check-in counter and see a long queue of anxious passengers.\n" +
                    " You notice an attendant dropping off the luggage and " +
                    "handing out boarding passes. \n" +
                    "Time is running out; decide on your next action",
            "The security line is long, and you can feel the pressure. \n" +
                    "You’re required to remove your electronics, " +
                    "and place all your belongings in the tray.\n" +
                    " The scanner looms ahead, and you spot an officer watching closely.",
            "You’ve made it through security, but now the sprawling terminal is ahead of you. \n" +
                    "You notice signs pointing towards different gates, " +
                    "you need to fetch your gate-number on the boarding-pass, \n" +
                    "locate the gate and board the flight"};

    //lists the nous used at various levels
    public static final String[][] nouns = {{"luggage", "ticket", "terminal"},
            {"queue", "luggage", "boarding-pass"},
            {"electronics", "tray", "scanner", "officer"},
            {"boarding-pass", "gate", "ticket", "flight"}};

    //lists the nous used at various levels
    public static final String[][] verbs = {{"walk", "check", "rush", "read", "grab", "carry"},
            {"wait", "print", "join", "drop", "check-in"},
            {"remove", "place", "walk", "answer", "talk", "take-off", "pass-through", "keep"},
            {"check", "read", "locate", "find", "show", "display", "board"}};

    //nouns and associated verbs at various levels.
    //eg : Luggage noun is used at level 1 & 2. Both levels have 2 different verbs each associated which could be used
    public static final Map nounVerbWithLevels = new HashMap<>(Map.ofEntries(
            Map.entry("luggage", new String[][]{{"grab", "carry"}, {"drop", "check-in"}}),
            Map.entry("ticket", new String[][]{{"read", "check"}, {}, {}, {"show", "display"}}),
            Map.entry("terminal", new String[][]{{"rush", "walk"}, {}}),
            Map.entry("boarding-pass", new String[][]{{}, {"print"}, {}, {"check", "read"}}),
            Map.entry("queue", new String[][]{{}, {"wait", "join"}}),
            Map.entry("electronics", new String[][]{{}, {}, {"remove", "take-off"}}),
            Map.entry("tray", new String[][]{{}, {}, {"place", "keep"}}),
            Map.entry("scanner", new String[][]{{}, {}, {"walk", "pass-through"}}),
            Map.entry("officer", new String[][]{{}, {}, {"talk", "answer"}}),
            Map.entry("flight", new String[][]{{}, {}, {}, {"board"}}),
            Map.entry("gate", new String[][]{{}, {}, {}, {"locate", "find"}})
    ));

    //Each map is noun and probable sentence with verb which could be used for that level.
    //These values are used as to serve hints if user is not able to form a sentence
    public static final Map[] nounToVerbAssociation = {new HashMap<>(Map.of("terminal", "Walk or rush to terminal ?",
            "ticket", "Read or check the ticket ?",
            "luggage", "Carry or grab your luggage ?")),
            new HashMap<>(Map.of(
                    "luggage", "Drop or check-in your luggage?",
                    "queue", "Wait in the queue or Join the queue?",
                    "boarding-pass", "print the boarding-pass?"
            )),
            new HashMap<>(Map.of(
                    "electronics", "Take-off or remove electronics",
                    "tray", "Place or keep items in the tray?",
                    "scanner", "Pass-through or walk into the scanner",
                    "officer", "Talk to officer or answer his questions"
            )),
            new HashMap<>(Map.of(
                    "boarding-pass", "check or read boarding-pass for gate-number",
                    "gate", "Locate or find the gate",
                    "flight", "Board the flight",
                    "ticket", "Show or display your ticket"
            ))
    };

    //Each map is verb and probable sentence with noun which could be used for that level
    //These values are used as to serve hints if user is not able to form a sentence
    public static final Map[] verbToNounAssociation = {new HashMap<>(Map.of("walk", "Walk or rush to terminal ?",
            "rush", "Walk or rush to terminal ?",
            "carry", "Carry your luggage ?",
            "grab", "Carry your luggage ?",
            "read", "Read or check the ticket ?",
            "check", "Read or check the ticket ?")),
            new HashMap<>(Map.of(
                    "wait", "Wait in the queue or Join the queue?",
                    "join", "Wait in the queue or Join the queue?",
                    "print", "print the boarding-pass"
            )),
            new HashMap<>(Map.of(
                    "take-off", "Take-off or remove electronics",
                    "remove", "Take-off or remove electronics",
                    "place", "Place or keep items in the tray?",
                    "keep", "Place or keep items in the tray?",
                    "answer", "Talk to officer or answer his questions",
                    "talk", "Talk to officer or answer his questions",
                    "pass-through", "Pass-through or walk into the scanner",
                    "walk", "Pass-through or walk into the scanner"
            )),
            new HashMap<>(Map.of(
                    "check", "check or read boarding-pass for gate-number",
                    "read", "check or read boarding-pass for gate-number",
                    "locate", "Locate or find the gate",
                    "find", "Locate or find the gate",
                    "show", "Show or display your ticket",
                    "diaplay", "Show or display your ticket",
                    "board", "Board the flight"
            ))
    };

    //Every index here is that level's hint with all sublevels mentioned in order.
    //eg: for level 1, 3 sublevels are defined & to be performed. Carry luggage, check ticket & walk to terminal
    public static String[] hints = new String[]{"To make it to the entrance you need to carry your luggage, \n" +
            "check your ticket and walk to the terminal!",
            "Maybe join the queue asap, drop away your luggage at the counter \nand print your boarding pass ?",
            "You may first need to remove all your electronics  \nand place it in tray, " +
                    "then walk through the scanner also you see a strict officer asking questions",
            "You need to find your gate on the terminal by checking your gate-number on the boarding-pass. \n" +
                    "Then display your ticket and finally, board the flight"
    };

    //2-D array to give sublevel hints giving probable sentence formation.
    //This is used when user forgets to execute or act on a sublevel
    public static String[][] sublevelhints = new String[][]{{"Are you forgetting your lugguage?",
            "Maybe you should check for details on ticket before heading to terminal",
            "Let's get going by starting the walk towards the terminal!"},
            {"Join the queue first! Hurry!!", "Are you forgetting to check-in your luggage?", "How about printing your boarding-pass?"},
            {"Oops, are you forgetting to remove your electronics?",
                    "Items needs to be placed in the tray!",
                    "You cannot proceed until you pass through the scanner",
                    "Strict officer won't let you proceed until you talk with him :("},
            {"First, Read your boarding-pass to see the gate-number","Next you need to locate the gate on the terminal",
            "You first need to display your ticket before boarding the flight","Board the flight..final call!! Rush rush"}};

}
