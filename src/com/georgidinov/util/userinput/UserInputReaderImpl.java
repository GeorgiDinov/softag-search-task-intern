package com.georgidinov.util.userinput;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputReaderImpl implements UserInputReader {

    //== fields ==
    private Scanner scanner;


    //== constructors ==
    public UserInputReaderImpl() {
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
                return new UserInputImpl(stringPath, stringToSearchFor);
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }//end of method getUserInput

}//end of class UserInputReader
