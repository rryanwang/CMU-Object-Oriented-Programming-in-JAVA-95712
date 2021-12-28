package exam2;

public interface Encryptable {
	static final char SPACE = 32;  //ASCII code for space
	public String encrypt(); //Takes message and key of the message-instance that calls it and returns and encrypted string 
	public String decrypt(); //Takes message and key of the message-instance that calls it and returns a decrypted string
}
