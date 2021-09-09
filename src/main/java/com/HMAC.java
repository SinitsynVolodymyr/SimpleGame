package com;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HMAC {

    private static final String CODING = "HmacSHA256";

    private String moves[];
    private String move;
    private byte key[] = new byte[16];
    private byte hashingMove[] = new byte[16];

    public HMAC(String moves[]) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        this.moves = moves;
        new SecureRandom().nextBytes(key);
        this.move = initMove();
        hashingMove = hashingHMAC(key, move);
    }

    private String initMove(){
        int rand = new SecureRandom().nextInt(moves.length);
        return moves[rand];
    }

    public byte[] hashingHMAC(byte key[] ,String message) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        Mac signer = Mac.getInstance(CODING);
        signer.init(new SecretKeySpec(key, CODING));
        byte[] digest = signer.doFinal(message.getBytes("utf-8"));
        return digest;
    }

    public String getMove() {
        return move;
    }

    public String getHashingMoveAsString() {
        return Hex.encodeHexString(hashingMove);
    }

    public byte[] getHashingMove() {
        return hashingMove;
    }

    public String getKeyAsString() {
        return Hex.encodeHexString(key);
    }


}
