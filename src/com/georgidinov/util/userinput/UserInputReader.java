package com.georgidinov.util.userinput;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputReader {

    //== fields ==
    private Scanner scanner;


    //== constructors ==
    public UserInputReader() {
        this.scanner = new Scanner(System.in);
    }//end of constructor


    //== public methods ==
    public UserInput getUserInput() {
        while (true) {
            try {
                System.out.print("Enter path: ");
                String stringPath = scanner.nextLine();
                System.out.print("Enter string to search for: ");
                String stringToSearchFor = scanner.nextLine();
                return new UserInput(stringPath, stringToSearchFor);
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }//end of method getUserInput

    private String readLine() {
        return scanner.nextLine();
    }

}//end of class UserInputReader
