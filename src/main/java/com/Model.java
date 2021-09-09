package com;

public class Model {
    public static int isUserWinner(int userMoveId, HMAC hmac, String[] args) {
        int compMoveId = -1;

        for (int i = 0; i<args.length; i++){
            if (args[i].equals(hmac.getMove())){compMoveId = i;}
        }

        if (compMoveId==-1){
            throw new IllegalArgumentException("User or hmac id not found");
        }

        if (userMoveId==compMoveId){
            return 0;
        }else{
            int halfCount = args.length/2;
            int userOffset = halfCount - userMoveId;

            if (userOffset>=0) {
                if (compMoveId > userMoveId
                        && compMoveId < args.length - userOffset) return -1;
                 else return 1;
            }else{
                if (compMoveId < userMoveId
                        && compMoveId > - userOffset) return 1;
                else return -1;
            }

        }
    }


}
