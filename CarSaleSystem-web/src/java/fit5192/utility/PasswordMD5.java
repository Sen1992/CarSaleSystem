/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author 王森
 */
public class PasswordMD5 {
    public static String generateCode(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] srcBytes = str.getBytes();
        md5.update(srcBytes);
        byte[] resultBytes = md5.digest();
        String result = new String(resultBytes);
        return result;
    }
}