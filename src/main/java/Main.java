import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        iconSet field = new iconSet();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        int turn = 2;
        boolean wrong;
        String[][] emptyArray = {
            {"-", "-", "-"}, //a
            {"-", "-", "-"}, //b
            {"-", "-", "-"}  //c
        };

        field.printWelcome();
        while(run){
            if(turn == 1){
                turn = 2;
            } else if (turn == 2) {
                turn = 1;
            }

            run = field.setField(turn);
            if(run){
                run = field.wonScan();

                if(!run){
                    do {
                        System.out.println("Would you like to play again y/n");
                        String input = scanner.nextLine();

                        if (input.equals("y")) {
                            field.setFieldArray(emptyArray);
                            run = true;
                            wrong = false;
                        } else if (input.equals("n")) {
                            field.printExit();
                            wrong = false;
                        } else {
                            System.out.println("ERROR: invalid input");
                            wrong = true;
                        }
                    }while (wrong);
                }
            }
        }
    }
}