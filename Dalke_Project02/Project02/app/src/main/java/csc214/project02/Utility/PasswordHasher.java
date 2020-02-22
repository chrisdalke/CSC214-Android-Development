////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 2
////////////////////////////////////////

package csc214.project02.Utility;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import java.security.MessageDigest;

////////////////////////////////////////
// Password Hasher
////////////////////////////////////////
//Password hashing function
//Used from http://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash
// I'm not sure why I chose to hash the passwords considering I'm using md5 andthis is a project.
public class PasswordHasher {

    public static String hash(String input){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(input.getBytes());
            StringBuffer hashedPassword = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                hashedPassword.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return hashedPassword.toString();
        } catch (Exception e) {
        }
        return "null";
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////