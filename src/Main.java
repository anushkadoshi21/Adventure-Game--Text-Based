import model.*;
import controller.*;
import view.*;

/*
* This class & method - "Main" is the starting point of the execution.
* It instantiates model, view, parser & controller objects passes them as parameters in controller constructor.
* And finally, starts the actual game execution using runGame() method of controller.
* */

public class Main{

    public static void main(String[] args) {
        // Initialize model, view, parser & controller. Trigger the run method to start execution
        Model model = new Model();
        View view = new View();
        InputParser parser= new InputParser(model);
        Controller control = new Controller(model, view,parser);
        control.runGame();

    }

}