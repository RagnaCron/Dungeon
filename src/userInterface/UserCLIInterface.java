package userInterface;

import java.util.Scanner;

public class UserCLIInterface {

    public String getInput(String prompt){
        Scanner scanner = new Scanner(System.in);
        print(prompt, "");
        return scanner.nextLine();
    }

    public void print(String message, String delimiter) {
        System.out.print(message + delimiter);
    }

}
