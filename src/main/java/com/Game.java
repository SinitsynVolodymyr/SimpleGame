package com;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Game {

    static final String helpCommand = "help";

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        int countArgs = args.length;
        if (countArgs >= 3 && countArgs % 2 != 0) {

            HashSet<String> argSet = new HashSet<>(Arrays.asList(args));
            if (argSet.size()<args.length){
                    System.out.println("Your arguments are repeated");
                    System.exit(1);
            }

            HMAC hmac = new HMAC(args);
            System.out.println("HMAC Algorithm: "+HMAC.CODING);
            System.out.println("HMAC: " + hmac.getHashingMoveAsString().toUpperCase(Locale.ROOT));
            System.out.println("Available moves:");
            int index = 0;
            for (String argTmp : args) {
                System.out.println(++index + " - " + argTmp);
            }
            System.out.println("? - " + helpCommand);
            System.out.println("Enter your move:");

            Scanner sc = new Scanner(System.in);
            int userRequest = inputUserRequest(sc, args);
            int compMoveId = -1;
            for (int i = 0; i<args.length; i++){
                if (args[i].equals(hmac.getMove())){compMoveId = i;}
            }
            int winResult = Model.isUserWinner(userRequest, compMoveId, args);
            System.out.println("Computer move: " + hmac.getMove());

            if (winResult == 0) System.out.println("DRAW!");
            else if (winResult > 0) System.out.println("You win!");
            else System.out.println("Computer win!");

            System.out.println("HMAC key: " + hmac.getKeyAsString().toUpperCase(Locale.ROOT));

        } else {
            System.out.println("Incorrect input. Please input the odd and >=3 amount strings");
        }
    }


    private static int inputUserRequest(Scanner sc, String[] moves) {

        while (true) {
            String input = sc.next();
            if (input.equals("?")) {
                System.out.println(HelpTable.generateHelpTable(moves));
            } else {
                try {
                    int userValue = Integer.parseInt(input);
                    if (userValue <= moves.length && userValue > 0)
                        return userValue - 1;
                    else {
                        System.out.println("Input a number value between [1 - " + moves.length + "]");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input a number or '?' please.");
                }
            }
        }
    }

}
