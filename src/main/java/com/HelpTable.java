package com;

import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;
import java.util.List;

public class HelpTable {

    public static String generateHelpTable(String[] moves){
        AsciiTable at = new AsciiTable();

        at.addRule();
        at.addRow(generateFirstRow(moves));
        at.addRule();

        for (int i=0; i<moves.length; i++){
            at.addRow(generateRow(moves,i));
            at.addRule();
        }

        return at.render(getWith(moves));
    }

    private static String[] generateFirstRow(String[] moves){
        List<String> firstRow = new ArrayList<>();
        firstRow.add("PC\\User");
        for (String moveTmp: moves) {
            firstRow.add(moveTmp);
        }
        return firstRow.toArray(new String[0]);
    }
    private static String[] generateRow(String[] moves, int rowNumber){
        List<String> firstRow = new ArrayList<>();
        firstRow.add(moves[rowNumber]);
        for (int i = 0; i<moves.length; i++) {
            int resultInt = Model.isUserWinner(i, rowNumber, moves);
            firstRow.add(getTextResult(resultInt));
        }
        return firstRow.toArray(new String[0]);
    }

    private static String getTextResult(int idResult){
        if (idResult > 0)return "WIN";
        else  if (idResult < 0) return "LOSE";
        else return "DRAW";
    }

    private static int getWith(String[] moves){
        int with = 0;
        for (String moveTmp: moves) {
            with += moveTmp.length() + 4;
        }
        return with;
    }


}
