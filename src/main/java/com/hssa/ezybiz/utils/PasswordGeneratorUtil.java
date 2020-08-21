/**
 * 
 */
package com.hssa.ezybiz.utils;

import java.util.Random;

import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import com.hssa.ezybiz.dto.UserPassword;

public class PasswordGeneratorUtil {
	/**passwordGenerator() method generates a random password of length 8.
     * @return random password.
     * When user forgets his password and request for new one then this method used for generating  password
     */
    public static String passwordGenerator() {
        char[] allChar =
        { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
          'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
          'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9', '@', '!','@','#','$','%'};
        int length = 8;
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
        	stringBuilder.append(allChar[random.nextInt(allChar.length)]);
        }
//         JcoeLogger.info(null, "In password Generator & Password is " + stringBuilder.toString());
        return stringBuilder.toString();
    }
    
    public static String newPasswordGenerator(){
    	char[] allChar =
            { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
    	int length = 5;
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
        	stringBuilder.append(allChar[random.nextInt(allChar.length)]);
        }
//         JcoeLogger.info(null, "In password Generator & Password is " + stringBuilder.toString());
        return "anz"+stringBuilder.toString();
    }


    /**encodePassword() method takes password as input string and convert it into an encoded form using SHA1 algorithm.
     * This encoded password is then used for comparing,storing and updating into database after performing
     * operations for these processes.
     * @param password
     * @return encoded string using SHA1 algorithm.
     */
 /*   public static String encodePassword(String inputPassword) {

        String encodedPassword = null;
        try {

            byte[] passwordBytes = inputPassword.getBytes();
            byte[] keyBytes = inputPassword.getBytes();

            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(passwordBytes);
            byte[] digestBytes = messageDigest.digest(keyBytes);
            encodedPassword=Base64.encodeBase64String(digestBytes);
        }

        catch (NoSuchAlgorithmException e) {
			JcoeLogger.error(Session.getServerSession(), e, "");
        }

        return encodedPassword;
    	}*/
    
	@SuppressWarnings("restriction")
	public static UserPassword encodePassword(UserPassword genPassVO) throws Exception {
		String base64Password = StringUtils.EMPTY;
		String base64Key = StringUtils.EMPTY;
		String passwd = StringUtils.EMPTY;
		if (genPassVO.isStatus()) {
			try {

				String salgorithm = "Blowfish";
				JCOEPasswordEncoder encoder = JCOEPasswordEncoder.getEncoder();
				passwd = genPassVO.getUserPassword();
				SecretKey key1 = encoder.getKey(salgorithm);
				byte[] cipherText = null;
				// for retail and other users
				cipherText = encoder.encodePassword(passwd + genPassVO.getUserName(), key1);

				base64Password = Base64.encodeBase64String(cipherText);
				base64Key = Base64.encodeBase64String(key1.getEncoded());
				genPassVO.setEncodedPassword(base64Password);
				genPassVO.setPasswordKey(base64Key);

			} catch (Exception ne) {
				
				throw ne;
			}
		}

		return genPassVO;
		
		

	}
}
