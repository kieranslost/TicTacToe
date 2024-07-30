import java.util.Scanner;

public class iconSet {
    Main main = new Main();
    Scanner scanner = new Scanner(System.in);
    int digitAsInt;
    private String[][] fieldArray = {
        {"-", "-", "-"}, //a
        {"-", "-", "-"}, //b
        {"-", "-", "-"}  //c
    };
    String[] lettersArray = {"a: ", "b: ", "c: "};
    String turnLetter;

    public boolean setField(int turn){

        if(turn == 1){
            turnLetter = "X";
        } else if (turn == 2) {
            turnLetter = "O";
        } else {
            System.out.println("Error, turn not set");
            return false;
        }

        System.out.println("Geben sie ihr Kästchen ein: ");
        String input = scanner.nextLine();

        if(input.equals("quit")){
            printExit();
            return false;
        }

        if(input.length() != 2){
            System.out.println("Geben sie eine Gültige Zahl ein");
            getTable();
            setField(turn);
            return true;
        }

        //gets the 1. and 2. digits
        String digit = input.substring(0, 1);
        String letter = input.substring(1);

        //turns the 1. digit from a String to an Int
        try{
            digitAsInt = Integer.parseInt(digit);

        } catch (Exception e){
            System.out.println("Geben sie eine Gültige Zahl ein");
            getTable();
            setField(turn);
            return true;
        }
        int newDigit = --digitAsInt;

        //Checks if the int is out of bounds
        if(newDigit >= fieldArray.length){
            System.out.println("Geben sie eine Gültige Zahl ein");
            getTable();
            setField(turn);
            return true;
        }

        //Switch case to enter the data into the correct row and using the "newDigit" as to find the correct colum
        switch (letter) {
            case "a" -> {
                //If this space has already been used, the user has to enter new Coordinates
                if (fieldArray[0][newDigit].equals("-")) {
                    fieldArray[0][newDigit] = turnLetter;
                } else {
                    System.out.println("Benutzen sie eine noch nicht zuvor benutztes Kästchen");
                    setField(turn);
                    return true;
                }
            }
            case "b" -> {
                //If this space has already been used, the user has to enter new Coordinates
                if (fieldArray[1][newDigit].equals("-")) {
                    fieldArray[1][newDigit] = turnLetter;
                } else {
                    System.out.println("Benutzen sie eine noch nicht zuvor benutztes Kästchen");
                    setField(turn);
                    return true;
                }
            }
            case "c" -> {
                //If this space has already been used, the user has to enter new Coordinates
                if (fieldArray[2][newDigit].equals("-")) {
                    fieldArray[2][newDigit] = turnLetter;
                } else {
                    System.out.println("Benutzen sie eine noch nicht zuvor benutztes Kästchen");
                    setField(turn);
                    return true;
                }
            }
            default -> { System.out.println("Geben sie eine Gültige Zahl ein");
                getTable();
                setField(turn);
                return true;
            }
        }

        getTable();
        return true;
    }

    //Checks if one of the Players Won
    public boolean wonScan(){

        String[][] array = getFieldArray();

        //Goes threw all possible winning conditions to see if one of the Players Won
        for(int x = 1; x <= 2; x++){
            if(x == 1){
                turnLetter = "X";
            } else if (x == 2) {
                turnLetter = "O";
            } else {
                System.out.println("System Error: turn not set");
                return false;
            }

            if(array[0][0].equals(turnLetter) && array[0][1].equals(turnLetter) && array[0][2].equals(turnLetter)){
                printWinner(x);
                return false;
            } else if(array[1][0].equals(turnLetter) && array[1][1].equals(turnLetter) && array[1][2].equals(turnLetter)){
                printWinner(x);
                return false;
            } else if(array[2][0].equals(turnLetter) && array[2][1].equals(turnLetter) && array[2][2].equals(turnLetter)){
                printWinner(x);
                return false;
            } else if(array[0][0].equals(turnLetter) && array[1][1].equals(turnLetter) && array[2][2].equals(turnLetter)){
                printWinner(x);
                return false;
            } else if(array[2][0].equals(turnLetter) && array[1][1].equals(turnLetter) && array[0][2].equals(turnLetter)){
                printWinner(x);
                return false;
            } else if(array[0][0].equals(turnLetter) && array[1][0].equals(turnLetter) && array[2][0].equals(turnLetter)){
                printWinner(x);
                return false;
            } else if(array[0][1].equals(turnLetter) && array[1][1].equals(turnLetter) && array[2][1].equals(turnLetter)){
                printWinner(x);
                return false;
            } else if(array[0][2].equals(turnLetter) && array[1][2].equals(turnLetter) && array[2][2].equals(turnLetter)){
                printWinner(x);
                return false;
            }
        }

        // Checks if the game has ended in a draw
        for(int n = 0; n <= 2; n++){
            for(int i = 0; i <= 2; i++){
                // If even one of the default values are still present it goes back to the main class
                if(array[n][i] == "-"){
                    return true;
                }
            }
        }

        // This only activates when there's a draw
        printDraw();
        return false;
    }

    //Prints out the Player that won
    public void printWinner(int winner){
        System.out.println("Player " + winner + " won");
    }

    public void printDraw(){
        System.out.println("The game ended in a draw");
    }

    //Prints out a nice Welcoming message and tells the User how to blay
    public void printWelcome(){
        System.out.println("Das ist ein TicTacToe spiel");
        System.out.println("Sie müssen bei den eingaben einer dieser Koordinaten eingeben:");
        System.out.println("   1 2 3");
        for (int i = 0; i < fieldArray.length; i++) {
            System.out.print(lettersArray[i]);
            for (int j = 0; j < fieldArray[i].length; j++) {
                System.out.print(fieldArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Korrekte Inputs sehen beispielerweise so aus: '2a' ");
        System.out.println("Wenn sie 3 in einer Linie haben, haben sie gewonnen");
        System.out.println("Sie können das System mit 'quit' beenden");
        System.out.println("Viel Spass");
    }

    //Prints out the Goodbye message
    public void printExit(){
        System.out.println("Goodbye :)");
    }

    public void getTable(){
        //Shows the TicTacToe table for the User to see
        System.out.println("   1 2 3");
        for (int i = 0; i < fieldArray.length; i++) {
            System.out.print(lettersArray[i]);
            for (int j = 0; j < fieldArray[i].length; j++) {
                System.out.print(fieldArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    //only Getters and Setters beyond this point
    public String[][] getFieldArray() {
        return fieldArray;
    }

    public void setFieldArray(String[][] fieldArray) {
        this.fieldArray = fieldArray;
    }
}
