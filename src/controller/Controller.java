package controller;

import java.util.Arrays;
import java.util.Scanner;

import model.*;
import view.*;

/**
* Controller class works as a brain and co-ordinates between model, parser & view classes.
* It is the driving class to make decisions communicate user actions with game model
*  and give result to view from model
* while acting as an intermidiator
*/

public class Controller {
    // Declaring class attributes
    Scanner userInput;
    Model model;
    View view;
    InputParser parser;
    int level;
    String input;

    //Default constructor which takes in model, parser & view objects
    //Since Controller is initialized at the very start, we set current level of the game=0
    //class attributes are assigned objects of respective types.
    public Controller(Model model, View view, InputParser parser) {
        this.level = 0;
        this.model = model;
        this.view = view;
        this.parser = parser;
        this.userInput = new Scanner(System.in);
    }

    //Main method to start the actual game
    public void runGame() {
        input = userInput.nextLine().toLowerCase();
        view.displayWelcomeMessage(); // Kick off the game with Welcome message
        view.displayCommands(); // Inform user about commands
        view.displayOutput(model.getIntroductionScenario(this.level)); //start the game with initial introduction scenario
        int success = 0; // set to 0 since user has not yet quit the game
        boolean nextLevelinfo = false; //will only become true when all sublevels of currentlevel are completed
        while (true) { // keeps on running until user quits or game is completed successfully
            input = userInput.nextLine().toLowerCase(); //take input and convert it to lowercase to avoid case issues in logic
            if (checkCommand()) { //if the input is one of the commands used in game enter the if condition
                success = executeCommand(); //simply execute the command
            } else {
                parser.setLevel(this.level); //set parser level to current game level
                parser.setVerbs(); //set list of verbs for current level
                parser.setNouns(); //set list of nouns for current level
                String[] detectedNounandVerb = parser.parseInput(input); //search for noun (0th index) & verb (1st index) in user's input
                String[] output = model.getOutput(detectedNounandVerb, this.level); //process the noun & verb found and getOutput from model
                if (output.length == 2) {
                    int i = Integer.parseInt(output[1]); // either 0 or 1, depending on the sublevel the user is at
                    this.level = this.level + i; // increment the level if all sublevels are done for current level
                    if (i == 1) { // all sublevels done
                        model.setSublevel(0); //set sublevel to 0 as we proceed to next level
                        nextLevelinfo = true; //since we need to display what's next
                    }
                    if (this.level==model.getTotalLevels()){ //all levels done, user has completed the game successfully
                        view.displayEndMessage(0); //hence display final success message & goodbye!
                        break; // since game is completed, we no longer require user's input
                    }
                }
                view.displayOutput(output[0]); // irrespective of outcome we need to show user output for his current input
                parser.setLevel(this.level); // set new level if current level is incremented
                if (nextLevelinfo) { // this is true when we advance to next level
                    view.displayOutput(model.getIntroductionScenario(this.level)); //therefore, show next level info
                    nextLevelinfo = false; //turned false so that we complete sublevels first before getting to know the next
                }
            }
            if (success != 0) {  // means something went wrong , user quit or
                break;
            }
        }
    }

    //method to check if the input entered is a valid command
    public boolean checkCommand() {
        String[] commands = model.getCommands();
        return Arrays.asList(commands).contains(input);
    }

    //Take appropriate action based on defined game commands
    //returns 0 if command executed successfully and -1 when user quits
    public int executeCommand() {
        int returnVal = 0;
        switch (input) {
            case "quit":
                view.displayEndMessage(1);
                returnVal = -1;
                break;
//                break;
            case "help":
                view.displayCommands();
                break;
            case "hint":
                view.displayOutput(model.getHint(this.level));
                break;
            default:
                returnVal = -1;
                break;
        }
        return returnVal;
    }
}
