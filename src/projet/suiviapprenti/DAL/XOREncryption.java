package projet.suiviapprenti.DAL;

public class XOREncryption {
	
	private static final char[] key = {'P', 'R', 'O', 'J', 'E', 'T'};
	
	public static String Crypt(String password) {
		byte[] buffer = password.getBytes();
		
		for(int i = 0; i < buffer.length; i++) {
			
			for(int j = 0; j < key.length; j++) {
				buffer[i] = (byte) (buffer[i] ^ key[j]);
			}
		}
		
		return new String(buffer);
	}
}
