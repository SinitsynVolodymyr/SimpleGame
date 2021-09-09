package com;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    static final String helpCommand = "help";

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        int countArgs = args.length;
        if (countArgs>=3&&countArgs%2!=0){

        }

        HMAC hmac = new HMAC(args);

        System.out.println("HMAC: " + hmac.getHashingMoveAsString());
        System.out.println("Available moves:");
        int index = 0;
        for (String argTmp: args){
            System.out.println(++index+" - "+argTmp);
        }
        System.out.println("? - " +helpCommand);
        System.out.println("Enter your move:");

        Scanner sc = new Scanner(System.in);
        String userRequest = inputUserRequest(sc, args);
        Model.isUserWinner(userRequest, hmac, args);


    }


    private static String inputUserRequest(Scanner sc, String[] moves){
        String answer = "";
        while(!(sc.hasNext() &&
                (Arrays.asList(moves).contains(answer = sc.next())))){
            if (answer.equals(helpCommand)){
                //TODO
                System.out.println("Your help)");
            }else{
                System.out.println("Incorrect move or command. Please repeat.");
            }
        }
        return answer;
    }


}
