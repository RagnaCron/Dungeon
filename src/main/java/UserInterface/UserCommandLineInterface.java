package UserInterface;

import java.util.Scanner;

/**
 * The UserCommandLineInterface is the API to the CommandLine when running the Application.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class UserCommandLineInterface {

    /**
     * This method will get you some input from the user, that he entered
     * over the CommandLine.
     * @param prompt The prompt before the user input.
     * @return Returns a String. The user input over the CommandLine.
     */
    public String getInput(String prompt){
        Scanner scanner = new Scanner(System.in);
        print(prompt, "");
        return scanner.nextLine();
    }

    /**
     * Prints a nice message to the STDOUT.
     * @param message The message that you want to display to the user over the CommandLine.
     * @param delimiter The end of line delimiter, something like "\n" or " " whatever you like.
     */
    public void print(String message, String delimiter) {
        System.out.print(message + delimiter);
    }

    /**
     * Prints a nice message to the STDOUT.
     * @param message Takes a String. The message that you want to display to the user over the CommandLine.
     */
    public void println(String message) {
        print(message, "\n");
    }

}
