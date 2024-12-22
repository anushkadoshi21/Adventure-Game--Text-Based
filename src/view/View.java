package view;
/*
* This View class is all about displaying the outputs to the user
* */

public class View {

    // First output to be displayed as soon as object of this class is made
    public View() {
        System.out.println("Welcome to Text Adventure Game ! \nPress any key to start your adventure...");
    }

    //Displays commands which can be used throught the game
    public void displayCommands() {
        System.out.println("Quit : Quit the game and miss your flight");
        System.out.println("Help : Displays this command panel");
        System.out.println("Hint : Get Hint for your current situation");

    }

    //Displays Welcome message and gives background of what the mission to
    // the user before (s)he starts the game
    public void displayWelcomeMessage() {
        System.out.println("Welcome to Airport Adventure! \nYour mission is to navigate through" +
                " the bustling airport and board your flight on time. \nBe mindful of your surroundings" +
                " and make wise choices! \nType 'Help' at any time for a list of commands.\nGood luck");
    }

    //Concludes the game with appropriate message depending on whether the player was
    // successful or not using success as parameter
    public void displayEndMessage(int success) {
        if (success == 0) {
            System.out.println("Congratulations! \nYou’ve successfully navigated the airport and boarded your plane. \n" +
                    "Thank you for playing Airport Adventure! " +
                    "\nBuckle up your seat belt and Safe travels!" +
                    "\nBON VOYAGE!!!");
        } else {
            System.out.println("Oops :(");
            System.out.println("You quit and missed your flight. \nSee you on your next adventure.");
            System.out.println("GoodBye !");
        }
    }

    //Displays output guided by controller to pass on appropriate message
    // to the player using output as a parameter
    public void displayOutput(String output) {
        System.out.println(output);
    }

}
